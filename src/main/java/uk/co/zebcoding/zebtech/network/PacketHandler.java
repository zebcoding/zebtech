package uk.co.zebcoding.zebtech.network;


import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.network.message.*;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityZechoriumExciter.class, MessageTileEntityZechoriumExciter.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityPipe.class, MessageTileEntityPipe.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityZechoriumInfuser.class, MessageTileEntityZechoriumInfuser.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityTank.class, MessageTileEntityTank.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityZechoriumFurnace.class, MessageTileEntityZechoriumFurnace.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityZechoriumCompressor.class, MessageTileEntityZechoriumCompressor.class, 5, Side.CLIENT);
    }
}
