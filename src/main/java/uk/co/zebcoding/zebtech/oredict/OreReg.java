package uk.co.zebcoding.zebtech.oredict;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.items.ZItems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OreReg {

    public static void init() {

        final HashMap<String, Block> block = new HashMap<String, Block>();
        block.put("oreTin", ZBlocks.tinOre);
        block.put("oreLead", ZBlocks.leadOre);
        block.put("oreCopper", ZBlocks.copperOre);

        Set<Entry<String, Block>> s = block.entrySet();
        Iterator<Entry<String, Block>> i = s.iterator();

        while (i.hasNext()) {
            Map.Entry<String, Block> m = (Map.Entry<String, Block>) i.next();
            OreDictionary.registerOre(m.getKey(), m.getValue());
        }

        final HashMap<String, Item> item = new HashMap<String, Item>();
        item.put("ingotTin", ZItems.tinIngot);
        item.put("ingotLead", ZItems.leadIngot);
        item.put("ingotCopper", ZItems.copperIngot);
        item.put("dustTin", ZItems.tinDust);
        item.put("dustLead", ZItems.leadDust);
        item.put("dustCopper", ZItems.copperDust);
        item.put("wireCopper", ZItems.copperWire);

        Set<Entry<String, Item>> s2 = item.entrySet();
        Iterator<Entry<String, Item>> i2 = s2.iterator();

        while (i.hasNext()) {
            Map.Entry<String, Item> m = (Map.Entry<String, Item>) i2.next();
            OreDictionary.registerOre(m.getKey(), m.getValue());
        }

    }
}
