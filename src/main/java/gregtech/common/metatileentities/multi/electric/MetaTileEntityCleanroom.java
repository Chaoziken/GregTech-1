package gregtech.common.metatileentities.multi.electric;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityCleanroom extends RecipeMapMultiblockController {

    private static MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.INPUT_ENERGY
    };

    private int cleanLevel;
    private int rawLevel;
    private boolean isClean;

    public MetaTileEntityCleanroom(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.CLEANROOM_RECIPES);
        this.recipeMapWorkable = new CleanroomWorkableHandler(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCleanroom(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXXXX", "XXX XXX", "XXX XXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX")
                .aisle("XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XFFFFFX").setRepeatable(2, 2)
                .aisle("XXXXXXX", "       ", "       ", "X     X", "X     X", "X     X", "XFFSFFX")
                .aisle("XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XFFFFFX").setRepeatable(2, 2)
                .aisle("XXXXXXX", "XXX XXX", "XXX XXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX")
                .where('X', maintenancePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('F', filterPredicate())
                .where('S', selfPredicate())
                .where(' ', (tile) -> true)
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.casingType.PLASCRETE);
    }

    public static Predicate<BlockWorldState> filterPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockCleanroomCasing))
                return false;
            BlockCleanroomCasing blockCleanroomCasing = (BlockCleanroomCasing) blockState.getBlock();
            BlockCleanroomCasing.casingType casingType = blockCleanroomCasing.getState(blockState);
            blockWorldState.getMatchContext().increment("filterLevel", casingType.getLevel());
            return true;
        };
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.PLASCRETE;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.CLEANROOM_OVERLAY;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.rawLevel = context.getOrDefault("filterLevel", 0);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.cleanLevel = 0;
        this.rawLevel = 0;
        this.isClean = false;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isClean) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.cleanroom.clean_state"));
            textList.add(new TextComponentTranslation("gregtech.multiblock.cleanroom.level", this.cleanLevel));
        } else {
            textList.add(new TextComponentTranslation("gregtech.multiblock.cleanroom.dirty_state"));
        }
    }

    protected void setCleanRecipeCompletion(boolean state) {
        this.isClean = state;
        if (isClean)
            this.cleanLevel = calculateCleanLevel(this.rawLevel);
        else
            this.cleanLevel = 0;
    }

    private int calculateCleanLevel(int rawLevel) {
        if (rawLevel >= 1024)
            return 1;
        else if (rawLevel >= 512)
            return 2;
        else if (rawLevel >= 256)
            return 3;
        else if (rawLevel >= 128)
            return 4;
        else if (rawLevel >= 64)
            return 5;
        else if (rawLevel >= 32)
            return 6;
        else if (rawLevel >= 16)
            return 7;
        else if (rawLevel >= 8)
            return 8;
        return 9;
    }

    protected int getRawLevel() {
        return this.rawLevel;
    }

    protected static class CleanroomWorkableHandler extends MultiblockRecipeLogic {

        private static final Recipe cleanroomRecipe = new Recipe(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1200, 1, true);

        public CleanroomWorkableHandler(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
        }

        private MetaTileEntityCleanroom getCleanroom() {
            return (MetaTileEntityCleanroom) metaTileEntity;
        }

        @Override
        protected void trySearchNewRecipe() {
            // do not run recipes when there are more than 5 maintenance problems
            MultiblockWithDisplayBase controller = (MultiblockWithDisplayBase) metaTileEntity;
            if (controller.hasMaintenanceMechanics() && controller.getNumMaintenanceProblems() > 5)
                return;

            setupRecipe(cleanroomRecipe);
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            int[] overclock = calculateOverclock(calculateRecipeVoltage(), getOverclockVoltage(), recipe.getDuration());
            this.progressTime = 1;
            setMaxProgress(overclock[1]);
            this.recipeEUt = overclock[0];
            System.out.println(overclock[0]);

            // prevent NBT writing NPE on world load
            this.itemOutputs = NonNullList.create();
            this.fluidOutputs = new ArrayList<>();

            if (this.wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
            } else {
                this.setActive(true);
            }
        }

        protected int calculateRecipeVoltage() {
            if (getCleanroom().getRawLevel() < 32)
                return getCleanroom().getRawLevel();
            return (int) GTValues.VA[GTUtility.getTierByVoltage(getCleanroom().getRawLevel())];
        }

        @Override
        protected int[] calculateOverclock(int EUt, long voltage, int duration) {
            // apply maintenance penalties
            int numMaintenanceProblems = (this.metaTileEntity instanceof MultiblockWithDisplayBase) ?
                    ((MultiblockWithDisplayBase) metaTileEntity).getNumMaintenanceProblems() : 0;

            int tier = getOverclockingTier(voltage);

            // Cannot overclock
            if (GTValues.V[tier] <= EUt || tier == 0)
                return new int[]{EUt, duration};

            int resultEUt = EUt;
            double resultDuration = duration;
            double divisor = ConfigHolder.U.overclockDivisor;
            int maxOverclocks = tier - 1; // exclude ULV overclocking

            //do not overclock further if duration is already too small
            while (resultDuration >= 3 && resultEUt <= GTValues.V[tier - 1] && maxOverclocks != 0) {
                resultEUt *= 4;
                resultDuration /= divisor;
                maxOverclocks--;
            }

            resultDuration *= (1 + 0.1 * numMaintenanceProblems);

            return new int[]{resultEUt, (int) Math.ceil(resultDuration)};
        }

        @Override
        protected void completeRecipe() {
            // increase total on time
            MultiblockWithDisplayBase controller = (MultiblockWithDisplayBase) metaTileEntity;
            if (controller.hasMaintenanceMechanics())
                controller.calculateMaintenance(this.progressTime);

            // complete cleaning
            if (!getCleanroom().isClean)
                getCleanroom().setCleanRecipeCompletion(true);

            this.progressTime = 0;
            setMaxProgress(0);
            this.recipeEUt = 0;

            // prevent NBT writing NPE on world load
            this.fluidOutputs = null;
            this.itemOutputs = null;

            this.hasNotEnoughEnergy = false;
            this.wasActiveAndNeedsUpdate = true;
        }

        @Override
        protected void updateRecipeProgress() {
            super.updateRecipeProgress();

            if (isHasNotEnoughEnergy() && getCleanroom().isClean)
                getCleanroom().setCleanRecipeCompletion(false);
        }
    }
}
