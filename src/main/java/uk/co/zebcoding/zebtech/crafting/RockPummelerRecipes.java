package uk.co.zebcoding.zebtech.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.items.ZItems;

public class RockPummelerRecipes {

    public static ItemStack getSmeltingResult(ItemStack var1) {
        Item item = var1.getItem();

        if (item instanceof ItemBlock
                && Block.getBlockFromItem(item) != Blocks.air) {
            Block block = Block.getBlockFromItem(item);

            if (block == ZBlocks.tinOre)
                return new ItemStack(ZItems.tinDust, 2);
            if (block == ZBlocks.leadOre)
                return new ItemStack(ZItems.leadDust, 2);
            if (block == ZBlocks.copperOre)
                return new ItemStack(ZItems.copperDust, 2);
        }
        return null;
    }

}
