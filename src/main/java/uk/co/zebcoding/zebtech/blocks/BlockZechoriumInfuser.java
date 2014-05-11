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
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumInfuser;

public class BlockZechoriumInfuser extends BlockContainer {

    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    public static boolean keepInventory;

    public BlockZechoriumInfuser(boolean isActive) {
        super(Material.rock);

        this.isActive = isActive;

        if (this.isActive) {
            setBlockName("zechoriumInfuserActive");
            setLightLevel(0.625F);
        } else {
            setBlockName("zechoriumInfuserIdle");
            setCreativeTab(ZebTab.tabZeb);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MODID + ":"
                + "basicMachineSide");
        this.iconFront = iconRegister.registerIcon(Reference.MODID
                + ":"
                + (this.isActive ? "alloySmelterBasicFrontActive"
                : "alloySmelterBasicFrontIdle"));

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta == 0 && side == 3 ? this.iconFront : (side != meta ? this.blockIcon : this.iconFront);
    }

    public Item getItemDropped(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ZBlocks.zechoriumInfuserIdle);
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
            ((TileEntityZechoriumInfuser) world.getTileEntity(x, y, z))
                    .furnaceName(itemstack.getDisplayName());
        }
    }

    public static void updateBlockState(boolean burning, World world, int x,
                                        int y, int z) {
        int direction = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        burning = true;

        if (burning) {
            world.setBlock(x, y, z, ZBlocks.zechoriumInfuserActive);
        } else {
            world.setBlock(x, y, z, ZBlocks.zechoriumInfuserIdle);
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
        player.openGui(ZebTech.instance, ZBlocks.guiIDZechoriumInfuser, world,
                x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityZechoriumInfuser();
    }

    // TODO randomDisplayTick

    public static void updateZechoriumInfuserBlockState(boolean active,
                                                        World worldObj, int xCoord, int yCoord, int zCoord) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        keepInventory = true;

        if (active)
            worldObj.setBlock(xCoord, yCoord, zCoord,
                    ZBlocks.zechoriumInfuserActive);
        else
            worldObj.setBlock(xCoord, yCoord, zCoord,
                    ZBlocks.zechoriumInfuserIdle);

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ZBlocks.zechoriumInfuserIdle);
    }
}
