package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.zebcoding.zebtech.help.Reference;

public class ZTileEntity {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityAlloySmelterCoal.class, "alloySmelterCoal");
        GameRegistry.registerTileEntity(TileEntityAlloySmelterBasic.class, "alloySmelterBasic");
        GameRegistry.registerTileEntity(TileEntityRockPummelerBasic.class, "rockPummelerBasic");
        GameRegistry.registerTileEntity(TileEntityZechoriumExciter.class, "zechoriumExciter");
        GameRegistry.registerTileEntity(TileEntityZechoriumInfuser.class, "zechoriumInfuser");
        GameRegistry.registerTileEntity(TileEntityZechoriumCompressor.class, Reference.MODID + ":" + "zechoriumCompressor");
        GameRegistry.registerTileEntity(TileEntityPipe.class, "pipe");
        GameRegistry.registerTileEntity(TileEntityTank.class, "tank");
    }
}
