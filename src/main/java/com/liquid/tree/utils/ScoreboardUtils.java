package com.liquid.tree.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardUtils {
    static final Minecraft mc = Minecraft.getMinecraft();
    public static List<String> getScoreboardLines(){
        List<String> lines = new ArrayList<String>();
        if(mc.theWorld==null) return lines;
        Scoreboard scoreboard = mc.theWorld.getScoreboard();
        if(scoreboard==null) return lines;

        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
        if(objective==null) return lines;

        System.out.println(scoreboard.getSortedScores(objective));

        return lines;

    }
}
