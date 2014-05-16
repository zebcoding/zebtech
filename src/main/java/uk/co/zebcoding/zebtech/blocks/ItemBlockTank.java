package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.util.List;

public class ItemBlockTank extends ItemBlock {

    public ItemBlockTank(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!world.setBlock(x, y, z, field_150939_a, metadata, 3)) {
            return false;
        }

        if (world.getBlock(x, y, z) == field_150939_a) {
            BlockTank block = (BlockTank) world.getBlock(x, y, z);
            block.setStored(stack.getItemDamage());
            field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
            field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Currently holding:");
        par3List.add(getDamage(par1ItemStack) + " / 10000 mB");
    }
}
