package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemCopperWire extends Item {
    public ItemCopperWire() {
        super();
        setUnlocalizedName("copperWire");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        this.maxStackSize = 16;
    }
}
