package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class PlayerUtils {
    static final Minecraft mc = Minecraft.getMinecraft();
    public static BlockPos playerPos(){
        return new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
    }
}
