package uk.co.zebcoding.zebtech.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.items.ZItems;

public class Recipes {
    public static void init() {
        // Crafting
        GameRegistry.addRecipe(new ItemStack(ZBlocks.tinBlock),
                new Object[]{"TTT", "TTT", "TTT", 'T', ZItems.tinIngot});
        GameRegistry.addRecipe(new ItemStack(ZBlocks.leadBlock),
                new Object[]{"LLL", "LLL", "LLL", 'L', ZItems.leadIngot});
        GameRegistry.addRecipe(new ItemStack(ZBlocks.copperBlock),
                new Object[]{"CCC", "CCC", "CCC", 'C', ZItems.copperIngot});
        GameRegistry.addRecipe(new ItemStack(ZItems.ironHammerHead),
                new Object[]{"II", " I", 'I', Items.iron_ingot});
        GameRegistry.addRecipe(new ItemStack(ZItems.ironHammer),
                new Object[]{"H", "S", "S", 'H', ZItems.ironHammerHead, 'S', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ZItems.stoneBoard, 4),
                new Object[]{"SSS", 'S', Blocks.stone_slab});
        GameRegistry.addRecipe(new ItemStack(ZItems.pcbBlank, 8),
                new Object[]{"SSS", "SGS", "SSS", 'S', ZItems.stoneBoard, 'G', new ItemStack(Items.dye, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(ZItems.solderingIron),
                new Object[]{"C  ", "BC ", "  I", 'C', ZItems.copperIngot, 'B', Items.coal, 'I', Items.iron_ingot});
        GameRegistry.addRecipe(new ItemStack(ZBlocks.alloySmelterCoalIdle),
                new Object[]{"CFC", "CCC", 'C', Blocks.cobblestone, 'F', Blocks.furnace});
        GameRegistry.addRecipe(new ItemStack(ZBlocks.alloySmelterBasicIdle),
                new Object[]{"III", "IAI", " P ", 'I', Items.iron_ingot, 'A', ZBlocks.alloySmelterCoalIdle,
                        'P', ZItems.pcbBasic});

        GameRegistry.addShapelessRecipe(new ItemStack(ZItems.tinIngot, 9),
                new Object[]{ZBlocks.tinBlock});
        GameRegistry.addShapelessRecipe(new ItemStack(ZItems.leadIngot, 9),
                new Object[]{ZBlocks.leadBlock});
        GameRegistry.addShapelessRecipe(new ItemStack(ZItems.copperIngot, 9),
                new Object[]{ZBlocks.copperBlock});

        GameRegistry.addShapelessRecipe(new ItemStack(ZItems.solderWire),
                new Object[]{new ItemStack(ZItems.ironHammer, 1, OreDictionary.WILDCARD_VALUE), ZItems.solderLump});
        GameRegistry.addShapelessRecipe(new ItemStack(ZItems.copperWire, 4),
                new Object[]{new ItemStack(ZItems.ironHammer, 1, OreDictionary.WILDCARD_VALUE), ZItems.copperIngot});

        ZItems.solderingIron.setContainerItem(ZItems.solderingIron);
        GameRegistry.addRecipe(new ItemStack(ZItems.pcbBasic),
                new Object[]{"IS ", "CCC", " P ", 'I', new ItemStack(ZItems.solderingIron, 1, OreDictionary.WILDCARD_VALUE),
                        'S', new ItemStack(ZItems.solderWire, 1, OreDictionary.WILDCARD_VALUE),
                        'C', ZItems.copperWire, 'P', ZItems.pcbBlank});

        // Smelting
        GameRegistry.addSmelting(ZBlocks.tinOre,
                new ItemStack(ZItems.tinIngot), 1);
        GameRegistry.addSmelting(ZBlocks.leadOre, new ItemStack(
                ZItems.leadIngot), 1);
    }
}
