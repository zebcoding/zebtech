package uk.co.zebcoding.zebtech.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.crafting.RockPummelerRecipes;
import uk.co.zebcoding.zebtech.tileentity.TileEntityRockPummelerBasic;

public class ContainerRockPummelerBasic extends Container {

    public static final int INPUT[] = {0};

    public static final int ORE_SLOTS = INPUT.length, FUEL = ORE_SLOTS + 1,
            RECIPE = FUEL, OUTPUT = RECIPE + 1, INV_START = OUTPUT + 1,
            INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
            HOTBAR_END = HOTBAR_START + 8;

    private TileEntityRockPummelerBasic rockPummelerBasic;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerRockPummelerBasic(InventoryPlayer player,
                                      TileEntityRockPummelerBasic tileEntityFurnace) {
        this.rockPummelerBasic = tileEntityFurnace;
        this.addSlotToContainer(new Slot(tileEntityFurnace, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player.player,
                tileEntityFurnace, 2, 116, 35));

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
        craft.sendProgressBarUpdate(this, 0, this.rockPummelerBasic.cookTime);
        craft.sendProgressBarUpdate(this, 1, this.rockPummelerBasic.burnTime);
        craft.sendProgressBarUpdate(this, 2,
                this.rockPummelerBasic.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting craft = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.rockPummelerBasic.cookTime) {
                craft.sendProgressBarUpdate(this, 0,
                        this.rockPummelerBasic.cookTime);
            }

            if (this.lastBurnTime != this.rockPummelerBasic.burnTime) {
                craft.sendProgressBarUpdate(this, 1,
                        this.rockPummelerBasic.burnTime);
            }

            if (this.lastItemBurnTime != this.rockPummelerBasic.currentItemBurnTime) {
                craft.sendProgressBarUpdate(this, 2,
                        this.rockPummelerBasic.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.rockPummelerBasic.burnTime;
        this.lastCookTime = this.rockPummelerBasic.cookTime;
        this.lastItemBurnTime = this.rockPummelerBasic.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.rockPummelerBasic.cookTime = par2;
        }

        if (par1 == 1) {
            this.rockPummelerBasic.burnTime = par2;
        }

        if (par1 == 2) {
            this.rockPummelerBasic.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.rockPummelerBasic.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (RockPummelerRecipes.getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityRockPummelerBasic.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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
