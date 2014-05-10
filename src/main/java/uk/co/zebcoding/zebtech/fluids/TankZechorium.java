package uk.co.zebcoding.zebtech.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TankZechorium extends FluidTank {


    public TankZechorium(Fluid fluid, int amount, int capacity) {
        super(fluid, amount, capacity);
        // TODO Auto-generated constructor stub
    }

    public int getFluidAmount() {
        FluidStack f = this.getFluid();
        if (f == null) return 0;
        return f.amount;
    }

}
