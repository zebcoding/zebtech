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

import java.util.Random;

public class ZMachine extends BlockContainer {

    public static boolean keepInventory;
    private final boolean isActive;
    @SideOnly (Side.CLIENT)
    private IIcon iconFront;
    private int level;
    private String machineName;
    private Item item;
    private Class<? extends TileEntity> te;
    private int guiID;

    public ZMachine (boolean isActive, String machineName, int level, Item item, Class<? extends TileEntity> te, int guiID) {
        super(Material.rock);

        this.isActive = isActive;
        this.level = level;
        this.machineName = machineName;
        this.item = item;
        this.te = te;
        if (this.te != null) System.out.println(this.te);
        this.guiID = guiID;

        setHardness(3.5F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 1);

        if (this.isActive) {
            setBlockName(machineName + "Active");
            setLightLevel(0.625F);
        } else {
            setBlockName(machineName + "Idle");
            setCreativeTab(ZebTab.tabZeb);
        }
    }

    public static void updateBlockState (Block block, World worldObj, int x, int y, int z) {
        int i = worldObj.getBlockMetadata(x, y, z);

        TileEntity tileentity = worldObj.getTileEntity(x, y, z);

        keepInventory = true;

        worldObj.setBlock(x, y, z, block);

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(x, y, z, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(x, y, z, tileentity);
        }
    }

    @SideOnly (Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MODID + ":"
            + (level == 1 ? "basicMachineSide" : "advancedMachineSide"));
        this.iconFront = iconRegister.registerIcon(Reference.MODID + ":"
            + (this.isActive ? machineName + "FrontActive"
            : machineName + "FrontIdle"));

    }

    @SideOnly (Side.CLIENT)
    public IIcon getIcon (int side, int meta) {
        return meta == 0 && side == 3 ? this.iconFront : (side != meta ? this.blockIcon : this.iconFront);
    }

    public Item getItemDropped (int i, Random r, int j) {
        return item;
    }

    public void onBlockAdded (World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection (World world, int x, int y, int z) {
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

    public void onBlockPlacedBy (World world, int x, int y, int z,
                                 EntityLivingBase entity, ItemStack itemstack) {
        int direction = MathHelper
            .floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

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
    }

    public boolean onBlockActivated (World world, int x, int y, int z,
                                     EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        player.openGui(ZebTech.instance, guiID, world,
            x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity (World var1, int var2) {
        try {
            final TileEntity entity = te.getConstructor(new Class[0]).newInstance();
            return entity;
        } catch (Exception exp) {
            System.out.println("Tile entity creation failed: " + exp);
            return null;
        }
    }

    @SideOnly (Side.CLIENT)
    public void randomDisplayTick (World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        if (this.isActive) {
            int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
            float f = (float)p_149734_2_ + 0.5F;
            float f1 = (float)p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            if (l == 4) {
                p_149734_1_.spawnParticle("portal", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 5) {
                p_149734_1_.spawnParticle("portal", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 2) {
                p_149734_1_.spawnParticle("portal", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            } else if (l == 3) {
                p_149734_1_.spawnParticle("portal", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly (Side.CLIENT)
    public Item getItem (World world, int x, int y, int z) {
        return item;
    }
}
