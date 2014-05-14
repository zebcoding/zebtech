package uk.co.zebcoding.zebtech;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.crafting.ZRecipes;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.fluids.ZFluids;
import uk.co.zebcoding.zebtech.handler.CraftingHandler;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.items.ZItems;
import uk.co.zebcoding.zebtech.network.PacketHandler;
import uk.co.zebcoding.zebtech.proxy.ZClientProxy;
import uk.co.zebcoding.zebtech.proxy.ZCommonProxy;
import uk.co.zebcoding.zebtech.tileentity.ZTileEntity;
import uk.co.zebcoding.zebtech.worldgen.ZebTechWorldGen;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class ZebTech {

    @Instance(Reference.MODID)
    public static ZebTech instance;

    ZebTechWorldGen eventWorldGen = new ZebTechWorldGen();

    @SidedProxy(clientSide = "uk.co.zebcoding.zebtech.proxy.ZClientProxy", serverSide = "uk.co.zebcoding.zebtech.proxy.ZCommonProxy")
    public static ZCommonProxy zebProxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ZebTab.init();
        ZFluids.init();
        ZBlocks.init();
        ZItems.init();
        ZClientProxy.init();

        GameRegistry.registerWorldGenerator(eventWorldGen, 0);

        PacketHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new CraftingHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, zebProxy);

        ZTileEntity.init();
        ZRecipes.init();
    }
}
