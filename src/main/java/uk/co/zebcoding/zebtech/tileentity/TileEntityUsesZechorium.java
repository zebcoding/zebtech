package uk.co.zebcoding.zebtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import uk.co.zebcoding.zebtech.fluids.ZFluids;

public class TileEntityUsesZechorium extends TileEntity {
    public FluidTank tank;

    public TileEntityUsesZechorium() {
        super();
        tank = new FluidTank(new FluidStack(ZFluids.liquidZechorium, 0), 5000);
    }

    public TileEntityUsesZechorium(int maxLiq) {
        super();
        tank = new FluidTank(new FluidStack(ZFluids.liquidZechorium, 0), maxLiq);
    }
}
