package uk.co.zebcoding.zebtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPipe extends TileEntity {

    /**
     * Connections, UP, DOWN, NORTH, EAST, SOUTH, WEST.
     */
    public ForgeDirection[] c = new ForgeDirection[6];

    public TileEntityPipe() {

    }

    public void updateEntity() {
        this.updateConnections();
    }

    /**
     * Gets all adjacent pipes.
     */
    public void updateConnections() {
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityPipe)
            c[0] = ForgeDirection.UP;
        else c[0] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityPipe)
            c[1] = ForgeDirection.DOWN;
        else c[1] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityPipe)
            c[2] = ForgeDirection.NORTH;
        else c[2] = null;
        if (this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe)
            c[3] = ForgeDirection.EAST;
        else c[3] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityPipe)
            c[4] = ForgeDirection.SOUTH;
        else c[4] = null;
        if (this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe)
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

}
