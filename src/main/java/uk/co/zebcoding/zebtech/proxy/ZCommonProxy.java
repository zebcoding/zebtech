package uk.co.zebcoding.zebtech.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.container.ContainerAlloySmelterBasic;
import uk.co.zebcoding.zebtech.container.ContainerAlloySmelterCoal;
import uk.co.zebcoding.zebtech.container.ContainerRockPummelerBasic;
import uk.co.zebcoding.zebtech.container.ContainerZechoriumExciter;
import uk.co.zebcoding.zebtech.tileentity.TileEntityAlloySmelterBasic;
import uk.co.zebcoding.zebtech.tileentity.TileEntityAlloySmelterCoal;
import uk.co.zebcoding.zebtech.tileentity.TileEntityRockPummelerBasic;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumExciter;

public class ZCommonProxy implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            switch (ID) {
                case ZBlocks.guiIDAlloySmelterCoal:
                    if (entity instanceof TileEntityAlloySmelterCoal) {
                        return new ContainerAlloySmelterCoal(player.inventory,
                                (TileEntityAlloySmelterCoal) entity);
                    }
                    return null;
                case ZBlocks.guiIDAlloySmelterBasic:
                    if (entity instanceof TileEntityAlloySmelterBasic) {
                        return new ContainerAlloySmelterBasic(player.inventory,
                                (TileEntityAlloySmelterBasic) entity);
                    }
                case ZBlocks.guiIDRockPummelerBasic:
                    if (entity instanceof TileEntityRockPummelerBasic) {
                        return new ContainerRockPummelerBasic(player.inventory,
                                (TileEntityRockPummelerBasic) entity);
                    }
                case ZBlocks.guiIDZechoriumExciter:
                    if (entity instanceof TileEntityZechoriumExciter) {
                        return new ContainerZechoriumExciter(player.inventory,
                                (TileEntityZechoriumExciter) entity);
                    }
            }
        }
        return null;
    }

    public World getClientWorld() {
        return null;
    }
}
