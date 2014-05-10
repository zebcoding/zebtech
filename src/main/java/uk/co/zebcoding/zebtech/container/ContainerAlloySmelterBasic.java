package uk.co.zebcoding.zebtech.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.items.ZItems;
import uk.co.zebcoding.zebtech.tileentity.TileEntityAlloySmelterBasic;

public class ContainerAlloySmelterBasic extends Container {

    public static final int INPUT[] = {0, 1};

    public static final int METAL_SLOTS = INPUT.length, FUEL = METAL_SLOTS + 1,
            RECIPE = FUEL, OUTPUT = RECIPE + 1, INV_START = OUTPUT + 1,
            INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
            HOTBAR_END = HOTBAR_START + 8;

    private TileEntityAlloySmelterBasic alloySmelterBasic;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerAlloySmelterBasic(InventoryPlayer player,
                                      TileEntityAlloySmelterBasic tileEntityFurnace) {
        this.alloySmelterBasic = tileEntityFurnace;
        this.addSlotToContainer(new Slot(tileEntityFurnace, 0, 45, 17));
        this.addSlotToContainer(new Slot(tileEntityFurnace, 1, 67, 17));
        this.addSlotToContainer(new Slot(tileEntityFurnace, 2, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player.player,
                tileEntityFurnace, 3, 116, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting craft) {
        super.addCraftingToCrafters(craft);
        craft.sendProgressBarUpdate(this, 0, this.alloySmelterBasic.cookTime);
        craft.sendProgressBarUpdate(this, 2, this.alloySmelterBasic.burnTime);
        craft.sendProgressBarUpdate(this, 3,
                this.alloySmelterBasic.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting craft = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.alloySmelterBasic.cookTime) {
                craft.sendProgressBarUpdate(this, 0,
                        this.alloySmelterBasic.cookTime);
            }

            if (this.lastBurnTime != this.alloySmelterBasic.burnTime) {
                craft.sendProgressBarUpdate(this, 2,
                        this.alloySmelterBasic.burnTime);
            }

            if (this.lastItemBurnTime != this.alloySmelterBasic.currentItemBurnTime) {
                craft.sendProgressBarUpdate(this, 3,
                        this.alloySmelterBasic.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.alloySmelterBasic.burnTime;
        this.lastCookTime = this.alloySmelterBasic.cookTime;
        this.lastItemBurnTime = this.alloySmelterBasic.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.alloySmelterBasic.cookTime = par2;
        }

        if (par1 == 2) {
            this.alloySmelterBasic.burnTime = par2;
        }

        if (par1 == 3) {
            this.alloySmelterBasic.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.alloySmelterBasic.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            Item item = itemstack1.getItem();

            // If item is in TileEntity inventory
            if (par2 < INV_START) {
                // try to place in player inventory / action bar
                if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END,
                        true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // Item is in player inventory, try to place in inscriber
            else if (par2 > OUTPUT) {
                // if it is tin, place in the first input slot
                if (itemstack1.getItem() == ZItems.tinIngot) {
                    if (!this.mergeItemStack(itemstack1, INPUT[0],
                            INPUT[0] + 1, false)) {
                        return null;
                    }
                }
                // if it is lead, place in the second input slot
                else if (itemstack1.getItem() == ZItems.leadIngot) {
                    if (!this.mergeItemStack(itemstack1, INPUT[1],
                            INPUT[1] + 1, false)) {
                        return null;
                    }
                }
                // if it is coal, place in fuel slot
                else if (itemstack1.getItem() == Items.coal) {
                    if (!this.mergeItemStack(itemstack1, FUEL - 1, FUEL, false)) {
                        return null;
                    }
                }
                // if it is a coal block, place in fuel slot
                else if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                    Block block = Block.getBlockFromItem(item);
                    if (block == Blocks.coal_block) {
                        if (!this.mergeItemStack(itemstack1, FUEL - 1, FUEL, false)) {
                            return null;
                        }
                    }
                }

                // item in player's inventory, but not in action bar
                else if (par2 >= INV_START - 1 && par2 < HOTBAR_START - 1) {
                    // place in action bar
                    if (!this.mergeItemStack(itemstack1, HOTBAR_START - 1,
                            HOTBAR_END, false)) {
                        return null;
                    }
                }
                // item in action bar - place in player inventory
                else if (par2 >= HOTBAR_START - 1
                        && par2 < HOTBAR_END
                        && !this.mergeItemStack(itemstack1, INV_START - 1,
                        HOTBAR_START - 1, false)) {
                    return null;
                }
            }
            // In one of the smelter's slots; try to place in player inventory /
            // action bar
            else if (!this.mergeItemStack(itemstack1, INV_START - 1,
                    HOTBAR_END, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}
