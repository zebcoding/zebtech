package uk.co.zebcoding.zebtech.creative;

import net.minecraft.creativetab.CreativeTabs;

public class ZebTab {
    public static CreativeTabs tabZeb;

    public static void init() {
        tabZeb = new CreativeTabZeb("TabZeb");
    }
}
