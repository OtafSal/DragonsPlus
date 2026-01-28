package com.sorensmods.dragonsplus.entity.ai;

import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.entity.GenericDragon;
import com.sorensmods.dragonsplus.util.LandPos;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.TryFindLand;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class DragonRandomFlyingGoal extends Goal {
    private final PathfinderMob dragon;
    private final float speedMod;

    private int timeout;
    private int landTimeout;
    private Vec3 wanted;
    private Vec3 relative;


    private final double maxHeight;
    private final double minHeight;
    private final double range;

    double relativeMin;
    double relatibeMax;

    private boolean flying = false;
    private boolean registerPos = false;

    public DragonRandomFlyingGoal(PathfinderMob dragon, float speedMod, int minHeight, int maxHeight, int range) {
        this.dragon = dragon;
        this.speedMod = speedMod;

        //convert distance from blocks to standard
        this.minHeight = new BlockPos(0, minHeight, 0).getCenter().y;
        this.maxHeight = new BlockPos(0, maxHeight, 0).getCenter().y;
        this.range = new BlockPos(range, 0, 0).getCenter().x;

        //Don't glitch out when spawning on air
        relative = this.dragon.position();
        relativeMin = relative.y - (double) (minHeight + maxHeight) /2;
        relatibeMax = relative.y + (double) (minHeight + maxHeight) /2;

        resetLanding();
        resetTimeout();

        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        MoveControl movecontrol = dragon.getMoveControl();
        flying = GenericDragon.isFlying(dragon, flying);
        if (!GenericDragon.isFlying(dragon, flying)) {

            registerPos = true;
            return false;
        }
        if (!movecontrol.hasWanted()) {
            return true;
        } else {
            double d0 = movecontrol.getWantedX() - dragon.getX();
            double d1 = movecontrol.getWantedY() - dragon.getY();
            double d2 = movecontrol.getWantedZ() - dragon.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1 || d3 > 3600.0;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return timeout != 0;
    }

    @Override
    public void start() {
        if (registerPos) {
            setInitialPos();
            resetLanding();
        }
        wanted = setWanted();

        //try finding land below
        if (landTimeout <= 0) wanted = new LandPos(wanted, dragon).wantedPos;
    }

    Vec3 setWanted()
    {
        RandomSource randomsource = dragon.getRandom();

        double x = dragon.getX() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 320);
        double y = dragon.getY() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16);
        double z = dragon.getZ() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 320);

        double bestY = (relativeMin + relatibeMax)/2;
        double bestX = (relative.x + range)/2;
        double bestZ = (relative.z + range)/2;

        //Go to best position if out of bounds
        if (y < relativeMin || y > relatibeMax) y = bestY;
        if (x < relative.x - range || x > relative.x + range) x = bestX;
        if (z < relative.z - range || z > relative.z + range) z = bestZ;

        Vec3 pos = new Vec3(x, y, z);

        return pos;
    }

    Vec3 setInitialPos()
    {
        relative = dragon.position();

        relativeMin = dragon.position().y + minHeight;
        relatibeMax = dragon.position().y + maxHeight;

        registerPos = false;

        return dragon.position();
    }

    void resetLanding()
    {
        landTimeout = dragon.getRandom().nextIntBetweenInclusive(5, 20)*20;
    }
    void resetTimeout()
    {
        timeout = dragon.getRandom().nextIntBetweenInclusive(1, 3)*20;
    }

    @Override
    public void tick() {
        super.tick();

        //count timers (the landing timeout only resets when on land)
        if (timeout <= 0) resetTimeout();
        else --timeout;
        if (landTimeout > 0) --landTimeout;

        dragon.getMoveControl().setWantedPosition(wanted.x, wanted.y, wanted.z, speedMod);
    }
}
