package uk.co.zebcoding.zebtech.items;

import net.minecraft.item.Item;
import uk.co.zebcoding.zebtech.help.RegisterHelper;

public class ZItems {
    public static Item tinIngot;
    public static Item leadIngot;
    public static Item copperIngot;
    public static Item infusedIngot;
    public static Item zechoriumCrystal;
    public static Item tinDust;
    public static Item leadDust;
    public static Item copperDust;
    public static Item solderLump;
    public static Item ironHammer;
    public static Item ironHammerHead;
    public static Item solderWire;
    public static Item copperWire;
    public static Item solderingIron;
    public static Item stoneBoard;
    public static Item pcbBlank;
    public static Item pcbBasic;
    public static Item pressurisedContainer;

    public static void init() {
        final Item[] items = new Item[]{
                tinIngot = new ItemTinIngot(),
                leadIngot = new ItemLeadIngot(),
                copperIngot = new ItemCopperIngot(),
                infusedIngot = new ItemInfusedIngot(),
                zechoriumCrystal = new ItemZechoriumCrystal(),
                tinDust = new ItemDust("tinDust"),
                leadDust = new ItemDust("leadDust"),
                copperDust = new ItemDust("copperDust"),
                solderLump = new ItemSolderLump(),
                ironHammer = new ItemIronHammer(),
                ironHammerHead = new ItemIronHammerHead(),
                solderWire = new ItemSolderWire(),
                copperWire = new ItemCopperWire(),
                solderingIron = new ItemSolderingIron(),
                stoneBoard = new ItemStoneBoard(),
                pcbBlank = new ItemPcbBlank(),
                pcbBasic = new ItemPcbBasic(),
                pressurisedContainer = new ItemPressurisedContainer()
        };

        for (int i = 0; i < items.length; i++) {
            RegisterHelper.registerItem(items[i]);
        }
    }
}
