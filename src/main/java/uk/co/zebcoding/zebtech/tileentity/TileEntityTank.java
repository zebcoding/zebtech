package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import uk.co.zebcoding.zebtech.blocks.BlockTank;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityTank;

public class TileEntityTank extends TileEntityUsesZechorium {

    public int stored;
    BlockTank block;

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
        updateConnectionsNoExciter();
        balanceLiquid();

        if (!this.worldObj.isRemote) {
            if (this.stored != this.tank.getFluidAmount()) {
                this.stored = this.tank.getFluidAmount();
                this.markDirty();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }

        if (block == null)
            block = (BlockTank) worldObj.getBlock(xCoord, yCoord, zCoord);
        block.stored = stored;
    }

    public void balanceLiquid() {
        TileEntityProvidesZechorium current = this;
        TileEntityTank producer = this;
        TileEntityUsesZechorium consumer = null;
        boolean updated = true;
        int prev = 10;
        while (consumer == null) {
            for (int i = 0; i < current.c.length; i++) {
                if (current.c[i] != null && i != getOppSide(prev)) {
                    TileEntity te = null;

                    if (i == 0) {
                        te = this.worldObj.getTileEntity(current.xCoord + 0, current.yCoord + 1, current.zCoord + 0);

                    } else if (i == 1) {
                        te = this.worldObj.getTileEntity(current.xCoord + 0, current.yCoord - 1, current.zCoord + 0);

                    } else if (i == 2) {
                        te = this.worldObj.getTileEntity(current.xCoord + 0, current.yCoord + 0, current.zCoord - 1);

                    } else if (i == 3) {
                        te = this.worldObj.getTileEntity(current.xCoord + 1, current.yCoord + 0, current.zCoord + 0);

                    } else if (i == 4) {
                        te = this.worldObj.getTileEntity(current.xCoord + 0, current.yCoord + 0, current.zCoord + 1);

                    } else if (i == 5) {
                        te = this.worldObj.getTileEntity(current.xCoord - 1, current.yCoord + 0, current.zCoord + 0);
                    }
                    if (te instanceof TileEntityUsesZechorium && !(te instanceof TileEntityZechoriumExciter)) {
                        consumer = (TileEntityUsesZechorium) te;
                        updated = true;
                    } else if (te instanceof TileEntityPipe) {
                        current = (TileEntityProvidesZechorium) te;
                        updated = true;
                    }
                    prev = i;
                }
            }
            if (!updated) return;
            updated = false;
        }

        FluidStack stack = producer.tank.drain(producer.tank.getCapacity(), false);
        int canDrain;
        if (stack != null) {
            canDrain = stack.amount;
        } else {
            canDrain = 0;
        }
        int canFill = consumer.tank.fill(new FluidStack(ZFluids.liquidZechorium, consumer.tank.getCapacity()), false);

        if (!(canDrain == 0 || canFill == 0)) {
            if (canDrain > canFill) {
                producer.tank.drain(canFill, true);
                consumer.tank.fill(new FluidStack(ZFluids.liquidZechorium, canFill), true);
            } else {
                producer.tank.drain(canDrain, true);
                consumer.tank.fill(new FluidStack(ZFluids.liquidZechorium, canDrain), true);
            }
        }
    }


    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTank(this));
    }

}
