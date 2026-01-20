package com.sorensmods.dragonsplus.util;


import net.minecraft.util.Mth;

public class Lerp {


    public static double interpolation(double start, double target, double step)
    {
        double stepFixed = Math.min(Mth.abs((float) (start - target)), step);
        return start <= target ? stepFixed : -stepFixed;
    }
}
