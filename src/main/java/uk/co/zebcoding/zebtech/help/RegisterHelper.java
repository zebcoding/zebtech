package uk.co.zebcoding.zebtech.help;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RegisterHelper {
    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, Reference.MODID + "_"
                + block.getUnlocalizedName().substring(5));
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, Reference.MODID + "_"
                + item.getUnlocalizedName().substring(5));
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
    }
}
