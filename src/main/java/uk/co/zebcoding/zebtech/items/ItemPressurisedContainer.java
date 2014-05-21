package uk.co.zebcoding.zebtech.items;

import net.minecraft.entity.Entity;
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
        setMaxStackSize(16);
    }

    @Override
    public void onCreated (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par1ItemStack.stackTagCompound = new NBTTagCompound();

        par1ItemStack.stackTagCompound.setInteger("item", 0);
        par1ItemStack.stackTagCompound.setInteger("metadata", 0);
        par1ItemStack.stackTagCompound.setInteger("amount", 0);
    }

    @Override
    public void onUpdate (ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.stackTagCompound = new NBTTagCompound();

            par1ItemStack.stackTagCompound.setInteger("item", 0);
            par1ItemStack.stackTagCompound.setInteger("metadata", 0);
            par1ItemStack.stackTagCompound.setInteger("amount", 0);
        }
    }

    @Override
    public void addInformation (ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        NBTTagCompound t = itemStack.stackTagCompound;
        String item = null;
        if(t != null) {
            item = t.getInteger("item") != 0 ? Integer.toString(t.getInteger("amount")) + " " + new ItemStack
                    (getItemById(t.getInteger("item")), 1, t.getInteger("metadata")).getDisplayName().toLowerCase() :
                    "nothing";
            if (!item.substring(item.length() - 1).equals("s") && t.getInteger("amount") > 1)
                item += "s";
        }
        if (item == null) item = "nothing";
        par3List.add("Contains " + item + ".");
    }

    @Override
    public ItemStack onItemRightClick (ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound t = itemStack.stackTagCompound;

        if (!world.isRemote) {
            int x = player.isSneaking() ? 1 : 32;
            int y = t.getInteger("amount");
            if (y != 0) {
                if (x > y) {
                    x = y;
                }
                player.entityDropItem(new ItemStack(getItemById(t.getInteger("item")), x, t.getInteger("metadata")), 1);
                t.setInteger("amount", t.getInteger("amount") - x);
            }
        }

        if (t.getInteger("amount") == 0) {
            t.setInteger("item", 0);
            t.setInteger("metadata", 0);
        }

        return itemStack;
    }
}
