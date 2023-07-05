package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class LogUtils {
    static final Minecraft mc = Minecraft.getMinecraft();
    public static void log(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Log " + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + "» " + EnumChatFormatting.GRAY + message));
    }
}
