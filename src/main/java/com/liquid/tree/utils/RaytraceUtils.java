package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RaytraceUtils {

    static final Minecraft mc = Minecraft.getMinecraft();
    public static BlockPos getTargetedBlock(int distance){
        Vec3 playerPos = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
        Vec3 lookDir = mc.thePlayer.getLookVec();
        double scaledX = lookDir.xCoord * distance;
        double scaledY = lookDir.yCoord * distance;
        double scaledZ = lookDir.zCoord * distance;
        Vec3 rayEnd = playerPos.addVector(scaledX, scaledY, scaledZ);

        MovingObjectPosition traceResult = mc.theWorld.rayTraceBlocks(playerPos, rayEnd);
        if(traceResult != null){
            return traceResult.getBlockPos();
        }
        return null;
    }

}
