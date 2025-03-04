package gregtech.api.recipes;

import com.google.common.collect.ImmutableList;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.recipes.recipeproperties.RecipePropertyStorage;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ItemStackHashStrategy;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that represent machine recipe.<p>
 * <p>
 * Recipes are created using {@link RecipeBuilder} or its subclasses in builder-alike pattern. To get RecipeBuilder use {@link RecipeMap#recipeBuilder()}.<p>
 * <p>
 * Example:
 * RecipeMap.POLARIZER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.APPLE)).outputs(new ItemStack(Items.GOLDEN_APPLE)).duration(256).EUt(480).buildAndRegister();<p>
 * This will create and register Polarizer recipe with Apple as input and Golden apple as output, duration - 256 ticks and energy consumption of 480 EU/t.<p>
 * To get example for particular RecipeMap see {@link RecipeMap}<p>
 * <p>
 * Recipes are immutable.
 */
public class Recipe {

    public static int getMaxChancedValue() {
        return 10000;
    }

    public static String formatChanceValue(int outputChance) {
        return String.format("%.2f", outputChance / (getMaxChancedValue() * 1.0) * 100);
    }

    private final List<CountableIngredient> inputs;
    private final NonNullList<ItemStack> outputs;

    /**
     * A chance of 10000 equals 100%
     */
    private final List<ChanceEntry> chancedOutputs;
    private final List<FluidStack> fluidInputs;
    private final List<FluidStack> fluidOutputs;

    private final int duration;

    /**
     * if > 0 means EU/t consumed, if < 0 - produced
     */
    private final int EUt;

    /**
     * If this Recipe is hidden from JEI
     */
    private final boolean hidden;

    private final RecipePropertyStorage recipePropertyStorage;

    private static final ItemStackHashStrategy hashStrategy = ItemStackHashStrategy.comparingAll();

    private final int hashCode;

    public Recipe(List<CountableIngredient> inputs, List<ItemStack> outputs, List<ChanceEntry> chancedOutputs,
                  List<FluidStack> fluidInputs, List<FluidStack> fluidOutputs,
                  int duration, int EUt, boolean hidden) {
        this.recipePropertyStorage = new RecipePropertyStorage();
        this.inputs = NonNullList.create();
        this.inputs.addAll(inputs);
        this.outputs = NonNullList.create();
        this.outputs.addAll(outputs);
        this.chancedOutputs = new ArrayList<>(chancedOutputs);
        this.fluidInputs = ImmutableList.copyOf(fluidInputs);
        this.fluidOutputs = ImmutableList.copyOf(fluidOutputs);
        this.duration = duration;
        this.EUt = EUt;
        this.hidden = hidden;

        //sort not consumables inputs to the end
        this.inputs.sort((ing1, ing2) -> Boolean.compare(ing1.isNonConsumable(), ing2.isNonConsumable()));
        this.hashCode = makeHashCode();
    }

    public Recipe copy() {

        // Create a new Recipe object
        Recipe newRecipe =  new Recipe(this.inputs, this.outputs, this.chancedOutputs, this.fluidInputs, this.fluidOutputs, this.duration, this.EUt, this.hidden);

        // Apply Properties from the original recipe onto the new one
        if(this.recipePropertyStorage.getSize() > 0) {
            for(Map.Entry<RecipeProperty<?>, Object> property : getRecipePropertyStorage().getRecipeProperties()) {
                newRecipe.setProperty(property.getKey(), property.getValue());
            }
        }

        return newRecipe;
    }

    /**
     * Trims the recipe outputs, chanced outputs, and fluid outputs based on the performing MetaTileEntity's trim limit.
     *
     * @param currentRecipe The recipe to perform the output trimming upon
     * @param recipeMap     The RecipeMap that the recipe is from
     * @param itemTrimLimit The Limit to which item outputs should be trimmed to, -1 for no trimming
     * @param fluidTrimLimit The Limit to which fluid outputs should be trimmed to, -1 for no trimming
     *
     * @return A new Recipe whose outputs have been trimmed.
     */
    public Recipe trimRecipeOutputs(Recipe currentRecipe, RecipeMap<?> recipeMap, int itemTrimLimit, int fluidTrimLimit) {

        // Fast return early if no trimming desired
        if(itemTrimLimit == -1 && fluidTrimLimit == -1) {
            return currentRecipe;
        }


        currentRecipe = currentRecipe.copy();
        RecipeBuilder<?> builder = new RecipeBuilder<>(currentRecipe, recipeMap);

        builder.clearOutputs();
        builder.clearChancedOutput();
        builder.clearFluidOutputs();

        // Chanced outputs are removed in this if they cannot fit the limit
        Pair<List<ItemStack>, List<Recipe.ChanceEntry>> recipeOutputs = currentRecipe.getItemAndChanceOutputs(itemTrimLimit);

        // Add the trimmed chanced outputs and outputs
        builder.chancedOutputs(recipeOutputs.getRight());
        builder.outputs(recipeOutputs.getLeft());

        List<FluidStack> recipeFluidOutputs = currentRecipe.getAllFluidOutputs(fluidTrimLimit);

        // Add the trimmed fluid outputs
        builder.fluidOutputs(recipeFluidOutputs);


        return builder.build().getResult();
    }

    public final boolean matches(boolean consumeIfSuccessful, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, MatchingMode matchingMode) {
        return matches(consumeIfSuccessful, GTUtility.itemHandlerToList(inputs), GTUtility.fluidHandlerToList(fluidInputs), matchingMode);
    }

    public final boolean matches(boolean consumeIfSuccessful, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
        return matches(consumeIfSuccessful, GTUtility.itemHandlerToList(inputs), GTUtility.fluidHandlerToList(fluidInputs), MatchingMode.DEFAULT);
    }

    public boolean matches(boolean consumeIfSuccessful, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        return matches(consumeIfSuccessful, inputs, fluidInputs, MatchingMode.DEFAULT);
    }

    /**
     * This methods aim to verify if the current recipe matches the given inputs according to matchingMode mode.
     *
     * @param consumeIfSuccessful if true and matchingMode is equal to {@link MatchingMode#DEFAULT} will consume the inputs of the recipe.
     * @param inputs              Items input or Collections.emptyList() if none.
     * @param fluidInputs         Fluids input or Collections.emptyList() if none.
     * @param matchingMode        How this method should check if inputs matches according to {@link MatchingMode} description.
     * @return true if the recipe matches the given inputs false otherwise.
     */
    public boolean matches(boolean consumeIfSuccessful, List<ItemStack> inputs, List<FluidStack> fluidInputs, MatchingMode matchingMode) {
        Pair<Boolean, Integer[]> fluids = null;
        Pair<Boolean, Integer[]> items = null;

        if (matchingMode == MatchingMode.IGNORE_FLUIDS) {
            if (getInputs().isEmpty()) {
                return false;
            }
        } else {
            fluids = matchesFluid(fluidInputs);
            if (!fluids.getKey()) {
                return false;
            }
        }

        if (matchingMode == MatchingMode.IGNORE_ITEMS) {
            if (getFluidInputs().isEmpty()) {
                return false;
            }
        } else {
            items = matchesItems(inputs);
            if (!items.getKey()) {
                return false;
            }
        }

        if (consumeIfSuccessful && matchingMode == MatchingMode.DEFAULT) {
            Integer[] fluidAmountInTank = fluids.getValue();
            Integer[] itemAmountInSlot = items.getValue();
            for (int i = 0; i < fluidAmountInTank.length; i++) {
                FluidStack fluidStack = fluidInputs.get(i);
                int fluidAmount = fluidAmountInTank[i];
                if (fluidStack == null || fluidStack.amount == fluidAmount)
                    continue;
                fluidStack.amount = fluidAmount;
                if (fluidStack.amount == 0)
                    fluidInputs.set(i, null);
            }
            for (int i = 0; i < itemAmountInSlot.length; i++) {
                ItemStack itemInSlot = inputs.get(i);
                int itemAmount = itemAmountInSlot[i];
                if (itemInSlot.isEmpty() || itemInSlot.getCount() == itemAmount)
                    continue;
                itemInSlot.setCount(itemAmountInSlot[i]);
            }
        }

        return true;
    }

    private Pair<Boolean, Integer[]> matchesItems(List<ItemStack> inputs) {
        Integer[] itemAmountInSlot = new Integer[inputs.size()];

        for (int i = 0; i < itemAmountInSlot.length; i++) {
            ItemStack itemInSlot = inputs.get(i);
            itemAmountInSlot[i] = itemInSlot.isEmpty() ? 0 : itemInSlot.getCount();
        }

        for (CountableIngredient ingredient : this.inputs) {
            int ingredientAmount = ingredient.getCount();
            for (int i = 0; i < inputs.size(); i++) {
                ItemStack inputStack = inputs.get(i);
                if (inputStack.isEmpty() || !ingredient.getIngredient().apply(inputStack))
                    continue;
                int itemAmountToConsume = Math.min(itemAmountInSlot[i], ingredientAmount);
                ingredientAmount -= itemAmountToConsume;
                if (!ingredient.isNonConsumable()) itemAmountInSlot[i] -= itemAmountToConsume;
                if (ingredientAmount == 0) break;
            }
            if (ingredientAmount > 0)
                return Pair.of(false, itemAmountInSlot);
        }

        return Pair.of(true, itemAmountInSlot);
    }

    private Pair<Boolean, Integer[]> matchesFluid(List<FluidStack> fluidInputs) {
        Integer[] fluidAmountInTank = new Integer[fluidInputs.size()];

        for (int i = 0; i < fluidAmountInTank.length; i++) {
            FluidStack fluidInTank = fluidInputs.get(i);
            fluidAmountInTank[i] = fluidInTank == null ? 0 : fluidInTank.amount;
        }

        for (FluidStack fluid : this.fluidInputs) {
            int fluidAmount = fluid.amount;
            boolean isNotConsumed = (fluid.tag != null && fluid.tag.hasKey("nonConsumable"));
            for (int i = 0; i < fluidInputs.size(); i++) {
                FluidStack tankFluid = fluidInputs.get(i);
                if (tankFluid == null || tankFluid.getFluid() != fluid.getFluid())
                    continue;
                int fluidAmountToConsume = Math.min(fluidAmountInTank[i], fluidAmount);
                fluidAmount -= fluidAmountToConsume;
                if (!isNotConsumed) fluidAmountInTank[i] -= fluidAmountToConsume;
                if (fluidAmount == 0) break;
            }
            if (fluidAmount > 0)
                return Pair.of(false, fluidAmountInTank);
        }
        return Pair.of(true, fluidAmountInTank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return this.EUt == recipe.EUt &&
                this.duration == recipe.duration &&
                hasSameInputs(recipe) &&
                hasSameOutputs(recipe) &&
                hasSameChancedOutputs(recipe) &&
                hasSameFluidInputs(recipe) &&
                hasSameFluidOutputs(recipe) &&
                hasSameRecipeProperties(recipe);
    }

    private int makeHashCode() {
        int hash = Objects.hash(EUt, duration);
        hash = 31 * hash + hashInputs();
        hash = 41 * hash + hashOutputs();
        hash = 31 * hash + hashChancedOutputs();
        hash = 31 * hash + hashFluidList(this.fluidInputs);
        hash = 41 * hash + hashFluidList(this.fluidOutputs);
        hash = 31 * hash + hashRecipeProperties();
        return hash;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    private int hashInputs() {
        int hash = 0;
        for (CountableIngredient countableIngredient : this.inputs) {
            for (ItemStack is : countableIngredient.getIngredient().getMatchingStacks()) {
                hash = 31 * hash + ItemStackHashStrategy.comparingAll().hashCode(is);
            }
        }
        return hash;
    }

    private boolean hasSameInputs(Recipe otherRecipe) {
        if (this.inputs.size() != otherRecipe.inputs.size()) return false;
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < this.inputs.get(i).getIngredient().getMatchingStacks().length; j++) {
                if (!hashStrategy.equals(this.inputs.get(i).getIngredient().getMatchingStacks()[j],
                        otherRecipe.inputs.get(i).getIngredient().getMatchingStacks()[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private int hashOutputs() {
        int hash = 0;
        for (ItemStack is : this.outputs) {
            hash = 31 * hash + hashStrategy.hashCode(is);
        }
        return hash;
    }

    private boolean hasSameOutputs(Recipe otherRecipe) {
        if (this.outputs.size() != otherRecipe.outputs.size()) return false;
        for (int i = 0; i < outputs.size(); i++) {
            if (!hashStrategy.equals(this.outputs.get(i), otherRecipe.outputs.get(i))) {
                return false;
            }
        }
        return true;
    }

    private int hashChancedOutputs() {
        int hash = 0;
        for (ChanceEntry chanceEntry : this.chancedOutputs) {
            hash = 31 * hash + hashStrategy.hashCode(chanceEntry.itemStack);
            hash = 31 * hash + chanceEntry.chance;
            hash = 31 * hash + chanceEntry.boostPerTier;
        }
        return hash;
    }

    private boolean hasSameChancedOutputs(Recipe otherRecipe) {
        if (this.chancedOutputs.size() != otherRecipe.chancedOutputs.size()) return false;
        for (int i = 0; i < chancedOutputs.size(); i++) {
            if (!hashStrategy.equals(this.chancedOutputs.get(i).itemStack, otherRecipe.chancedOutputs.get(i).itemStack)) {
                return false;
            }
        }
        return true;
    }

    public int hashFluidList(List<FluidStack> fluids) {
        int hash = 0;
        for (FluidStack fluidStack : fluids) {
            hash = 31 * hash + new FluidKey(fluidStack).hashCode();
            hash = 31 * hash + fluidStack.amount;
        }
        return hash;
    }

    private boolean hasSameFluidInputs(Recipe otherRecipe) {
        if (this.fluidInputs.size() != otherRecipe.fluidInputs.size()) return false;
        for (int i = 0; i < fluidInputs.size(); i++) {
            if (!fluidInputs.get(i).isFluidStackIdentical(otherRecipe.fluidInputs.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasSameFluidOutputs(Recipe otherRecipe) {
        if (this.fluidOutputs.size() != otherRecipe.fluidOutputs.size()) return false;
        for (int i = 0; i < fluidOutputs.size(); i++) {
            if (!fluidOutputs.get(i).isFluidStackIdentical(otherRecipe.fluidOutputs.get(i))) {
                return false;
            }
        }
        return true;
    }

    private int hashRecipeProperties() {
        int hash = 0;
        for (Map.Entry<RecipeProperty<?>, Object> propertyObjectEntry : this.recipePropertyStorage.getRecipeProperties()) {
            hash = 31 * hash + propertyObjectEntry.getKey().hashCode();
        }
        return hash;
    }

    private boolean hasSameRecipeProperties(Recipe otherRecipe) {
        if (this.getPropertyCount() != otherRecipe.getPropertyCount()) return false;
        return this.recipePropertyStorage.getRecipeProperties().containsAll(otherRecipe.recipePropertyStorage.getRecipeProperties());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("inputs", inputs)
                .append("outputs", outputs)
                .append("chancedOutputs", chancedOutputs)
                .append("fluidInputs", fluidInputs)
                .append("fluidOutputs", fluidOutputs)
                .append("duration", duration)
                .append("EUt", EUt)
                .append("hidden", hidden)
                .toString();
    }

    ///////////////////
    //    Getters    //
    ///////////////////

    public List<CountableIngredient> getInputs() {
        return inputs;
    }

    public NonNullList<ItemStack> getOutputs() {
        return outputs;
    }

    // All Recipes this method is called for should be already trimmed, if required

    /**
     * Returns all outputs from the recipe.
     * This is where Chanced Outputs for the recipe are calculated.
     * The Recipe should be trimmed by calling {@link Recipe#getItemAndChanceOutputs(int)} before calling this method,
     * if trimming is required.
     *
     * @param tier The Voltage Tier of the Recipe, used for chanced output calculation
     * @param recipeMap The RecipeMap that the recipe is being performed upon, used for chanced output calculation
     *
     * @return A list of all resulting ItemStacks from the recipe, after chance has been applied to any chanced outputs
     */
    public List<ItemStack> getResultItemOutputs(int tier, RecipeMap<?> recipeMap) {
        ArrayList<ItemStack> outputs = new ArrayList<>(GTUtility.copyStackList(getOutputs()));
        List<ChanceEntry> chancedOutputsList = getChancedOutputs();
        List<ItemStack> resultChanced = new ArrayList<>();
        for (ChanceEntry chancedOutput : chancedOutputsList) {
            int outputChance = recipeMap.getChanceFunction().chanceFor(chancedOutput.getChance(), chancedOutput.getBoostPerTier(), tier);
            if (GTValues.RNG.nextInt(Recipe.getMaxChancedValue()) <= outputChance) {
                ItemStack stackToAdd = chancedOutput.getItemStack();
                GTUtility.addStackToItemStackList(stackToAdd, resultChanced);
            }
        }

        outputs.addAll(resultChanced);

        return outputs;
    }

    /**
     * Returns the maximum possible recipe outputs from a recipe, divided into regular and chanced outputs
     * Takes into account any specific output limiters, ie macerator slots, to trim down the output list
     * Trims from chanced outputs first, then regular outputs
     *
     * @param outputLimit The limit on the number of outputs, -1 for disabled.
     * @return A Pair of recipe outputs and chanced outputs, limited by some factor
     */
    public Pair<List<ItemStack>, List<ChanceEntry>> getItemAndChanceOutputs(int outputLimit) {
        List<ItemStack> outputs = new ArrayList<>();



        // Create an entry for the chanced outputs, and initially populate it
        List<ChanceEntry> chancedOutputs = new ArrayList<>(getChancedOutputs());


        // No limiting
        if(outputLimit == -1) {
            outputs.addAll(GTUtility.copyStackList(getOutputs()));
        }
        // If just the regular outputs would satisfy the outputLimit
        else if(getOutputs().size() >= outputLimit) {
            outputs.addAll(GTUtility.copyStackList(getOutputs()).subList(0, Math.min(outputLimit, getOutputs().size())));
            // clear the chanced outputs, as we are only getting regular outputs
            chancedOutputs.clear();
        }
        // If the regular outputs and chanced outputs are required to satisfy the outputLimit
        else if(!getOutputs().isEmpty() && (getOutputs().size() + chancedOutputs.size()) >= outputLimit) {
            outputs.addAll(GTUtility.copyStackList(getOutputs()));

            // Calculate the number of chanced outputs after adding all the regular outputs
            int numChanced = outputLimit - getOutputs().size();

            chancedOutputs = chancedOutputs.subList(0, Math.min(numChanced, chancedOutputs.size()));
        }
        // There are only chanced outputs to satisfy the outputLimit
        else if(getOutputs().isEmpty()) {
            chancedOutputs = chancedOutputs.subList(0, Math.min(outputLimit, chancedOutputs.size()));
        }
        // The number of outputs + chanced outputs is lower than the trim number, so just add everything
        else {
            outputs.addAll(GTUtility.copyStackList(getOutputs()));
            // Chanced outputs are taken care of in the original copy
        }

        return Pair.of(outputs, chancedOutputs);
    }

    /**
     * Returns a list of every possible ItemStack output from a recipe, including all possible chanced outputs.
     *
     * @return A List of ItemStack outputs from the recipe, including all chanced outputs
     */
    public List<ItemStack> getAllItemOutputs() {
        List<ItemStack> recipeOutputs = new ArrayList<>(this.outputs);

        recipeOutputs.addAll(chancedOutputs.stream().map(ChanceEntry::getItemStack).collect(Collectors.toList()));

        return recipeOutputs;
    }


    public List<ChanceEntry> getChancedOutputs() {
        return chancedOutputs;
    }

    public List<FluidStack> getFluidInputs() {
        return fluidInputs;
    }

    public boolean hasInputFluid(FluidStack fluid) {
        for (FluidStack fluidStack : fluidInputs) {
            if (fluid.getFluid() == fluidStack.getFluid()) {
                if (fluidStack.tag != null && fluidStack.tag.hasKey("nonConsumable")) {
                    fluidStack = fluidStack.copy();
                    fluidStack.tag.removeTag("nonConsumable");
                    if (fluidStack.tag.isEmpty()) {
                        fluidStack.tag = null;
                    }
                }
                return fluidStack.isFluidEqual(fluid);
            }
        }
        return false;
    }

    public List<FluidStack> getFluidOutputs() {
        return fluidOutputs;
    }

    /**
     * Trims the list of fluid outputs based on some passed factor.
     * Similar to {@link Recipe#getItemAndChanceOutputs(int)} but does not handle chanced fluid outputs
     *
     * @param outputLimit The limiting factor to trim the fluid outputs to, -1 for disabled.
     *
     * @return A trimmed List of fluid outputs.
     */
    // TODO, implement future chanced fluid outputs here
    public List<FluidStack> getAllFluidOutputs(int outputLimit) {
        return outputLimit == -1 ? fluidOutputs : fluidOutputs.subList(0, Math.min(fluidOutputs.size(), outputLimit));
    }

    public int getDuration() {
        return duration;
    }

    public int getEUt() {
        return EUt;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean hasValidInputsForDisplay() {
        boolean hasValidInputs = true;
        for (CountableIngredient ingredient : inputs) {
            ItemStack[] matchingItems = ingredient.getIngredient().getMatchingStacks();
            hasValidInputs &= Arrays.stream(matchingItems).anyMatch(s -> !s.isEmpty());
        }
        return hasValidInputs;
    }

    ///////////////////////////////////////////////////////////
    //               Property Helper Methods                 //
    ///////////////////////////////////////////////////////////
    public <T> T getProperty(RecipeProperty<T> property, T defaultValue) {
        return recipePropertyStorage.getRecipePropertyValue(property, defaultValue);
    }

    public Object getPropertyRaw(String key) {
        return recipePropertyStorage.getRawRecipePropertyValue(key);
    }

    public boolean setProperty(RecipeProperty<?> property, Object value) {
        return recipePropertyStorage.store(property, value);
    }

    public Set<Map.Entry<RecipeProperty<?>, Object>> getPropertyValues() {
        return recipePropertyStorage.getRecipeProperties();
    }

    public Set<String> getPropertyKeys() {
        return recipePropertyStorage.getRecipePropertyKeys();
    }

    public boolean hasProperty(RecipeProperty<?> property) {
        return recipePropertyStorage.hasRecipeProperty(property);
    }

    public int getPropertyCount() {
        return recipePropertyStorage.getSize();
    }

    public int getUnhiddenPropertyCount() {
        return (int) recipePropertyStorage.getRecipeProperties().stream().filter((property) -> !property.getKey().isHidden()).count();
    }

    public RecipePropertyStorage getRecipePropertyStorage() {
        return recipePropertyStorage;
    }

    ///////////////////////////////////////////////////////////
    //                   Chanced Output                      //
    ///////////////////////////////////////////////////////////
    public static class ChanceEntry {
        private final ItemStack itemStack;
        private final int chance;
        private final int boostPerTier;

        public ChanceEntry(ItemStack itemStack, int chance, int boostPerTier) {
            this.itemStack = itemStack.copy();
            this.chance = chance;
            this.boostPerTier = boostPerTier;
        }

        public ItemStack getItemStack() {
            return itemStack.copy();
        }

        public ItemStack getItemStackRaw() {
            return itemStack;
        }

        public int getChance() {
            return chance;
        }

        public int getBoostPerTier() {
            return boostPerTier;
        }

        public ChanceEntry copy() {
            return new ChanceEntry(itemStack, chance, boostPerTier);
        }
    }
}
