package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class BlockInfusedIron extends Block{

    public BlockInfusedIron() {
        super(Material.rock);
        setBlockName("infusedIronBlock");
        setBlockTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        setLightLevel(8.0F);
        setHardness(3.0F);
        setResistance(5.0F);
    }
}
