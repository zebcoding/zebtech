package uk.co.zebcoding.zebtech.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumFurnace;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumInfuser;

public class MessageTileEntityZechoriumFurnace implements IMessage, IMessageHandler<MessageTileEntityZechoriumFurnace, IMessage> {
    public int x, y, z;
    public int stored;
    public String fluid;

    public MessageTileEntityZechoriumFurnace() {
    }

    public MessageTileEntityZechoriumFurnace(TileEntityZechoriumFurnace tileEntity) {
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
    public IMessage onMessage(MessageTileEntityZechoriumFurnace message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityZechoriumFurnace) {
            ((TileEntityZechoriumFurnace) tileEntity).stored = message.stored;
            ((TileEntityZechoriumFurnace) tileEntity).tank.setFluid(new FluidStack(ZFluids.liquidZechorium, message.stored));
        }

        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}