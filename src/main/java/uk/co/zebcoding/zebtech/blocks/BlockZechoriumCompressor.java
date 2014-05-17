package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumCompressor;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumFurnace;

public class BlockZechoriumCompressor extends ZMachine {
    BlockZechoriumCompressor(boolean isActive) {
        super(isActive, "zechoriumCompressor", 2, Item.getItemFromBlock(ZBlocks.zechoriumCompressorIdle),
                TileEntityZechoriumCompressor.class, ZBlocks.guiIDZechoriumCompressor);
    }
}
