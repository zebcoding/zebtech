package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

public class ItemIronHammer extends Item {
    public ItemIronHammer() {
        super();
        setUnlocalizedName("ironHammer");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
        this.maxStackSize = 1;
        this.setMaxDamage(64);
        this.setNoRepair();
    }
}
