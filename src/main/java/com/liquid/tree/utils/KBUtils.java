package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import javax.swing.text.JTextComponent;

public class KBUtils {

    static final Minecraft mc = Minecraft.getMinecraft();
     public static void rightClick() {
        if (!ReflectionUtils.invoke(mc, "func_147121_ag")) {
            ReflectionUtils.invoke(mc, "rightClickMouse");
        }
    }

    public static void leftClick() {
        if (!ReflectionUtils.invoke(mc, "func_147116_af")) {
            ReflectionUtils.invoke(mc, "clickMouse");
        }
    }

    public static void leftClick(boolean left){
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), left);
    }
}
