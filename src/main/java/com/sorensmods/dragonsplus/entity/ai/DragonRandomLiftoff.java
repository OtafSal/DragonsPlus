package com.sorensmods.dragonsplus.entity.ai;

import com.sorensmods.dragonsplus.entity.GenericDragon;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DragonRandomLiftoff extends Goal {

    private final TamableAnimal mob;
    private final double JUMP_POWER;
    private final double LIFTOFF_POWER;

    boolean isFlying = false;

    public DragonRandomLiftoff(TamableAnimal mob, double liftoff, double jump) {
        this.mob = mob;

        JUMP_POWER = jump;
        LIFTOFF_POWER = liftoff;

        this.setFlags(EnumSet.of(Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {

        if (mob.getOwner() != null) return false;

        isFlying = GenericDragon.isFlying(mob, isFlying);

        return !GenericDragon.isFlying(mob, isFlying) && mob.getRandom().nextFloat() < 0.02f;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        GenericDragon.liftOff(LIFTOFF_POWER, JUMP_POWER, mob);
        super.start();
    }
}
