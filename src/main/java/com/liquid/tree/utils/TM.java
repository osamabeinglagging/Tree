package com.liquid.tree.utils;

import com.ibm.icu.impl.BMPSet;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TM {
    public static List<Block> allowedDirtTypes = new ArrayList<Block>(Arrays.asList(Blocks.dirt, Blocks.grass, Blocks.farmland));
}
