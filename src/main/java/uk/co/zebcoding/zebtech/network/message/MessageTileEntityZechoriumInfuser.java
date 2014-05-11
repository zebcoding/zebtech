package uk.co.zebcoding.zebtech.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumInfuser;

public class MessageTileEntityZechoriumInfuser implements IMessage, IMessageHandler<MessageTileEntityZechoriumInfuser, IMessage> {
    public int x, y, z;
    public int stored;
    public String fluid;

    public MessageTileEntityZechoriumInfuser() {
    }

    public MessageTileEntityZechoriumInfuser(TileEntityZechoriumInfuser tileEntity) {
        this.x = tileEntity.xCoord;
        this.y = tileEntity.yCoord;
        this.z = tileEntity.zCoord;
        this.stored = tileEntity.tank.getFluidAmount();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.stored = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(stored);
    }

    @Override
    public IMessage onMessage(MessageTileEntityZechoriumInfuser message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityZechoriumInfuser) {
            ((TileEntityZechoriumInfuser) tileEntity).stored = message.stored;
            ((TileEntityZechoriumInfuser) tileEntity).tank.setFluid(new FluidStack(ZFluids.liquidZechorium, message.stored));
        }

        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}