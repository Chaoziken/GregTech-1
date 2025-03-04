package gregtech.api.recipes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;

public class FluidKey {

    public final String fluid;
    public final NBTTagCompound tag;

    public FluidKey(FluidStack fluidStack) {
        this.fluid = fluidStack.getFluid().getName();
        this.tag = fluidStack.tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FluidKey)) return false;
        FluidKey fluidKey = (FluidKey) o;
        if (!Objects.equals(fluid, fluidKey.fluid) )
            return false;
        if (tag == null && fluidKey.tag != null) return false;
        else return tag == null || tag.equals(fluidKey.tag);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += Objects.hash(fluid);
        if (tag != null && !tag.isEmpty()) {
            hash += tag.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "FluidKey{" +
                "fluid=" + fluid +
                ", tag=" + tag +
                '}';
    }

    public Fluid getFluid() {
        return FluidRegistry.getFluid(this.fluid);
    }
}
