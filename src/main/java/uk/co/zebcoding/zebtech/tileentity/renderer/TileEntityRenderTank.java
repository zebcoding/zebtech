package uk.co.zebcoding.zebtech.tileentity.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.tileentity.TileEntityTank;

public class TileEntityRenderTank extends TileEntitySpecialRenderer {

    private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

    /**
     * Reference to the tile entity.
     */
    TileEntityTank tank;

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
    public void renderTileEntityAt(TileEntity te, double transX, double transY, double transZ, float f) {
        GL11.glTranslated(transX, transY, transZ);
        GL11.glDisable(GL11.GL_LIGHTING);

        tank = (TileEntityTank) te;
        float i1 = this.tank.getLiquidLevel();

        drawTankSide();
        drawTankTop();
        if (i1 > 0)
            drawFluid(i1);

        TileEntityTank tank = (TileEntityTank) te;
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-transX, -transY, -transZ);
    }

    /**
     * Draws the sides of the tank.
     */
    public void drawTankSide() {
        this.bindTexture(s);
        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        //Outside
        {
            tes.addVertexWithUV(l, l, l, 1, 0);
            tes.addVertexWithUV(l, h, l, 1, 1);
            tes.addVertexWithUV(h, h, l, 0, 1);
            tes.addVertexWithUV(h, l, l, 0, 0);

            tes.addVertexWithUV(h, l, l, 1, 0);
            tes.addVertexWithUV(h, h, l, 1, 1);
            tes.addVertexWithUV(h, h, h, 0, 1);
            tes.addVertexWithUV(h, l, h, 0, 0);

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
        this.bindTexture(t);
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

    public void drawFluid(float i1) {

        IIcon icon = ZBlocks.liquidZechorium.getIcon(0, 0);
        this.bindTexture(BLOCK_TEXTURE);

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