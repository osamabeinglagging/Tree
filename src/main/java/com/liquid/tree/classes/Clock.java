package com.liquid.tree.classes;

public class Clock {
    long endTime;
    boolean scheduled;

    public void start(int time){
        if(scheduled) return;
        endTime=System.currentTimeMillis()+time;
        scheduled = true;
    }

    public boolean getState(){
        end();
        return System.currentTimeMillis()>=endTime;
    }

    public void end(){
        scheduled=false;
    }
}
