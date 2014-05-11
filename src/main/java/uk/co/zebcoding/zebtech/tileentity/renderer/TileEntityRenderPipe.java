package uk.co.zebcoding.zebtech.tileentity.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.tileentity.TileEntityPipe;

public class TileEntityRenderPipe extends TileEntitySpecialRenderer {

    /**
     * Resource loc. for the pipe texture.
     */
    ResourceLocation t = new ResourceLocation(Reference.MODID, "textures/model/pipe.png");
    /**
     * 1 pixel in mc render.
     */
    float p = 1.0F / 16.0F;
    /**
     * 1 pixel in pipe texture.
     */
    float tp = 1.0F / 32.0F;

    @Override
    public void renderTileEntityAt(TileEntity te, double transX, double transY, double transZ, float f) {
        GL11.glTranslated(transX, transY, transZ);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.bindTexture(t);

        TileEntityPipe p = (TileEntityPipe) te;

        if (!p.oneOppositeConnection(p.c)) {
            drawCore(te);

            for (int i = 0; i < p.c.length; i++) {
                if (p.c[i] != null) {
                    drawConnection(p.c[i]);
                }
            }
        } else {
            for (int i = 0; i < p.c.length; i++) {
                if (p.c[i] != null) {
                    drawStraight(p.c[i]);
                    break;
                }
            }
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-transX, -transY, -transZ);
    }

    /**
     * Draws a straight connection.
     *
     * @param d = direction to draw pipe.
     */
    public void drawStraight(ForgeDirection d) {

        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (d.equals(ForgeDirection.UP)) {

        } else if (d.equals(ForgeDirection.DOWN)) {
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.NORTH)) {
            GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.SOUTH)) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.WEST)) {
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        } else if (d.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 20 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 20 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 20 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 20 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 20 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 20 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 20 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 20 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 5 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);

        tes.draw();


        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (d.equals(ForgeDirection.UP)) {

        } else if (d.equals(ForgeDirection.DOWN)) {
            GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.NORTH)) {
            GL11.glRotatef(-270.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.SOUTH)) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.WEST)) {
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
        } else if (d.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(-270.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    /**
     * Draws pipe connector.
     *
     * @param d = direction to draw pipe.
     */
    public void drawConnection(ForgeDirection d) {

        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (d.equals(ForgeDirection.UP)) {

        } else if (d.equals(ForgeDirection.DOWN)) {
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.NORTH)) {
            GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.SOUTH)) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.WEST)) {
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        } else if (d.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 10 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 10 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 10 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 10 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 10 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 10 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 10 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 10 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 1 - 11 * p / 2, 05 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 + 11 * 0 / 2, 0 + 11 * p / 2, 00 * tp, 5 * tp);

        tes.draw();


        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (d.equals(ForgeDirection.UP)) {

        } else if (d.equals(ForgeDirection.DOWN)) {
            GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.NORTH)) {
            GL11.glRotatef(-270.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.SOUTH)) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        } else if (d.equals(ForgeDirection.WEST)) {
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
        } else if (d.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(-270.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    /**
     * Draws the pipe core.
     *
     * @param te = TileEntityPipe
     */
    public void drawCore(TileEntity te) {
        Tessellator tes = Tessellator.instance;
        tes.startDrawingQuads();

        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 5 * tp);

        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 5 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 5 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 5 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 5 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 1 - 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 1 - 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 5 * tp, 5 * tp);

        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 5 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 0 + 11 * p / 2, 0 * tp, 0 * tp);
        tes.addVertexWithUV(1 - 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 0 * tp);
        tes.addVertexWithUV(0 + 11 * p / 2, 0 + 11 * p / 2, 1 - 11 * p / 2, 5 * tp, 5 * tp);

        tes.draw();
    }
}