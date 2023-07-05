package com.liquid.tree;

import com.liquid.tree.commands.Set;
import com.liquid.tree.macro.ForagingMacro;
import com.liquid.tree.handler.EventHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Tree.MODID, version = Tree.VERSION)
public class Tree
{
    public static final String MODID = "tree";
    public static final String VERSION = "1.0";
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new ForagingMacro());

        ClientCommandHandler.instance.registerCommand(new Set());
    }
}
