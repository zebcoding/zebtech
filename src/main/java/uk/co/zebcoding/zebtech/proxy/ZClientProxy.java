package uk.co.zebcoding.zebtech.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.gui.*;
import uk.co.zebcoding.zebtech.tileentity.*;
import uk.co.zebcoding.zebtech.tileentity.renderer.TileEntityRenderPipe;
import uk.co.zebcoding.zebtech.tileentity.renderer.TileEntityRenderPipeItem;
import uk.co.zebcoding.zebtech.tileentity.renderer.TileEntityRenderTank;
import uk.co.zebcoding.zebtech.tileentity.renderer.TileEntityRenderTankItem;

public class ZClientProxy extends ZCommonProxy {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null) {
            switch (ID) {
                case ZBlocks.guiIDAlloySmelterCoal:
                    if (entity instanceof TileEntityAlloySmelterCoal) {
                        return new GuiAlloySmelterCoal(player.inventory,
                                (TileEntityAlloySmelterCoal) entity);
                    }
                    return null;
                case ZBlocks.guiIDAlloySmelterBasic:
                    if (entity instanceof TileEntityAlloySmelterBasic) {
                        return new GuiAlloySmelterBasic(player.inventory,
                                (TileEntityAlloySmelterBasic) entity);
                    }
                case ZBlocks.guiIDRockPummelerBasic:
                    if (entity instanceof TileEntityRockPummelerBasic) {
                        return new GuiRockPummelerBasic(player.inventory,
                                (TileEntityRockPummelerBasic) entity);
                    }
                case ZBlocks.guiIDZechoriumExciter:
                    if (entity instanceof TileEntityZechoriumExciter) {
                        return new GuiZechoriumExciter(player.inventory,
                                (TileEntityZechoriumExciter) entity);
                    }
                case ZBlocks.guiIDZechoriumInfuser:
                    if (entity instanceof TileEntityZechoriumInfuser) {
                        return new GuiZechoriumInfuser(player.inventory,
                                (TileEntityZechoriumInfuser) entity);
                    }
            }
        }
        return null;
    }

    public static void init() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityRenderPipe());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new TileEntityRenderTank());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ZBlocks.pipe), new TileEntityRenderPipeItem());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ZBlocks.tank), new TileEntityRenderTankItem());
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

}