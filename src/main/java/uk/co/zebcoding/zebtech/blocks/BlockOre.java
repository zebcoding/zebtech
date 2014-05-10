package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.items.ZItems;

import java.util.Random;

public class BlockOre extends Block {
    public BlockOre(String name) {
        super(Material.rock);
        setBlockName(name);
        setBlockTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        setHardness(3.0F);
        setResistance(5.0F);
        int level = (this == ZBlocks.zechoriumOre ? 3 : (this == ZBlocks.leadOre ? 2 : 1));
        setHarvestLevel("pickaxe", level);
    }

    public Item getItemDropped(int x, Random r, int y) {
        return this == ZBlocks.zechoriumOre ? ZItems.zechoriumCrystal : Item.getItemFromBlock(this);
    }

    public int quantityDropped(Random r) {
        return this == ZBlocks.zechoriumOre ? 2 + r.nextInt(3) : 1;
    }

    private Random rand = new Random();

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        return this == ZBlocks.zechoriumOre ? MathHelper.getRandomIntegerInRange(rand, 3, 7) : 0;
    }
}
