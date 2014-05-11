package uk.co.zebcoding.zebtech.tileentity;

import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityPipe;

public class TileEntityPipe extends TileEntity {

    /**
     * Connections, UP, DOWN, NORTH, EAST, SOUTH, WEST.
     */
    public ForgeDirection[] c = new ForgeDirection[6];

    /**
     * Amount of liquid the pipe can store.
     */
    int maxLiq = 1000 / 2;

    /**
     * The pipes storage.
     */
    public FluidTank tank;

    public TileEntityPipe() {
        tank = new FluidTank(new FluidStack(ZFluids.liquidZechorium, 0), maxLiq);
    }

    public void updateEntity() {
        this.updateConnections();
        this.balanceLiquid();
    }

    /**
     * Gets all adjacent pipes.
     */
    public void updateConnections() {
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            c[0] = ForgeDirection.UP;
        else c[0] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            c[1] = ForgeDirection.DOWN;
        else c[1] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityUsesZechorium)
            c[2] = ForgeDirection.NORTH;
        else c[2] = null;
        if (this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            c[3] = ForgeDirection.EAST;
        else c[3] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityUsesZechorium)
            c[4] = ForgeDirection.SOUTH;
        else c[4] = null;
        if (this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            c[5] = ForgeDirection.WEST;
        else c[5] = null;
    }

    /**
     * Looks to see if a single opposite connection exists.
     *
     * @param d = pipe connections.
     * @return true = one opposite connection.
     */
    public boolean oneOppositeConnection(ForgeDirection[] d) {
        ForgeDirection main = null;
        boolean isOpp = false;

        for (int i = 0; i < d.length; i++) {
            if (d[i] != null) {
                if (main == null && d[i] != null)
                    main = d[i];

                if (d[i] != null && d[i] != main)
                    if (!isOpposite(main, d[i]))
                        return false;
                    else isOpp = true;
            }
        }

        return isOpp;
    }

    /**
     * Checks if second is opposite main.
     *
     * @param main   = first check direction.
     * @param second = second check direction.
     * @return true = are opposite.
     */
    public boolean isOpposite(ForgeDirection main, ForgeDirection second) {
        if ((main.equals(ForgeDirection.NORTH) && second.equals(ForgeDirection.SOUTH)) || (main.equals(ForgeDirection.SOUTH) && second.equals(ForgeDirection.NORTH)))
            return true;
        if ((main.equals(ForgeDirection.EAST) && second.equals(ForgeDirection.WEST)) || (main.equals(ForgeDirection.WEST) && second.equals(ForgeDirection.EAST)))
            return true;
        if ((main.equals(ForgeDirection.UP) && second.equals(ForgeDirection.DOWN)) || (main.equals(ForgeDirection.DOWN) && second.equals(ForgeDirection.UP)))
            return true;

        return false;
    }

    /**
     * Balances liquid between 2 tanks.*
     */
    public void balanceLiquid() {
        for (int i = 0; i < this.c.length; i++) {
            if (this.c[i] != null) {
                TileEntity te = null;
                if (i == 0) {
                    te = this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0);
                } else if (i == 1) {
                    te = this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0);
                } else if (i == 2) {
                    te = this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1);
                } else if (i == 3) {
                    te = this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0);
                } else if (i == 4) {
                    te = this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1);
                } else if (i == 5) {
                    te = this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0);
                }

                if (te instanceof TileEntityPipe) {
                    TileEntityPipe p = (TileEntityPipe) te;

                    int stored1 = this.tank.getFluidAmount();
                    int stored2 = p.tank.getFluidAmount();

                    while (!(stored1 == stored2 || stored1 + 1 == stored2 || stored1 == stored2 + 1)) {
                        if (stored1 > stored2) {
                            stored1--;
                            stored2++;
                        } else {
                            stored1++;
                            stored2--;
                        }
                    }

                    this.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored1));
                    p.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored2));
                }

                if (te instanceof TileEntityZechoriumExciter) {
                    TileEntityZechoriumExciter e = (TileEntityZechoriumExciter) te;

                    int stored1 = this.tank.getFluidAmount();
                    int stored2 = e.tank.getFluidAmount();

                    while (!(stored1 >= this.maxLiq || stored2 <= 0)) {
                        stored1++;
                        stored2--;
                    }

                    this.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored1));
                    e.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored2));
                }

                if (te instanceof TileEntityUsesZechorium) {
                    TileEntityUsesZechorium e = (TileEntityUsesZechorium) te;

                    int stored1 = this.tank.getFluidAmount();
                    int stored2 = e.tank.getFluidAmount();

                    while (!(stored1 <= 0 || stored2 >= e.maxLiq)) {
                        stored1--;
                        stored2++;
                    }

                    this.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored1));
                    e.tank.setFluid(new FluidStack(ZFluids.liquidZechorium, stored2));
                }
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityPipe(this));
    }

}
