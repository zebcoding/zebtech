package uk.co.zebcoding.zebtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityProvidesZechorium extends TileEntity {

    /**
     * Connections, UP, DOWN, NORTH, EAST, SOUTH, WEST.
     */
    public ForgeDirection[] c = new ForgeDirection[6];

    TileEntityProvidesZechorium() {
        super();
    }

    /**
     * Gets all adjacent pipes.
     */
    public void updateConnections() {
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[0] = ForgeDirection.UP;
        else this.c[0] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[1] = ForgeDirection.DOWN;
        else this.c[1] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityUsesZechorium)
            this.c[2] = ForgeDirection.NORTH;
        else this.c[2] = null;
        if (this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[3] = ForgeDirection.EAST;
        else this.c[3] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityUsesZechorium)
            this.c[4] = ForgeDirection.SOUTH;
        else this.c[4] = null;
        if (this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityZechoriumExciter ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[5] = ForgeDirection.WEST;
        else this.c[5] = null;
    }

    /**
     * Gets all adjacent connections w/o exciter.
     */
    public void updateConnectionsNoExciter() {
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[0] = ForgeDirection.UP;
        else this.c[0] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord - 1, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[1] = ForgeDirection.DOWN;
        else this.c[1] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord - 1) instanceof TileEntityUsesZechorium)
            this.c[2] = ForgeDirection.NORTH;
        else this.c[2] = null;
        if (this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[3] = ForgeDirection.EAST;
        else this.c[3] = null;
        if (this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord + 0, yCoord + 0, zCoord + 1) instanceof TileEntityUsesZechorium)
            this.c[4] = ForgeDirection.SOUTH;
        else this.c[4] = null;
        if (this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityPipe ||
                this.worldObj.getTileEntity(xCoord - 1, yCoord + 0, zCoord + 0) instanceof TileEntityUsesZechorium)
            this.c[5] = ForgeDirection.WEST;
        else this.c[5] = null;
    }

    public int getOppSide(int i) {
        switch (i) {
            case (0):
                return 1;
            case (1):
                return 0;
            case (2):
                return 4;
            case (3):
                return 5;
            case (4):
                return 2;
            case (5):
                return 3;
        }
        return 10;
    }


}
