package uk.co.zebcoding.zebtech.crafting;

import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.items.ZItems;

public class AlloySmelterRecipes {

    public static ItemStack getSmeltingResult(ItemStack var1, ItemStack var2) {
        if (var1.getItem() == ZItems.tinIngot
                && var2.getItem() == ZItems.leadIngot)
            return new ItemStack(ZItems.solderLump, 2);
        return null;
    }

}
