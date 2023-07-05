package com.liquid.tree.utils;

import com.liquid.tree.classes.Tuple2;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AngleUtils {
    final static Minecraft mc = Minecraft.getMinecraft();
    static long endTime;
    public static boolean rotating = false;
    static float yaw;
    static float pitch;
    public static List<BlockPos> blocks = new ArrayList<BlockPos>();

    public static float getClosest90(){
        float yaw = get360Yaw();
        if(yaw >= 0 && yaw <=45){
            return 0.0f;
        }
        else if(yaw >=45.0f && yaw < 135.0f){
            return 90.0f;
        }
        else if(yaw >= 135.0f && yaw < 225.0f){
            return 180.0f;
        }
        else if(yaw >=225.0f && yaw < 315.0f){
            return 270.0f;
        }
        else{
            return 0.0f;
        }
    }

    public static double getYawToBlock(BlockPos blockPos) {
        double dX = blockPos.getX()+0.5 - mc.thePlayer.posX;
        double dZ = blockPos.getZ()+0.5 - mc.thePlayer.posZ;
        double yaw = Math.atan2(dX, dZ);
        return -Math.toDegrees(yaw);
    }

    public static double getPitchToBlock(BlockPos blockPos) {
        double dX = blockPos.getX()+0.5 - mc.thePlayer.posX;
        double dY = blockPos.getY() - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double dZ = blockPos.getZ()+0.5 - mc.thePlayer.posZ;
        double distanceToBlock = Math.sqrt(dX * dX + dZ * dZ);
        return -Math.toDegrees(Math.atan2(dY, distanceToBlock));
    }

    static float get360Yaw(){return get360Yaw(mc.thePlayer.rotationYaw);}

    public static float get360Yaw(float yaw){
        return ((yaw % 360) + 360) % 360;
    }

    public static void lookAtBlock(BlockPos block, int time){
        if(rotating) return;

        endTime = System.currentTimeMillis() + time;
        yaw = get360Yaw((float)getYawToBlock(block));
        pitch = (float)getPitchToBlock(block);
        rotating = true;
    }

    public static void lookAtYP(float y, float p, int time){
        if(rotating) return;

        endTime = System.currentTimeMillis() + time;
        yaw = get360Yaw(y);
        pitch = p;
        rotating = true;
    }

    public static void rotate(){
        if(!rotating) return;

        Tuple2<Boolean, Float> noWayPogQuestionMark = getClosestDistance(mc.thePlayer.rotationYaw, yaw);
        float ticks = (float)(endTime-System.currentTimeMillis()) /  50.0f;
        if(ticks <= 0) {rotating = false ;return;}

        float yawInterpolate = noWayPogQuestionMark.right()/ticks;
        float pitchInterpolate = Math.abs(mc.thePlayer.rotationPitch - pitch) / ticks;

        if (noWayPogQuestionMark.left()){
           mc.thePlayer.rotationYaw -= yawInterpolate;
        }
        else {
            mc.thePlayer.rotationYaw += yawInterpolate;
        }

        if (pitch < mc.thePlayer.rotationPitch){
            mc.thePlayer.rotationPitch -= pitchInterpolate;
        }
        else {
            mc.thePlayer.rotationPitch += pitchInterpolate;
        }

        if(Math.abs(mc.thePlayer.rotationYaw - yaw) < 0.3f && Math.abs(mc.thePlayer.rotationPitch - pitch) < 0.3f){
            endTime = 0;
            rotating = false;
            yaw = 0;
            pitch = 0;
        }

    }

    static Tuple2<Boolean, Float> getClosestDistance(float current, float distance){
        float clockwiseDistance = ((distance - current) + 360) % 360;
        float antiClockWiseDistance = ((current - distance ) + 360 ) % 360;
        Tuple2<Boolean, Float> result = new Tuple2<Boolean, Float>();
        if(clockwiseDistance > antiClockWiseDistance){
            result.add(true, antiClockWiseDistance);
        }
        else{
            result.add(false, clockwiseDistance);
        }
        return result;
    }
}