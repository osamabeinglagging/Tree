package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DrawUtils {

    final static Minecraft mc = Minecraft.getMinecraft();
  public static void drawBox(RenderWorldLastEvent event, BlockPos blockPos, Color color, boolean esp) {
        AxisAlignedBB aabb = new AxisAlignedBB(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX()+1, blockPos.getY()+1,blockPos.getZ()+1);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferBuilder = tessellator.getWorldRenderer();
        final Entity render = mc.getRenderViewEntity();

        final double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * event.partialTicks;
        final double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * event.partialTicks;
        final double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * event.partialTicks;

        float r = color.getRed()/255.0f;
        float g = color.getGreen()/255.0f;
        float b = color.getBlue()/255.0f;
        float a = 255.0f * 0.9f;

        GlStateManager.pushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.translate(-realX, -realY, -realZ);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GL11.glDisable(3553);
        GL11.glLineWidth(3f);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        if(esp){
            GlStateManager.disableDepth();
        }
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        bufferBuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();
//
        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();

        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r,g,b,a).endVertex();
        bufferBuilder.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r,g,b,a).endVertex();
        tessellator.draw();

        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        if(esp){
            GlStateManager.enableDepth();
        }
        GlStateManager.depthMask(true);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }

}
