package uk.co.zebcoding.zebtech.tileentity.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.help.Reference;

public class TileEntityRenderTankItem implements IItemRenderer {

    /**
     * Resource loc. for the tank texture.
     */
    ResourceLocation s = new ResourceLocation(Reference.MODID, "textures/model/tankSide.png");
    ResourceLocation t = new ResourceLocation(Reference.MODID, "textures/model/tankTop.png");

    /**
     * Coordinates used in rendering tank.
     */
    double l = 0.01;
    double h = 0.99;

    /**
     * Coordinates used in rendering fluid.
     */
    double l2 = l + 0.005;
    double h2 = h - 0.005;

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return helper != ItemRendererHelper.ENTITY_BOBBING && helper != ItemRendererHelper.ENTITY_ROTATION;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glDisable(GL11.GL_LIGHTING);
        Minecraft.getMinecraft().getTextureManager().bindTexture(t);

        drawTankSide();
        drawTankTop();

        GL11.glEnable(GL11.GL_LIGHTING);
    }

    /**
     * Draws the sides of the tank.
     */
    public void drawTankSide() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(s);
        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        //Outside
        {
            tes.addVertexWithUV(l, l, l, 0, 0);
            tes.addVertexWithUV(l, h, l, 0, 1);
            tes.addVertexWithUV(h, h, l, 1, 1);
            tes.addVertexWithUV(h, l, l, 1, 0);

            tes.addVertexWithUV(h, l, l, 0, 0);
            tes.addVertexWithUV(h, h, l, 0, 1);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(h, l, h, 1, 0);

            tes.addVertexWithUV(h, l, h, 1, 0);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(l, h, h, 0, 1);
            tes.addVertexWithUV(l, l, h, 0, 0);

            tes.addVertexWithUV(l, l, h, 1, 0);
            tes.addVertexWithUV(l, h, h, 1, 1);
            tes.addVertexWithUV(l, h, l, 0, 1);
            tes.addVertexWithUV(l, l, l, 0, 0);
        }

        //Inside
        {
            tes.addVertexWithUV(h, l, l, 1, 0);
            tes.addVertexWithUV(h, h, l, 1, 1);
            tes.addVertexWithUV(l, h, l, 0, 1);
            tes.addVertexWithUV(l, l, l, 0, 0);

            tes.addVertexWithUV(h, l, h, 1, 0);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(h, h, l, 0, 1);
            tes.addVertexWithUV(h, l, l, 0, 0);

            tes.addVertexWithUV(l, l, h, 0, 0);
            tes.addVertexWithUV(l, h, h, 0, 1);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(h, l, h, 1, 0);

            tes.addVertexWithUV(l, l, l, 0, 0);
            tes.addVertexWithUV(l, h, l, 0, 1);
            tes.addVertexWithUV(l, h, h, 1, 1);
            tes.addVertexWithUV(l, l, h, 1, 0);
        }
        tes.draw();
    }

    /**
     * Draws the top and bottom of the tank.
     */
    public void drawTankTop() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(t);
        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        //Outside
        {
            tes.addVertexWithUV(l, h, l, 0, 0);
            tes.addVertexWithUV(l, h, h, 0, 1);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(h, h, l, 1, 0);

            tes.addVertexWithUV(h, l, l, 1, 0);
            tes.addVertexWithUV(h, l, h, 1, 1);
            tes.addVertexWithUV(l, l, h, 0, 1);
            tes.addVertexWithUV(l, l, l, 0, 0);
        }

        //Inside
        {
            tes.addVertexWithUV(h, h, l, 1, 0);
            tes.addVertexWithUV(h, h, h, 1, 1);
            tes.addVertexWithUV(l, h, h, 0, 1);
            tes.addVertexWithUV(l, h, l, 0, 0);

            tes.addVertexWithUV(l, l, l, 0, 0);
            tes.addVertexWithUV(l, l, h, 0, 1);
            tes.addVertexWithUV(h, l, h, 1, 1);
            tes.addVertexWithUV(h, l, l, 1, 0);
        }
        tes.draw();
    }

}