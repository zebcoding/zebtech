package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemSolderWire extends Item {
    public ItemSolderWire() {
        super();
        setUnlocalizedName("solderWire");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        this.maxStackSize = 1;
        this.setMaxDamage(16);
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
