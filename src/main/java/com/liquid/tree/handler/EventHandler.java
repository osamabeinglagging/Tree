package com.liquid.tree.handler;

import com.liquid.tree.utils.AngleUtils;
import com.liquid.tree.utils.DrawUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

public class EventHandler {

    public static List<BlockPos> blocks = new ArrayList<BlockPos>();
    public static List<Block> allowedDirtTypes = new ArrayList<Block>(Arrays.asList(Blocks.dirt, Blocks.grass, Blocks.farmland));

    @SubscribeEvent
    void onRender(RenderWorldLastEvent event) {
        Color hsb = Color.getHSBColor((float) ((System.currentTimeMillis() / 10) % 2000) / 2000, 1, 1);
        Color chroma = new Color(hsb.getRed(), hsb.getGreen(), hsb.getBlue(), hsb.getAlpha());
        for (BlockPos block : blocks) {
            DrawUtils.drawBox(event, block, chroma, true);
        }
        if (AngleUtils.rotating) AngleUtils.rotate();
    }
}