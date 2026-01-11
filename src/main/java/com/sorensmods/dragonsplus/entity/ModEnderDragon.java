package com.sorensmods.dragonsplus.entity;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.entity.animal.Animal;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ModEnderDragon extends Animal {

    //Idle animation
    public final AnimationState idleAnimationState = new AnimationState();
    private int animTimeout = 0;

    private void setupAnimationStates() {
        if(this.animTimeout <= 0) {
            this.animTimeout = 20*4;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.animTimeout;
        }
    }

    @Override
    public void tick() {

        if (this.level().isClientSide) {
            setupAnimationStates();
        }

        super.tick();
    }

    public ModEnderDragon(EntityType<? extends Animal> pEntityType, Level pLevel) {

        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder Properties()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.MOVEMENT_SPEED, 0.5f)
                .add(Attributes.FOLLOW_RANGE, 60);
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
}
