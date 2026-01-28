package com.sorensmods.dragonsplus.entity.custom;


import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.entity.DragonAnimController;
import com.sorensmods.dragonsplus.entity.GenericDragon;
import com.sorensmods.dragonsplus.entity.ai.DragonMoveController;
import com.sorensmods.dragonsplus.entity.ai.DragonRandomFlyingGoal;
import com.sorensmods.dragonsplus.entity.ai.DragonRandomLiftoff;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;

import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SaddleItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ModEnderDragon extends TamableAnimal implements Saddleable, FlyingAnimal, PlayerRideable {

    //Dragon-specific attributes
    public static final double LIFTOFF_POWER = 3;
    public static final double JUMP_POWER = 1;
    public static final int STAMINA = 10;

    //Generic code for all dragons
    public final GenericDragon base;

    //Saddle data
    private static final EntityDataAccessor<Boolean> DATA_SADDLED = SynchedEntityData.defineId(ModEnderDragon.class, EntityDataSerializers.BOOLEAN);
    private static final String NBT_SADDLED = "Saddle";

    //Animation controller (animations + spine register)
    public DragonAnimController anims = new DragonAnimController(this, 20);




    /**
     * Returns true if the entity is flying.
     */
    @Override
    public boolean isFlying()
    {
        return base.flying;
    }


    public ModEnderDragon(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {

        super(pEntityType, pLevel);
        base = new GenericDragon(this, JUMP_POWER, LIFTOFF_POWER);

        noCulling = true;

        moveControl = new DragonMoveController(base);

        anims.UpperSpine = new ArrayList<>();
        anims.LowerSpine = new ArrayList<>();

        navigation = base.ground;

    }

    public static AttributeSupplier.Builder Properties()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.FLYING_SPEED, 0.32f)
                .add(Attributes.JUMP_STRENGTH, JUMP_POWER)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0)
                .add(Attributes.ATTACK_DAMAGE, 10);
    }

    //AI (Default)
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));

        //this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1f, p_326983_ -> p_326983_.is(Tags.Items.FOODS_RAW_FISH), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));

        this.goalSelector.addGoal(1, new DragonRandomFlyingGoal(this, 1f, 15, 30, 40));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(7, new DragonRandomLiftoff(this, LIFTOFF_POWER, JUMP_POWER));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        //this.goalSelector.addGoal(5, new DragonFollowOwnerGoal(base, 1f, 10f, 5f, 20f));

        targetSelector.addGoal(0, new OwnerHurtByTargetGoal(this));
        targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        targetSelector.addGoal(3, new NonTameRandomTargetGoal<>(this, Animal.class, false, e -> (e instanceof Animal)));
    }




    @Override
    public void tick()
    {
        super.tick();
        //LogUtils.getLogger().debug(!base.isFlying() + "");

        base.updateVars();
        if (!level().isClientSide) {
            if (base.changeNav) navigation = base.setNavigation(base.flying);
        }
        else {
            //Animations
            if (base.isFlying()) {
                anims.AnimateLiftOff();
                if (!base.soaring) {
                    anims.AnimateFlap();
                } else {
                    anims.StopFlap();
                }
            } else {
                anims.AnimateIdle();
            }
            calculateEntityAnimation(true);
            anims.animateSitting(isInSittingPose());
        }
    }




    @Override
    public boolean isFood(ItemStack pStack) {

        return false;
    }

    @Override
    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {

        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);

        builder.define(DATA_SADDLED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean(NBT_SADDLED, isSaddled());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        entityData.set(DATA_SADDLED, compound.getBoolean(NBT_SADDLED));
    }


    @Override
    public boolean isSaddleable() {
        return isTame();
    }

    @Override
    public void equipSaddle(ItemStack pStack, @Nullable SoundSource pSoundSource) {
        entityData.set(DATA_SADDLED, true);
    }

    @Override
    public boolean isSaddled() {
        return entityData.get(DATA_SADDLED);
    }


    @Override
    @SuppressWarnings("ConstantConditions")// I bet the breed exists at this point...
    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        var stack = player.getItemInHand(hand);
        var stackResult = stack.interactLivingEntity(player, this, hand);
        if (stackResult.consumesAction()) return stackResult;

        // tame!
        if (!isTame()) {
            return base.tame(stack, player, navigation);
        }

        // sit!
        if (base.isTamedFor(player) && (player.isSecondaryUseActive() || stack.is(Items.BONE) || isInSittingPose())) // "bone sitting" for legacy reasons
        {
            return base.sit(navigation);
        }

        //heal!
        if (getHealth() < getMaxHealth() && stack.is(ItemTags.FISHES))
        {
            return base.healDragon(stack);
        }

        // saddle up!
        if (base.isTamedFor(player) && isSaddleable() && !isSaddled() && stack.getItem() instanceof SaddleItem)
        {
            return base.sadlleUp(stack, entityData, DATA_SADDLED);
        }

        // give the saddle back!
        if (base.isTamedFor(player) && isSaddled() && stack.is(Tags.Items.TOOLS_SHEAR))
        {
            return base.saddleDown(player, stack, hand, entityData, DATA_SADDLED);
        }

        // ride on!
        if (base.isTamedFor(player) && isSaddled() && !isFood(stack) && !isInSittingPose())
        {
            return base.rideOn(player, navigation);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 vec3)
    {
        if (!base.travel(vec3, 0.1f)) super.travel(vec3);
    }



    @Override
    public LivingEntity getControllingPassenger()
    {
        return base.getControllingPassenger();
    }

    @Override
    protected Vec3 getRiddenInput(Player driver, Vec3 move)
    {
        return base.getRiddenInput(driver, move);
    }

    @Override
    protected void tickRidden(Player driver, Vec3 move)
    {
        base.tickRidden(driver, move);
        super.tickRidden(driver, move);
    }

    @Override
    protected void positionRider(Entity ridden, MoveFunction pCallback)
    {
        // fix rider rotation
        base.fixRot(ridden);
        super.positionRider(ridden, pCallback);
    }

    @Override
    protected float getRiddenSpeed(Player driver)
    {
        return base.getRiddenSpeed(driver);
    }
    @Override
    protected void addPassenger(Entity passenger)
    {
        super.addPassenger(passenger);
        base.addPassenger(passenger);
    }
    @Override
    protected void removePassenger(Entity passenger)
    {
        super.removePassenger(passenger);
        Vec3 vec31 = new Vec3(passenger.getX(), passenger.getY() - 3, passenger.getZ());
        passenger.setPos(vec31.x, vec31.y, vec31.z);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        return base.getDismountLocationForPassenger(pLivingEntity,
                getCollisionHorizontalEscapeVector(
                (double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F)
        ),
                getCollisionHorizontalEscapeVector(
                (double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F)
        ));
    }

    @Override
    public boolean onClimbable() {
        return false;
    }


}