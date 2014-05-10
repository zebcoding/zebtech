package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.tileentity.TileEntityPipe;

public class BlockPipe extends BlockContainer {

    protected BlockPipe() {
        super(Material.ground);
        setBlockName("blockPipe");
        setCreativeTab(ZebTab.tabZeb);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPipe();
    }
}
