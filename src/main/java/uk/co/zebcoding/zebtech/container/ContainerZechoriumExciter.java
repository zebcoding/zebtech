package uk.co.zebcoding.zebtech.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.crafting.ZechoriumExciterRecipes;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumExciter;

public class ContainerZechoriumExciter extends Container {

    public static final int INPUT[] = {0};

    public static final int ORE_SLOTS = INPUT.length, FUEL = ORE_SLOTS + 1,
            RECIPE = FUEL, INV_START = RECIPE + 1,
            INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
            HOTBAR_END = HOTBAR_START + 8;

    private TileEntityZechoriumExciter zechoriumExciter;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerZechoriumExciter(InventoryPlayer player,
                                     TileEntityZechoriumExciter tileEntityFurnace) {
        this.zechoriumExciter = tileEntityFurnace;
        this.addSlotToContainer(new Slot(tileEntityFurnace, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityFurnace, 1, 56, 53));

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
        craft.sendProgressBarUpdate(this, 0, this.zechoriumExciter.cookTime);
        craft.sendProgressBarUpdate(this, 1, this.zechoriumExciter.burnTime);
        craft.sendProgressBarUpdate(this, 2,
                this.zechoriumExciter.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting craft = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.zechoriumExciter.cookTime) {
                craft.sendProgressBarUpdate(this, 0,
                        this.zechoriumExciter.cookTime);
            }

            if (this.lastBurnTime != this.zechoriumExciter.burnTime) {
                craft.sendProgressBarUpdate(this, 1,
                        this.zechoriumExciter.burnTime);
            }

            if (this.lastItemBurnTime != this.zechoriumExciter.currentItemBurnTime) {
                craft.sendProgressBarUpdate(this, 2,
                        this.zechoriumExciter.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.zechoriumExciter.burnTime;
        this.lastCookTime = this.zechoriumExciter.cookTime;
        this.lastItemBurnTime = this.zechoriumExciter.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.zechoriumExciter.cookTime = par2;
        }

        if (par1 == 1) {
            this.zechoriumExciter.burnTime = par2;
        }

        if (par1 == 2) {
            this.zechoriumExciter.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.zechoriumExciter.isUseableByPlayer(player);
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
                if (ZechoriumExciterRecipes.getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityZechoriumExciter.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 2 && par2 < 29) {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                        return null;
                    }
                } else if (par2 >= 29 && par2 < 38 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
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
