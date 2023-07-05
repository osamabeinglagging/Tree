package com.liquid.tree.utils;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class WorldUtils {
    static final Minecraft mc = Minecraft.getMinecraft();

    public static BlockPos getRelativeBlock(int x, int y, int z){
        BlockPos playerPos = PlayerUtils.playerPos().add(0,-1,0);
        if(mc.thePlayer.getHorizontalFacing() == EnumFacing.SOUTH){
            return playerPos.add(-x,y,z);
        }
        else if(mc.thePlayer.getHorizontalFacing() == EnumFacing.NORTH){
            return playerPos.add(x,y,-z);
        }
        else if(mc.thePlayer.getHorizontalFacing() == EnumFacing.EAST){
            return playerPos.add(z,y,x);
        }
        else{
            return playerPos.add(-z,y,-x);
        }
    }

    public static boolean saplingOnTop(BlockPos block){
        return mc.theWorld.getBlockState(block.up()).getBlock() instanceof BlockSapling;
    }
}
