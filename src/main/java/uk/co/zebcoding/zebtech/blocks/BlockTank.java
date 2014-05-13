package uk.co.zebcoding.zebtech.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.tileentity.TileEntityTank;

import java.util.Random;

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

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        float f = (float) p_149734_2_ + 0.5F;
        float f1 = (float) p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
        float f2 = (float) p_149734_4_ + 0.5F;
        float f3 = 0.52F;
        float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

        p_149734_1_.spawnParticle("portal", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("portal", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("portal", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("portal", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
    }
}