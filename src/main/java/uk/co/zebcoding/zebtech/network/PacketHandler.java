package uk.co.zebcoding.zebtech.network;


import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityZechoriumExciter;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityZechoriumExciter.class, MessageTileEntityZechoriumExciter.class, 0, Side.CLIENT);
    }
}
