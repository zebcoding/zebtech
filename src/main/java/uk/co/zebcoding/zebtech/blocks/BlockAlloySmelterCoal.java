package uk.co.zebcoding.zebtech.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.ZebTech;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.tileentity.TileEntityAlloySmelterCoal;

import java.util.Random;

public class BlockAlloySmelterCoal extends BlockContainer {

    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public static boolean keepInventory;

    public BlockAlloySmelterCoal(boolean isActive) {
        super(Material.rock);

        this.isActive = isActive;

        if (this.isActive) {
            setBlockName("alloyFurnaceCoalActive");
            setLightLevel(0.625F);
        } else {
            setBlockName("alloyFurnaceCoalIdle");
            setCreativeTab(ZebTab.tabZeb);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MODID + ":"
                + "alloySmelterCoalSide");
        this.iconFront = iconRegister.registerIcon(Reference.MODID
                + ":"
                + (this.isActive ? "alloySmelterCoalFrontActive"
                : "alloySmelterCoalFrontIdle"));
        this.iconTop = iconRegister.registerIcon(Reference.MODID + ":"
                + "alloySmelterCoalTop");

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta == 0 && side == 3 ? this.iconFront : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop
                : (side != meta ? this.blockIcon : this.iconFront)));
    }

    public Item getItemDropped(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ZBlocks.alloySmelterCoalIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);

            byte b0 = 3;

            if (b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }

            if (b2.func_149730_j() && !b1.func_149730_j()) {
                b0 = 2;
            }

            if (b3.func_149730_j() && !b4.func_149730_j()) {
                b0 = 5;
            }

            if (b4.func_149730_j() && !b3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, x, b0, 2);
        }

    }

    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack itemstack) {
        int direction = MathHelper
                .floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (direction == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (direction == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (direction == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (direction == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemstack.hasDisplayName()) {
            ((TileEntityAlloySmelterCoal) world.getTileEntity(x, y, z))
                    .furnaceName(itemstack.getDisplayName());
        }
    }

    public static void updateBlockState(boolean burning, World world, int x,
                                        int y, int z) {
        int direction = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        burning = true;

        if (burning) {
            world.setBlock(x, y, z, ZBlocks.alloySmelterCoalActive);
        } else {
            world.setBlock(x, y, z, ZBlocks.alloySmelterCoalIdle);
        }

        burning = false;
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);

        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        player.openGui(ZebTech.instance, ZBlocks.guiIDAlloySmelterCoal, world,
                x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAlloySmelterCoal();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        if (this.isActive) {
            int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
            float f = (float) p_149734_2_ + 0.5F;
            float f1 = (float) p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = (float) p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            if (l == 4) {
                p_149734_1_.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 5) {
                p_149734_1_.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 2) {
                p_149734_1_.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
            } else if (l == 3) {
                p_149734_1_.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public static void updateAlloySmelterCoalBlockState(boolean active,
                                                        World worldObj, int xCoord, int yCoord, int zCoord) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        keepInventory = true;

        if (active)
            worldObj.setBlock(xCoord, yCoord, zCoord,
                    ZBlocks.alloySmelterCoalActive);
        else
            worldObj.setBlock(xCoord, yCoord, zCoord,
                    ZBlocks.alloySmelterCoalIdle);

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ZBlocks.alloySmelterCoalIdle);
    }
}
