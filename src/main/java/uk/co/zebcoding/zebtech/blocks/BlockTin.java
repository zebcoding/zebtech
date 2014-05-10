package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class BlockTin extends Block {
    public BlockTin() {
        super(Material.rock);
        setBlockName("tinBlock");
        setBlockTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        setHardness(3.0F);
        setResistance(5.0F);
    }
}
