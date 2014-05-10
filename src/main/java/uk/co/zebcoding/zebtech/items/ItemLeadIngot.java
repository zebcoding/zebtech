package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemLeadIngot extends Item {
    public ItemLeadIngot() {
        super();
        setUnlocalizedName("leadIngot");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
    }
}
