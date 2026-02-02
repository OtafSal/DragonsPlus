package com.sorensmods.dragonsplus.entity.ai;

import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.entity.GenericDragon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class DragonRunAroundLikeCrazyGoal extends Goal {
    private final TamableAnimal dragon;
    private PathNavigation navigation;
    private final double speedModifier;
    private double posX;
    private double posY;
    private double posZ;

    private int timeout = 0;

    public DragonRunAroundLikeCrazyGoal(TamableAnimal pHorse, PathNavigation navigation, double pSpeedModifier) {
        this.dragon = pHorse;
        this.navigation = navigation;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.dragon.isTame() && this.dragon.isVehicle()) {
            Vec3 vec3 = DefaultRandomPos.getPos(this.dragon, 5, 4);
            if (vec3 == null) {
                return false;
            } else {
                this.posX = vec3.x;
                this.posY = vec3.y;
                this.posZ = vec3.z;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        this.dragon.getMoveControl().setWantedPosition(this.posX, this.posY, this.posZ, this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return !this.dragon.isTame() && timeout != 0 && this.dragon.isVehicle();
    }

    @Override
    public void tick() {
        if (timeout > 0) --timeout;
        else  timeout = 20;

        if (!this.dragon.isTame() && this.dragon.getRandom().nextInt(this.adjustedTickDelay(50)) == 0) {
            Entity entity = this.dragon.getFirstPassenger();
            if (entity == null) {
                return;
            }

            if (entity instanceof Player player) {
                int i = 2;
                int j = 10;
                if (j > 0 && this.dragon.getRandom().nextInt(j) < i && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(dragon, (Player)entity)) {
                    GenericDragon.tamedFor(dragon, player, true, navigation);
                    return;
                }
            }

            this.dragon.ejectPassengers();
            this.dragon.level().broadcastEntityEvent(this.dragon, (byte)6);
        }
    }
}
