package uk.co.zebcoding.zebtech.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;

public class ContainerZechoriumCompressor extends Container{
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }
}
