package uk.co.zebcoding.zebtech.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.items.ZItems;

/**
 * Created by Charlotte on 21/05/2014.
 */
public class SlotCompressor extends Slot {

    public SlotCompressor (IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid (ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ZItems.pressurisedContainer;
    }

    @Override
    public int getSlotStackLimit () {
        return 1;
    }
}
