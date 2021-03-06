package uk.co.zebcoding.zebtech.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import uk.co.zebcoding.zebtech.blocks.BlockAlloySmelterBasic;
import uk.co.zebcoding.zebtech.crafting.AlloySmelterRecipes;

public class TileEntityAlloySmelterBasic extends TileEntity implements
        ISidedInventory {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};

    private ItemStack[] furnaceItemStacks = new ItemStack[4];

    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    private String furnaceName;

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
                : "Alloy Smelter (Basic)";
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
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[2]);

        if (tagCompound.hasKey("CustomName", 8)) {
            this.furnaceName = tagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("BurnTime", (short) this.burnTime);
        tagCompound.setShort("CookTime", (short) this.burnTime);
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

        if (this.hasCustomInventoryName()) {
            tagCompound.setString("CustomName", this.furnaceName);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return this.cookTime * par1 / 150;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 150;
        }

        return this.burnTime * par1 / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.burnTime > 0) {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.furnaceItemStacks[2]);

                if (this.burnTime > 0) {
                    flag1 = true;
                    if (this.furnaceItemStacks[2] != null) {
                        --this.furnaceItemStacks[2].stackSize;

                        if (this.furnaceItemStacks[2].stackSize == 0) {
                            this.furnaceItemStacks[2] = furnaceItemStacks[2]
                                    .getItem().getContainerItem(
                                            this.furnaceItemStacks[2]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.cookTime;
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
            BlockAlloySmelterBasic.updateAlloySmelterBasicBlockState(
                    this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord,
                    this.zCoord);
        }

        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null
                || this.furnaceItemStacks[1] == null) {
            return false;
        } else {
            ItemStack itemstack = AlloySmelterRecipes.getSmeltingResult(
                    this.furnaceItemStacks[0], this.furnaceItemStacks[1]);
            if (itemstack == null)
                return false;
            if (this.furnaceItemStacks[3] == null)
                return true;
            if (!this.furnaceItemStacks[3].isItemEqual(itemstack))
                return false;
            int result = furnaceItemStacks[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit()
                    && result <= this.furnaceItemStacks[3].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = AlloySmelterRecipes.getSmeltingResult(
                    this.furnaceItemStacks[0], this.furnaceItemStacks[1]);

            if (this.furnaceItemStacks[3] == null) {
                this.furnaceItemStacks[3] = itemstack.copy();
            } else if (this.furnaceItemStacks[3].getItem() == itemstack
                    .getItem()) {
                this.furnaceItemStacks[3].stackSize += itemstack.stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;
            --this.furnaceItemStacks[1].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0) {
                this.furnaceItemStacks[0] = null;
            }

            if (this.furnaceItemStacks[1].stackSize <= 0) {
                this.furnaceItemStacks[1] = null;
            }
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

                if (block == Blocks.coal_block) {
                    return 5400;
                }
            }

            if (item == Items.coal)
                return 600;
            return 0;
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

}
