package com.liquid.tree.macro;

import com.liquid.tree.handler.EventHandler;
import com.liquid.tree.utils.*;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import static com.liquid.tree.utils.LogUtils.log;
import java.util.ArrayList;
import java.util.List;

public class ForagingMacro {
    static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean macroOn = false;
    static State currentState = State.NONE;
    static List<BlockPos> blocks = new ArrayList<BlockPos>();

    static boolean monke = true;
    static int sapling;
    static int bonemeal;
    static int axe;
    static int rod;
    static int delay = 0;

    static boolean buglog = false;

    public enum State{
        START,
        SAPLING,
        BONEMEAL,
        TREECAP,
        ROD,
        SWITCHING,
        NONE
    }

    @SubscribeEvent
    void onTick(TickEvent.ClientTickEvent event){
        if(mc.theWorld==null || mc.thePlayer==null)return;;
        if(!macroOn) return;
        if(AngleUtils.rotating) return;
        if(delay>0) delay--;
        if(!findItems()){
            log("expected items not in hotbar");
            stop();
            return;
        }

        for(BlockPos block: blocks){
            if(!TM.allowedDirtTypes.contains(mc.theWorld.getBlockState(block).getBlock()) && !EventHandler.blocks.contains(block)){
                EventHandler.blocks.add(block);
                System.out.println("HERE");
            }
        }
        if(EventHandler.blocks.size()>0){
            System.out.println(EventHandler.blocks);
            log("stoppign macro because not all blocks are ok");
            stop();
        }
            switch (currentState) {
                case START:
                    for (BlockPos block : blocks) {
                        if (mc.theWorld.getBlockState(block.up()).getBlock() instanceof BlockLog) {
                            log("log found");
                            double yaw = AngleUtils.get360Yaw((float) AngleUtils.getYawToBlock(block.up()));
                            double pitch = AngleUtils.getPitchToBlock(block.up());
                            if(!((Math.abs(AngleUtils.get360Yaw(mc.thePlayer.rotationYaw) - yaw)) < 0.2 && Math.abs(mc.thePlayer.rotationPitch - pitch) < 0.2 && delay <= 0)){
                                KBUtils.leftClick(false);
                                log("looking at log");
                                AngleUtils.lookAtBlock(block.up(), 150);
                            } else {
                                log("breakinge etra blocks");
                                hold(axe);
                                KBUtils.leftClick(true);
                            }
                            buglog=true;
                            break;
                        }
                        else{
                            buglog=false;
                        }
                    }
                    if(buglog) return;
                    currentState = State.SAPLING;
                    break;
                case SAPLING:
                    for (BlockPos block : blocks) {
                        if (!(mc.theWorld.getBlockState(block.up()).getBlock() instanceof BlockSapling) && !(mc.theWorld.getBlockState(block.up()).getBlock() instanceof BlockLog)) {
                            KBUtils.leftClick(false);
                            log("sapling time");
                            hold(sapling);
                            double yaw = AngleUtils.get360Yaw((float) AngleUtils.getYawToBlock(block.up()));
                            double pitch = AngleUtils.getPitchToBlock(block.up());
                            AngleUtils.lookAtBlock(block.up(), 150);
                            if((Math.abs(AngleUtils.get360Yaw(mc.thePlayer.rotationYaw) - yaw)) < 0.2 && Math.abs(mc.thePlayer.rotationPitch - pitch) < 0.2 && delay <= 0) {
                                log("clicking");
                                KBUtils.rightClick();
                            }
                            break;
                        }
                    }
                    if (blocksWithoutSapling() == blocks.size()) {
                        log("saplings placed.");
                        currentState = State.BONEMEAL;
                    }
                    break;
                case BONEMEAL:
                    hold(bonemeal);
                    BlockPos targetBlock = blocks.get(blocks.size() - 1);
                    if (mc.theWorld.getBlockState(targetBlock.up()).getBlock() instanceof BlockLog) {
                        log("tree grown");
                        currentState = State.TREECAP;
                    } else if (delay <= 0) {
                        KBUtils.rightClick();
                        delay = 10;
                        System.out.println(delay);
                    } else {
                        AngleUtils.lookAtBlock(targetBlock.up(), 150);
                    }
                    break;
                case TREECAP:
                    hold(axe);
                    BlockPos targetLogBlock = blocks.get(blocks.size() - 1).up();
                    if (!RaytraceUtils.getTargetedBlock(4).equals(targetLogBlock)) {
                        AngleUtils.lookAtBlock(targetLogBlock, 150);
                    } else if (mc.theWorld.getBlockState(targetLogBlock).getBlock() instanceof BlockLog) {
                        KBUtils.leftClick(true);
                        delay = 20;
                    }
                    if (!(mc.theWorld.getBlockState(targetLogBlock).getBlock() instanceof BlockLog)) {
                        KBUtils.leftClick(false);
                    }
                    if (delay <= 0) {
                        log("breaking ended");
                        if (monke) currentState = State.ROD;
                        else currentState = State.SAPLING;
                        delay = 20;
                    }
                    break;
                case ROD:
                    hold(rod);
                    KBUtils.rightClick();
                    currentState = State.START;
                    break;
            }
        }

    public static List<BlockPos> getExpectedBlocks(){
        List<BlockPos> blocks = new ArrayList<BlockPos>();
        blocks.add(0, WorldUtils.getRelativeBlock(0,1,1));
        blocks.add(0, WorldUtils.getRelativeBlock(-1,1,1));
        blocks.add(0, WorldUtils.getRelativeBlock(0,1,2));
        blocks.add(0, WorldUtils.getRelativeBlock(-1,1,2));
        return blocks;
    }

    public static int blocksWithoutSapling(){
        int saplings = 0;
        for(BlockPos block: blocks){
            if(mc.theWorld.getBlockState(block.up()).getBlock() instanceof BlockSapling){
                saplings++;
            }
        }
        return saplings;
    }

    public static void start(){
        blocks.addAll(getExpectedBlocks());
//        EventHandler.blocks.addAll(blocks);
        currentState = State.START;
        macroOn = true;
    }

    public static void stop(){
        macroOn = false;
        blocks.clear();
        EventHandler.blocks.clear();
        currentState = State.NONE;
    }

    public static boolean findItems(){
        sapling = InventoryUtils.findSlot("sapling");
        bonemeal = InventoryUtils.findSlot("bone");
        axe = InventoryUtils.findSlot("treecap");
        if(monke){
            rod = InventoryUtils.findSlot("rod");
            return sapling<9 && bonemeal < 9 && axe< 9 && rod<9;
        }
        return sapling<9 && bonemeal < 9 && axe< 9;
    }

    static void hold(int slot){
        mc.thePlayer.inventory.currentItem = slot; // Returns error for sum reason
    }
}
