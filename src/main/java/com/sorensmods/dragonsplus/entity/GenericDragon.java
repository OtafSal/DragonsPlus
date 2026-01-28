package com.sorensmods.dragonsplus.entity;

import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.entity.client.KeyMappings;
import com.sorensmods.dragonsplus.util.Lerp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class GenericDragon {


    public final TamableAnimal entity;

    //Flight behavior
    public final FlyingPathNavigation flight;
    public final GroundPathNavigation ground;

    public boolean flying;
    public boolean nearGround;
    public boolean changeNav = false;

    public boolean soaring;
    public boolean movingFly;

    float speedBonus = 0;
    final float ACCELERATION;
    final float MAX_SPEED;
    public float angleX;

    public final double JUMP_POWER;
    public final double LIFTOFF_POWER;




    public GenericDragon(TamableAnimal entityGet, double jumpPower, double liftoffPower)
    {
        entity = entityGet;

        flight = new FlyingPathNavigation(entity, entity.level());
        ground = new GroundPathNavigation(entity, entity.level());

        flight.setCanFloat(true);
        ground.setCanFloat(true);

        ACCELERATION = (float) (entity.getAttribute(Attributes.FLYING_SPEED).getValue()/10);
        MAX_SPEED = (float) (entity.getAttribute(Attributes.FLYING_SPEED).getValue()*2 );
        JUMP_POWER = jumpPower;
        LIFTOFF_POWER = liftoffPower;
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

    public static boolean isFlying(PathfinderMob entity, boolean alreadyFlying)
    {
        if (alreadyFlying) return !entity.onGround();
        boolean nearGround = entity.onGround() || !entity.level().noCollision(entity, new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.getX(), entity.getY() - (3), entity.getZ()));
        return !entity.isInWater() && !nearGround;
    }

    public boolean isNearGround()
    {
        return nearGround;
    }

    public PathNavigation setNavigation(boolean flyingGet)
    {
        changeNav = false;

        return flyingGet ?
                flight :
                ground;
    }


    public void updateVars()
    {
        PathNavigation navigationNew = entity.getNavigation();

        //Checks if its near ground
        nearGround = entity.onGround() || !entity.level().noCollision(entity, new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.getX(), entity.getY() - (3), entity.getZ()));
        // update flying state based on the distance to the ground
        boolean flyingNew = shouldFly();
        if (flyingNew != isFlying())
        {
            setFlying(flyingNew);

            //Update pathfinding method
            changeNav = true;
        }


        if (isFlying())
        {
            movingFly = Mth.abs((float) entity.getDeltaMovement().x) + Mth.abs((float) entity.getDeltaMovement().z) >= 0.4f;
            soaring = movingFly && entity.getDeltaMovement().y <= 0;

            //variable to accelerate the dragon when soaring
            if (movingFly) speedBonus += (float) Lerp.interpolation(speedBonus, soaring ? MAX_SPEED : 0, Mth.abs((float) (ACCELERATION*entity.getDeltaMovement().y)));
            else speedBonus = 0;

            if (speedBonus < 0) speedBonus = 0;
            if (speedBonus > MAX_SPEED) speedBonus = MAX_SPEED;

            if (entity.getControllingPassenger() == null) {

                angleX += (float) Lerp.interpolation(angleX, -(Mth.atan2(entity.getDeltaMovement().y, Mth.sqrt(
                        (float) entity.getDeltaMovement().x * (float) entity.getDeltaMovement().x +
                                (float) entity.getDeltaMovement().z * (float) entity.getDeltaMovement().z)) *
                        (double) (180F / (float) Math.PI)), 3);
            }

            entity.setXRot(angleX);

        }
        else {
            //reset acceleration
            speedBonus = 0;
            movingFly = false;
            soaring = false;
        }
    }


    public static void liftOff(double liftoff, double jump, LivingEntity entity)
    {
        entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(liftoff);
        entity.jumpFromGround();
        entity.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(jump);
    }



    public boolean travel(Vec3 vec3, float flutterLoop)
    {
        if (isFlying())
        {
            if (entity.isControlledByLocalInstance())
            {
                // Move relative to yaw - handled in the move controller or by driver
                entity.moveRelative(entity.getSpeed() + speedBonus, vec3);
                entity.move(MoverType.SELF, entity.getDeltaMovement());
                if (entity.getDeltaMovement().lengthSqr() < 0.1) // we're not actually going anywhere, bob up and down.
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0, Math.sin(entity.tickCount*flutterLoop) * 0.03, 0));
                entity.setDeltaMovement(entity.getDeltaMovement().scale(0.9f)); // smoothly slow down
            }

            entity.calculateEntityAnimation(true);
            return true;
        }
        else return false;
    }


    public InteractionResult tame(ItemStack stack, Player player, PathNavigation navigation)
    {
        // tame
        if (!entity.level().isClientSide && stack.is(Tags.Items.FOODS_RAW_FISH)) {
            stack.shrink(1);
            tamedFor(player, entity.getRandom().nextInt(5) == 0, navigation);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS; // pass regardless. We don't want to perform breeding, age ups, etc. on untamed.
    }
    public void tamedFor(Player player, boolean successful, PathNavigation navigation)
    {
        if (successful)
        {
            entity.tame(player);
            navigation.stop();
            entity.setTarget(null);
            entity.setOwnerUUID(player.getUUID());
            entity.level().broadcastEntityEvent(entity, (byte) 7);
        }
        else
        {
            entity.level().broadcastEntityEvent(entity, (byte) 6);
        }

    }

     public InteractionResult healDragon(ItemStack stack)
     {
         //noinspection ConstantConditions

         entity.heal(stack.get(DataComponents.FOOD).nutrition());
         entity.playSound(entity.getEatingSound(stack), 0.7f, 1);
         stack.shrink(1);
         return InteractionResult.sidedSuccess(entity.level().isClientSide);
     }

     public InteractionResult sit(PathNavigation navigation)
     {
         if (!entity.level().isClientSide)
         {
             navigation.stop();
             entity.setOrderedToSit(!entity.isOrderedToSit());
             if (entity.isOrderedToSit()) entity.setTarget(null);
         }
         return InteractionResult.sidedSuccess(entity.level().isClientSide);
     }

    //Saddle up
    public InteractionResult sadlleUp(ItemStack stack, SynchedEntityData entityData, EntityDataAccessor<Boolean> DATA_SADDLED)
    {
        stack.shrink(1);
        entityData.set(DATA_SADDLED, true);;
        return InteractionResult.sidedSuccess(entity.level().isClientSide);
    }

    //Saddle down
    public InteractionResult saddleDown(Player player, ItemStack stack, InteractionHand hand, SynchedEntityData entityData, EntityDataAccessor<Boolean> DATA_SADDLED)
    {
        entity.spawnAtLocation(Items.SADDLE);
        player.playSound(SoundEvents.SHEEP_SHEAR, 1f, 1f);
        entityData.set(DATA_SADDLED, false);
        entity.gameEvent(GameEvent.SHEAR, player);
        stack.hurtAndBreak(1, player, entity.getSlotForHand(hand));

        return InteractionResult.sidedSuccess(entity.level().isClientSide);
    }

    //Ride on
    public InteractionResult rideOn(Player player, PathNavigation navigation)
    {
        if (!entity.level().isClientSide)
        {
            player.startRiding(entity);
            navigation.stop();
            entity.setTarget(null);
        }
        entity.setOrderedToSit(false);
        entity.setInSittingPose(false);
        return InteractionResult.sidedSuccess(entity.level().isClientSide);
    }

    public Vec3 getRiddenInput(Player driver, Vec3 move)
    {
        double moveY = move.y;
        double moveForward = Math.min(Math.abs(driver.zza) + Math.abs(driver.xxa), 1);

        if (isFlying())
        {

            if (hasLocalDriver()) {
                moveForward = moveForward > 0 ? moveForward : 0;
                if (!movingFly) {
                    if (KeyMappings.FLIGHT_ASCEND_KEY.isDown()) moveY = 1;
                    else if (KeyMappings.FLIGHT_DESCENT_KEY.isDown()) moveY = -1;
                }
                else moveY = -driver.getXRot() / 90; // normalize from -1 to 1
            }
        }


        // mimic ****** implementation of AI movement vectors
        // the way this works is that it will mimic how setSpeed in Mob works:
        // it sets the normal speed variable,
        // and then sets the walk forward variable to the same value.
        // so if speed is 0.3, walk forward will also be 0.3 instead of 1.0.
        // so when moveRelative calculates movespeed, (walkforward * speed) we get 0.15.
        // so I guess we should do it to.
        var speed = getRiddenSpeed(driver);
        return new Vec3(0, moveY * speed, moveForward * speed);
    }
    boolean hasLocalDriver()
    {
        return entity.getControllingPassenger() instanceof Player p && p.isLocalPlayer();
    }

    public boolean isTamedFor(Player player)
    {
        return entity.isTame() && entity.isOwnedBy(player);
    }


    public void tickRidden(Player driver, Vec3 move)
    {
        // rotate head to match driver.
        float yaw = driver.yHeadRot;
        if (move.z > 0) // rotate in the direction of the drivers controls
            yaw += (float) Mth.atan2(driver.zza, driver.xxa) * (180f / (float) Math.PI) - 90;

        entity.yHeadRot = yaw;
        //This variable is also accessed in the dragon animator controller;
        angleX = driver.getXRot() * 0.68f;

        // rotate body towards the head
        entity.setYRot(Mth.rotateIfNecessary(entity.yHeadRot, entity.getYRot(), 4));

        if (entity.isControlledByLocalInstance())
        {
            if (!isFlying() && hasLocalDriver() && KeyMappings.FLIGHT_ASCEND_KEY.isDown()) liftOff(LIFTOFF_POWER, JUMP_POWER, entity);
        }
    }

    public void fixRot(Entity ridden)
    {
        // fix rider rotation
        ridden.xRotO = ridden.getXRot();
        ridden.yRotO = ridden.getYRot();
        ridden.setYBodyRot(entity.yBodyRot);
    }
    public float getRiddenSpeed(Player driver)
    {
        return (float) entity.getAttributeValue(isFlying()? Attributes.FLYING_SPEED : Attributes.MOVEMENT_SPEED);
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    public LivingEntity getControllingPassenger()
    {
        return entity.getFirstPassenger() instanceof LivingEntity driver && entity.isOwnedBy(driver)? driver : null;
    }

    public void addPassenger(Entity passenger)
    {
        if (passenger instanceof Player)
        {
            passenger.setYRot(entity.getYRot());
            passenger.setXRot(entity.getXRot());
        }
    }


    private Vec3 getDismountLocationInDirection(Vec3 pDirection, LivingEntity pPassenger) {
        double d0 = entity.getX() + pDirection.x;
        double d1 = entity.getBoundingBox().minY;
        double d2 = entity.getZ() + pDirection.z;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Pose pose : pPassenger.getDismountPoses()) {
            blockpos$mutableblockpos.set(d0, d1, d2);
            double d3 = entity.getBoundingBox().maxY + 0.75;

            do {
                double d4 = entity.level().getBlockFloorHeight(blockpos$mutableblockpos);
                if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(d4)) {
                    AABB aabb = pPassenger.getLocalBoundsForPose(pose);
                    Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
                    if (DismountHelper.canDismountTo(entity.level(), pPassenger, aabb.move(vec3))) {
                        pPassenger.setPose(pose);
                        return vec3;
                    }
                }

                blockpos$mutableblockpos.move(Direction.UP);
            } while (!((double)blockpos$mutableblockpos.getY() < d3));
        }

        return null;
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity, Vec3 escapeVector1, Vec3 escapeVector2) {
        Vec3 vec3 = escapeVector1;
        Vec3 vec31 = this.getDismountLocationInDirection(vec3, pLivingEntity);
        if (vec31 != null) {
            return vec31;
        } else {
            Vec3 vec32 = escapeVector2;
            Vec3 vec33 = this.getDismountLocationInDirection(vec32, pLivingEntity);
            return vec33 != null ? vec33 : entity.position();
        }
    }

}