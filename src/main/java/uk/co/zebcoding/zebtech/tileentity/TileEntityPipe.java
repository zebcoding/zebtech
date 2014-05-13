package uk.co.zebcoding.zebtech.tileentity;

import net.minecraft.network.Packet;
import net.minecraftforge.common.util.ForgeDirection;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityPipe;

public class TileEntityPipe extends TileEntityProvidesZechorium {

    /**
     * Machine connections, UP, DOWN, NORTH, EAST, SOUTH, WEST.
     */
    public ForgeDirection[] mc = new ForgeDirection[6];
    /**
     * The pipes storage.
     */

    public TileEntityPipe() {
        super();
    }

    public void updateEntity() {
        updateConnections();
        this.updateMachineConnections();
    }

    /**
     * Gets all adjacent machines.
     */
    public void updateMachineConnections() {
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            mc[0] = ForgeDirection.UP;
        else mc[0] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            mc[1] = ForgeDirection.DOWN;
        else mc[1] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityUsesZechorium)
            mc[2] = ForgeDirection.NORTH;
        else mc[2] = null;
        if (this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            mc[3] = ForgeDirection.EAST;
        else mc[3] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityUsesZechorium)
            mc[4] = ForgeDirection.SOUTH;
        else mc[4] = null;
        if (this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            mc[5] = ForgeDirection.WEST;
        else mc[5] = null;
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

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityPipe(this));
    }

}
