package uk.co.zebcoding.zebtech.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumExciter;

public class MessageTileEntityZechoriumExciter implements IMessage, IMessageHandler<MessageTileEntityZechoriumExciter, IMessage> {
    public int x, y, z;
    public int stored;

    public MessageTileEntityZechoriumExciter() {
    }

    public MessageTileEntityZechoriumExciter(TileEntityZechoriumExciter tileEntity) {
        this.x = tileEntity.xCoord;
        this.y = tileEntity.yCoord;
        this.z = tileEntity.zCoord;
        this.stored = tileEntity.stored;
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
    public IMessage onMessage(MessageTileEntityZechoriumExciter message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityZechoriumExciter) {
            ((TileEntityZechoriumExciter) tileEntity).stored = message.stored;
        }

        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}