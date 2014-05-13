package uk.co.zebcoding.zebtech.tileentity.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.help.Reference;

public class TileEntityRenderTankItem implements IItemRenderer {

    private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

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
        GL11.glTranslatef(0.0F, -0.1F, 0.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(t);

        drawTankSide();
        drawTankTop();
        float i1 = item.getItemDamage() / 20000.0F;
        if (i1 > 0)
            drawFluid(i1);

        GL11.glTranslatef(0.0F, 0.1F, 0.0F);
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
        tes.draw();
    }

    public void drawFluid(float i1) {

        IIcon icon = ZBlocks.liquidZechorium.getIcon(0, 0);
        Minecraft.getMinecraft().getTextureManager().bindTexture(BLOCK_TEXTURE);

        double l3 = icon.getMinU(),
                l4 = icon.getMinV(),
                h3 = icon.getMaxU(),
                h4 = icon.getInterpolatedV(i1 * 16);

        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        tes.addVertexWithUV(l2, l2 * i1, h2, l3, l4);
        tes.addVertexWithUV(h2, l2 * i1, h2, h3, l4);
        tes.addVertexWithUV(h2, h2 * i1, h2, h3, h4);
        tes.addVertexWithUV(l2, h2 * i1, h2, l3, h4);

        tes.addVertexWithUV(h2, h2 * i1, l2, l3, l4);
        tes.addVertexWithUV(h2, h2 * i1, h2, h3, l4);
        tes.addVertexWithUV(h2, l2 * i1, h2, h3, h4);
        tes.addVertexWithUV(h2, l2 * i1, l2, l3, h4);

        tes.addVertexWithUV(l2, h2 * i1, l2, l3, h4);
        tes.addVertexWithUV(h2, h2 * i1, l2, h3, h4);
        tes.addVertexWithUV(h2, l2 * i1, l2, h3, l4);
        tes.addVertexWithUV(l2, l2 * i1, l2, l3, l4);

        tes.addVertexWithUV(l2, l2 * i1, l2, l3, h4);
        tes.addVertexWithUV(l2, l2 * i1, h2, h3, h4);
        tes.addVertexWithUV(l2, h2 * i1, h2, h3, l4);
        tes.addVertexWithUV(l2, h2 * i1, l2, l3, l4);

        tes.addVertexWithUV(l2, h2 * i1, h2, l3, h4);
        tes.addVertexWithUV(h2, h2 * i1, h2, h3, h4);
        tes.addVertexWithUV(h2, h2 * i1, l2, h3, l4);
        tes.addVertexWithUV(l2, h2 * i1, l2, l3, l4);

        tes.addVertexWithUV(l2, l2, l2, l3, l4);
        tes.addVertexWithUV(h2, l2, l2, h3, l4);
        tes.addVertexWithUV(h2, l2, h2, h3, h4);
        tes.addVertexWithUV(l2, l2, h2, l3, h4);

        tes.draw();
    }

}