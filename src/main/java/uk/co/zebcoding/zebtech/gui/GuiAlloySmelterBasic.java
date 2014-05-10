package uk.co.zebcoding.zebtech.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.container.ContainerAlloySmelterBasic;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.tileentity.TileEntityAlloySmelterBasic;

public class GuiAlloySmelterBasic extends GuiContainer {

    public static final ResourceLocation bground = new ResourceLocation(
            Reference.MODID + ":" + "textures/gui/GuiAlloySmelterCoal.png");

    public TileEntityAlloySmelterBasic alloySmelterBasic;

    public GuiAlloySmelterBasic(InventoryPlayer inventoryPlayer,
                                TileEntityAlloySmelterBasic entity) {
        super(new ContainerAlloySmelterBasic(inventoryPlayer, entity));
        this.alloySmelterBasic = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int var1, int var2) {
        String name = this.alloySmelterBasic.hasCustomInventoryName() ? this.alloySmelterBasic
                .getInventoryName() : I18n.format(
                this.alloySmelterBasic.getInventoryName(), new Object[0]);

        this.fontRendererObj.drawString(name, this.xSize / 2
                - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(
                I18n.format("container.inventory", new Object[0]), 8,
                this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2,
                                                   int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        int i1;


        if (this.alloySmelterBasic.isBurning()) {
            i1 = this.alloySmelterBasic.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }


        i1 = this.alloySmelterBasic.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

    }

}
