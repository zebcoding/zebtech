package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityTank;

public class TileEntityTank extends TileEntityUsesZechorium {

    public int stored;

    public TileEntityTank() {
        super(10000);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        this.tank.readFromNBT(tagCompound);

        this.stored = tagCompound.getInteger("Stored");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        this.tank.writeToNBT(tagCompound);

        tagCompound.setInteger("Stored", this.stored);
    }

    /**
     * Gets level in tank.
     *
     * @return Tank level, from 0 - 1.
     */
    @SideOnly(Side.CLIENT)
    public float getLiquidLevel() {
        return (float) this.stored / (float) this.tank.getCapacity();
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            if (this.stored != this.tank.getFluidAmount()) {
                this.stored = this.tank.getFluidAmount();
                this.markDirty();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTank(this));
    }

}
