package uk.co.zebcoding.zebtech.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

import java.util.List;

public class ItemZechoriumCrystal extends Item {
    public ItemZechoriumCrystal() {
        super();
        setUnlocalizedName("zechoriumCrystal");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("A mysterious energy pulsates");
        par3List.add("throughout the crystal...");
    }
}
