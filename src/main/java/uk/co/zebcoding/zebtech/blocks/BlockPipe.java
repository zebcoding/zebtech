package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.tileentity.TileEntityPipe;

public class BlockPipe extends BlockContainer {

    protected BlockPipe() {
        super(Material.ground);
        setBlockName("blockPipe");
        setCreativeTab(ZebTab.tabZeb);
        setHarvestLevel("pickaxe", 1);

        float p = 1.0F / 16.0F;
        setBlockBounds(0 + 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2);

        setLightLevel(8);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        TileEntityPipe pipe = (TileEntityPipe) world.getTileEntity(x, y, z);

        if (pipe != null) {
            float p = 1.0F / 16.0F;
            float s1 = 0 + 11 * p / 2;
            float s2 = 1 - 11 * p / 2;

            float minX = s1 - (pipe.c[5] != null ? s1 : 0);
            float minY = s1 - (pipe.c[1] != null ? s1 : 0);
            float minZ = s1 - (pipe.c[2] != null ? s1 : 0);
            float maxX = s2 + (pipe.c[3] != null ? s1 : 0);
            float maxY = s2 + (pipe.c[0] != null ? s1 : 0);
            float maxZ = s2 + (pipe.c[4] != null ? s1 : 0);

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        TileEntityPipe pipe = (TileEntityPipe) world.getTileEntity(x, y, z);

        if (pipe != null) {
            float p = 1.0F / 16.0F;
            float s1 = 0 + 11 * p / 2;
            float s2 = 1 - 11 * p / 2;

            float minX = s1 - (pipe.c[5] != null ? s1 : 0);
            float minY = s1 - (pipe.c[1] != null ? s1 : 0);
            float minZ = s1 - (pipe.c[2] != null ? s1 : 0);
            float maxX = s2 + (pipe.c[3] != null ? s1 : 0);
            float maxY = s2 + (pipe.c[0] != null ? s1 : 0);
            float maxZ = s2 + (pipe.c[4] != null ? s1 : 0);

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPipe();
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