package uk.co.zebcoding.zebtech.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.items.ZItems;

public class ZechoriumExciterRecipes {

    public static FluidStack getSmeltingResult(ItemStack var1) {
        Item item = var1.getItem();

        return item == ZItems.zechoriumCrystal ? new FluidStack(ZFluids.liquidZechorium, 1000) : null;
    }

}
