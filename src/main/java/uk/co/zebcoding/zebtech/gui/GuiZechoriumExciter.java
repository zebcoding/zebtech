package uk.co.zebcoding.zebtech.gui;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;
import uk.co.zebcoding.zebtech.container.ContainerZechoriumExciter;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.tileentity.TileEntityZechoriumExciter;

public class GuiZechoriumExciter extends GuiContainer {

    public static final ResourceLocation bground = new ResourceLocation(
            Reference.MODID + ":" + "textures/gui/GuiZechoriumExciter.png");
    private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

    public static final ResourceLocation zechorium = new ResourceLocation(Reference.MODID + ":" + "textures/blocks/liquidZechoriumStill.png");

    public TileEntityZechoriumExciter zechoriumExciter;

    public GuiZechoriumExciter(InventoryPlayer inventoryPlayer,
                               TileEntityZechoriumExciter entity) {
        super(new ContainerZechoriumExciter(inventoryPlayer, entity));
        this.zechoriumExciter = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int var1, int var2) {
        String name = this.zechoriumExciter.hasCustomInventoryName() ? this.zechoriumExciter
                .getInventoryName() : I18n.format(
                this.zechoriumExciter.getInventoryName(), new Object[0]);

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


        if (this.zechoriumExciter.isBurning()) {
            i1 = this.zechoriumExciter.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }


        i1 = this.zechoriumExciter.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

        i1 = this.zechoriumExciter.getLiquidLevelScaled(44);

        this.drawFluid(ZBlocks.liquidZechorium, i1, k + 114, l + 21, 16, 44);
    }

    private void drawFluid(Block fluid, int level, int x, int y, int width, int height) {
        if (fluid == null) {
            return;
        }
        IIcon icon = fluid.getIcon(0, 0);
        mc.renderEngine.bindTexture(BLOCK_TEXTURE);
        int fullX = width / 16;
        int fullY = height / 16;
        int lastX = width - fullX * 16;
        int lastY = height - fullY * 16;
        int fullLvl = (height - level) / 16;
        int lastLvl = (height - level) - fullLvl * 16;
        for (int i = 0; i < fullX; i++) {
            for (int j = 0; j < fullY; j++) {
                if (j >= fullLvl) {
                    drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
                }
            }
        }
        for (int i = 0; i < fullX; i++) {
            drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
        }
        for (int i = 0; i < fullY; i++) {
            if (i >= fullLvl) {
                drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
            }
        }
        drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
    }

    //The magic is here
    private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
        tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
        tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
        tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
        tess.draw();
    }
}
