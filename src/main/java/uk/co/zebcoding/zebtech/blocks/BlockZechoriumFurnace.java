package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumFurnace;

public class BlockZechoriumFurnace extends ZMachine {
    BlockZechoriumFurnace(boolean isActive) {
        super(isActive, "zechoriumFurnace", 2, Item.getItemFromBlock(ZBlocks.zechoriumFurnaceIdle),
                TileEntityZechoriumFurnace.class, ZBlocks.guiIDZechoriumFurnace);
    }
}
