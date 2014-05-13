package uk.co.zebcoding.zebtech.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import uk.co.zebcoding.zebtech.tileentity.TileEntityPipe;

public class MessageTileEntityPipe implements IMessage, IMessageHandler<MessageTileEntityPipe, IMessage> {
    public int x, y, z;
    public int stored;
    public String fluid;

    public MessageTileEntityPipe() {
    }

    public MessageTileEntityPipe(TileEntityPipe tileEntity) {
        this.x = tileEntity.xCoord;
        this.y = tileEntity.yCoord;
        this.z = tileEntity.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public IMessage onMessage(MessageTileEntityPipe message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityPipe) {
        }

        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}