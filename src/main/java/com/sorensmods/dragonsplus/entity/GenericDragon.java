package com.sorensmods.dragonsplus.entity;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GenericDragon {

    //Flight behavior
    public final FlyingPathNavigation flyingNavigation;
    public final GroundPathNavigation groundNavigation;
    public final Animal entity;

    public boolean flying;
    public boolean nearGround;

    public GenericDragon(Animal entityGet)
    {
        entity = entityGet;
        flyingNavigation = new FlyingPathNavigation(entity, entity.level());
        groundNavigation = new GroundPathNavigation(entity, entity.level());

        flyingNavigation.setCanFloat(true);
        groundNavigation.setCanFloat(true);


    }


    public boolean shouldFly()
    {
        if (isFlying()) return !entity.onGround(); // more natural landings
        return !entity.isInWater() && !isNearGround();
    }
    public void setFlying(boolean flyingSet)
    {
        this.flying = flyingSet;
    }
    /**
     * Returns true if the entity is flying.
     */
    public boolean isFlying()
    {
        return flying;
    }

    public boolean isNearGround()
    {
        return nearGround;
    }

    PathNavigation setNavigation(boolean flyingGet)
    {

        return flyingGet ?
                flyingNavigation :
                groundNavigation;
    }

    public PathNavigation updateVars(PathNavigation navigation)
    {

        //Checks if its near ground
        nearGround = entity.onGround() || !entity.level().noCollision(entity, new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.getX(), entity.getY() - (3), entity.getZ()));
        // update flying state based on the distance to the ground
        boolean flyingNew = shouldFly();
        if (flyingNew != isFlying())
        {
            setFlying(flyingNew);

            // update pathfinding method
            if (!entity.level().isClientSide) navigation = setNavigation(flying);
        }
        return navigation;
    }


    public void liftOff()
    {
        entity.jumpFromGround();
    }


    public void setAnims(DragonAnimController anims)
    {
        if (!this.isFlying()) {
            if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D) {
                anims.WALKING.Animate(entity.tickCount);
            } else {
                anims.IDLE.Animate(entity.tickCount);
            }
        }
        else
        {
            if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D) {
                anims.FLYING.Animate(entity.tickCount);
            } else {
                anims.FLYING_STILL.Animate(entity.tickCount);
            }
        }
    }

    public boolean travel(Vec3 vec3)
    {
        if (isFlying())
        {
            if (entity.isControlledByLocalInstance())
            {
                // Move relative to yaw - handled in the move controller or by driver
                entity.moveRelative(entity.getSpeed(), vec3);
                entity.move(MoverType.SELF, entity.getDeltaMovement());
                if (entity.getDeltaMovement().lengthSqr() < 0.1) // we're not actually going anywhere, bob up and down.
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0, Math.sin(entity.tickCount / 4f) * 0.03, 0));
                entity.setDeltaMovement(entity.getDeltaMovement().scale(0.9f)); // smoothly slow down
            }

            entity.calculateEntityAnimation(true);
            return true;
        }
        else return false;
    }
}
