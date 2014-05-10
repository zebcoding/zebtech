package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemPcbBasic extends Item {
    public ItemPcbBasic() {
        super();
        setUnlocalizedName("pcbBasic");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
    }
}
