package uk.co.zebcoding.zebtech.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import uk.co.zebcoding.zebtech.creative.ZebTab;
import uk.co.zebcoding.zebtech.help.Reference;

import java.util.List;

public class ItemPressurisedContainer extends Item {
    public ItemPressurisedContainer () {
        super();
        setUnlocalizedName("pressurisedContainer");
        setTextureName(Reference.MODID + ":"
                + getUnlocalizedName().substring(5));
        setCreativeTab(ZebTab.tabZeb);
    }

    @Override
    public void onCreated (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par1ItemStack.stackTagCompound = new NBTTagCompound();

        par1ItemStack.stackTagCompound.setInteger("item", Item.getIdFromItem(Items.apple));
        par1ItemStack.stackTagCompound.setInteger("amount", 64);
    }

    @Override
    public void addInformation (ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        NBTTagCompound t = itemStack.stackTagCompound;
        String item;
        item = (t != null && t.getInteger("item") != 0) ? Integer.toString(t.getInteger("amount")) + " " + new ItemStack
                (getItemById(t.getInteger("item"))).getDisplayName().toLowerCase() + "s" : "nothing";
        par3List.add("Contains " + item + ".");
    }

    @Override
    public ItemStack onItemRightClick (ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound t = itemStack.stackTagCompound;

        if (!world.isRemote) {
            int x = player.isSneaking() ? 1 : 32;
            if (x <= t.getInteger("amount")) {
                player.dropItem(getItemById(t.getInteger("item")), x);
                t.setInteger("amount", t.getInteger("amount") - x);
            }
        }

        if (t.getInteger("amount") == 0) t.setInteger("item", 0);

        return itemStack;
    }
}
