package uk.co.zebcoding.zebtech.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.items.ZItems;

public class CraftingHandler {
    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {
        for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
            if (event.craftMatrix.getStackInSlot(i) != null) {
                ItemStack item0 = event.craftMatrix.getStackInSlot(i);
                if (item0 != null && item0.getItem() == ZItems.ironHammer) {
                    ItemStack k = new ItemStack(ZItems.ironHammer, 2,
                            (item0.getItemDamage() + 1));

                    if (k.getItemDamage() >= k.getMaxDamage()) {
                        k.stackSize--;
                    }
                    event.craftMatrix.setInventorySlotContents(i, k);
                }

                ItemStack item1 = event.craftMatrix.getStackInSlot(i);
                if (item1 != null && item1.getItem() == ZItems.solderWire) {
                    ItemStack k = new ItemStack(ZItems.solderWire, 2,
                            (item1.getItemDamage() + 1));

                    if (k.getItemDamage() >= k.getMaxDamage()) {
                        k.stackSize--;
                    }
                    event.craftMatrix.setInventorySlotContents(i, k);
                }
            }
        }
    }

}
