package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;

public class ZTileEntity {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityAlloySmelterCoal.class, "alloySmelterCoal");
        GameRegistry.registerTileEntity(TileEntityAlloySmelterBasic.class, "alloySmelterBasic");
        GameRegistry.registerTileEntity(TileEntityRockPummelerBasic.class, "rockPummelerBasic");
        GameRegistry.registerTileEntity(TileEntityZechoriumExciter.class, "zechoriumExciter");
        GameRegistry.registerTileEntity(TileEntityZechoriumInfuser.class, "zechoriumInfuser");
        GameRegistry.registerTileEntity(TileEntityPipe.class, "pipe");
    }
}
