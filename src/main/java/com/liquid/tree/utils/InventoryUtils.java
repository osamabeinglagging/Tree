package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

public class InventoryUtils {
    final static Minecraft mc = Minecraft.getMinecraft();
    public static int findSlot(String name){
        InventoryPlayer inventory = mc.thePlayer.inventory;
        for(int i = 0; i < inventory.getSizeInventory(); i++){
            if(inventory.getStackInSlot(i)==null) continue;
            if(inventory.getStackInSlot(i).getDisplayName().toLowerCase().contains(name)){
                return i;
            }
        }
        return 100;
    }
}
