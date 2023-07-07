package com.liquid.tree.commands;

import com.liquid.tree.handler.EventHandler;
import com.liquid.tree.macro.ForagingMacro;
import com.liquid.tree.utils.*;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.liquid.tree.utils.LogUtils.log;

public class Set extends CommandBase {

    final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }
    
    @Override
    public String getCommandName() {
        return "set";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        EventHandler.blocks.clear();
        if(ForagingMacro.macroOn){
            log("disabling");
            ForagingMacro.stop();
        }
        else{
            log("enabling");
            ForagingMacro.start();
        }
//        ScoreboardUtils.getScoreboardLines();
//        Scoreboard scoreboard = mc.theWorld.getScoreboard();
//        System.out.println(scoreboard.);
//        log(" " + mc.thePlayer.inventory.getCurrentItem().getDisplayName());
    }
}
