package uk.co.zebcoding.zebtech.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.items.ZItems;

public class CreativeTabZeb extends CreativeTabs {

    public CreativeTabZeb(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ZItems.zechoriumCrystal;
    }

}
