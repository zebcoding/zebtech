package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemSolderingIron extends Item {
    public ItemSolderingIron() {
        super();
        setUnlocalizedName("solderingIron");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        this.maxStackSize = 1;
        this.setMaxDamage(64);
        this.setNoRepair();
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack) {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack cStack = itemStack.copy();
        cStack.setItemDamage(cStack.getItemDamage() + 1);
        cStack.stackSize = 1;
        return cStack;
    }
}
