package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.tileentity.TileEntityTank;

public class BlockTank extends BlockContainer {

    protected BlockTank() {
        super(Material.ground);
        setBlockName("blockTank");
        setCreativeTab(ZebTab.tabZeb);

        setLightLevel(8);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTank();
    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}