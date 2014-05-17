package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import uk.co.zebcoding.zebtech.blocks.BlockZechoriumCompressor;
import uk.co.zebcoding.zebtech.blocks.BlockZechoriumInfuser;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.crafting.ZechoriumCompressorRecipes;
import uk.co.zebcoding.zebtech.crafting.ZechoriumInfuserRecipes;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityZechoriumCompressor;
import uk.co.zebcoding.zebtech.network.message.MessageTileEntityZechoriumInfuser;

/**
 * Created by Callum on 16/05/2014.
 */
public class TileEntityZechoriumCompressor extends TileEntityUsesZechorium implements
        ISidedInventory {
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};

    private ItemStack[] furnaceItemStacks = new ItemStack[2];

    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;
    public int stored;

    private String furnaceName;

    public TileEntityZechoriumCompressor() {
        super();
    }

    public void furnaceName(String string) {
        this.furnaceName = string;
    }

    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.furnaceItemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.furnaceItemStacks[par1] != null) {
            ItemStack itemstack;
            if (this.furnaceItemStacks[par1].stackSize <= par2) {
                itemstack = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.furnaceItemStacks[par1].splitStack(par2);

                if (this.furnaceItemStacks[par1].stackSize == 0) {
                    this.furnaceItemStacks[par1] = null;
                }
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.furnaceItemStacks[slot] != null) {
            ItemStack itemstack = this.furnaceItemStacks[slot];
            this.furnaceItemStacks[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.furnaceItemStacks[slot] = itemstack;

        if (itemstack != null
                && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.furnaceName
                : "Zechroium Compressor";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.furnaceName != null && this.furnaceName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tabCompound1 = tagList.getCompoundTagAt(i);
            byte byte0 = tabCompound1.getByte("Slot");

            if (byte0 >= 0 && byte0 < this.furnaceItemStacks.length) {
                this.furnaceItemStacks[byte0] = ItemStack
                        .loadItemStackFromNBT(tabCompound1);
            }
        }

        this.burnTime = tagCompound.getShort("BurnTime");
        this.cookTime = tagCompound.getShort("CookTime");
        this.currentItemBurnTime = tagCompound.getShort("CurrentBurnTime");

        this.tank.readFromNBT(tagCompound);

        this.stored = tagCompound.getInteger("Stored");

        if (tagCompound.hasKey("CustomName", 8)) {
            this.furnaceName = tagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        tagCompound.setShort("BurnTime", (short) this.burnTime);
        tagCompound.setShort("CookTime", (short) this.cookTime);
        tagCompound.setShort("CurrentBurnTime", (short) this.currentItemBurnTime);

        this.tank.writeToNBT(tagCompound);

        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] != null) {
                NBTTagCompound tagCompound1 = new NBTTagCompound();
                tagCompound1.setByte("Slot", (byte) i);
                this.furnaceItemStacks[i].writeToNBT(tagCompound1);
                tagList.appendTag(tagCompound1);
            }
        }

        tagCompound.setTag("Items", tagList);

        tagCompound.setInteger("Stored", this.stored);

        if (this.hasCustomInventoryName()) {
            tagCompound.setString("CustomName", this.furnaceName);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return this.cookTime * par1 / 150;
    }

    @SideOnly(Side.CLIENT)
    public int getLiquidLevelScaled(int par1) {
        int x = this.stored * par1 / this.tank.getCapacity();
        return x;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void updateEntity() {
        boolean flag = isBurning();
        boolean flag1 = false;

        if (flag) {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.stored != this.tank.getFluidAmount()) {
                this.stored = this.tank.getFluidAmount();
                flag1 = true;
            }

            if (this.burnTime == 0 && this.canSmelt()) {
                if (this.tank.getFluidAmount() >= 250)
                    this.currentItemBurnTime = this.burnTime = 150;
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.cookTime;
                this.tank.drain(1, true);
                if (this.cookTime == 150) {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }
        }

        if (flag != this.burnTime > 0) {
            flag1 = true;
            BlockZechoriumCompressor.updateBlockState(
                    this.burnTime > 0 ? ZBlocks.zechoriumCompressorActive : ZBlocks.zechoriumCompressorIdle, this.worldObj, this.xCoord, this.yCoord,
                    this.zCoord);
        }

        if (flag1) {
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = ZechoriumCompressorRecipes
                    .getSmeltingResult(this.furnaceItemStacks[0]);
            if (itemstack == null)
                return false;
            if (this.furnaceItemStacks[1] == null)
                return true;
            if (!this.furnaceItemStacks[1].isItemEqual(itemstack))
                return false;
            int result = furnaceItemStacks[1].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[1].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = ZechoriumCompressorRecipes.getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[1] == null) {
                this.furnaceItemStacks[1] = itemstack.copy();
            } else if (this.furnaceItemStacks[1].isItemEqual(itemstack)) {
                this.furnaceItemStacks[1].stackSize += itemstack.stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0) {
                this.furnaceItemStacks[0] = null;
            }

            this.tank.drain(100, true);
        }
    }

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock
                    && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 150;
                }

                if (block.getMaterial() == Material.wood) {
                    return 300;
                }

                if (block == Blocks.coal_block) {
                    return 16000;
                }
            }

            if (item instanceof ItemTool
                    && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemSword
                    && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemHoe
                    && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item == Items.stick)
                return 100;
            if (item == Items.coal)
                return 1600;
            if (item == Items.lava_bucket)
                return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling))
                return 100;
            if (item == Items.blaze_rod)
                return 2400;
            return GameRegistry.getFuelValue(itemstack);
        }
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return getItemBurnTime(itemstack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
                this.zCoord) != this ? false : player.getDistanceSq(
                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack itemstack) {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(itemstack) : true);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack itemstack, int par3) {
        return this.isItemValidForSlot(par1, itemstack);
    }

    @Override
    public boolean canExtractItem(int par1, ItemStack itemstack, int par3) {
        return par3 != 0 || par1 != 1 || itemstack.getItem() == Items.bucket;
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityZechoriumCompressor(this));
    }
}
