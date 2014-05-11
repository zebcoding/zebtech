package uk.co.zebcoding.zebtech.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.items.ZItems;

public class ZechoriumInfuserRecipes {

    public static ItemStack getSmeltingResult(ItemStack var1) {
        Item item = var1.getItem();

        return item == Items.iron_ingot ? new ItemStack(ZItems.infusedIngot, 1) : null;
    }

}
