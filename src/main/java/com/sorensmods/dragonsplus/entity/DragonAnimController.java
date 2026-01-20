package com.sorensmods.dragonsplus.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import com.sorensmods.dragonsplus.util.Lerp;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.TamableAnimal;

import java.util.List;

public class DragonAnimController {

    TamableAnimal entity;

    public AnimationState IDLE, WALKING, SITTING, STANDING, FLYING, LIFTOFF, LANDING;

    //Durations for the flight animation
    public int duration;
    int timeout = 0;

    //"Trg" stands for "Trigger"
    boolean liftoffTrg = true;
    boolean landingTrg = true;


    //Variables using to control the dragon's spine
    public List<ModelPart> UpperSpine;
    public ModelPart Trunk;
    public List<ModelPart> LowerSpine;


    public DragonAnimController(TamableAnimal entityGet, int flightDuration)
    {

        IDLE = new AnimationState();
        WALKING = new AnimationState();

        SITTING = new AnimationState();
        STANDING = new AnimationState();

        FLYING = new AnimationState();
        LIFTOFF = new AnimationState();
        LANDING = new AnimationState();

        duration = flightDuration;

        entity = entityGet;
    }

    public void AnimateIdle() {

        StopFlap();
        if (landingTrg) {
            LIFTOFF.stop();
            LANDING.start(entity.tickCount);

            landingTrg = false;
            liftoffTrg = true;
        }
    }

    public void AnimateLiftOff()
    {
        if (liftoffTrg) {
            LANDING.stop();
            LIFTOFF.start(entity.tickCount);

            landingTrg = true;
            liftoffTrg = false;
        }
    }

    public void AnimateFlap()
    {
        if (timeout <= 1)
        {
            FLYING.start(entity.tickCount);
            timeout = duration;
        }
        else{
            --timeout;
        }
    }

    public void StopFlap()
    {
        if (timeout <= 1 ||timeout == 11)
        {
            FLYING.stop();
        }
        else {
            --timeout;
        };
    }



    //This part is for the spine control

    public void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, int size)
    {
            pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
            pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

            pNetHeadYaw /= size;
            pHeadPitch /= size;

            pNetHeadYaw *= (float) (Math.PI / 180.0);
            pHeadPitch *= (float) (Math.PI / 180.0);

            for (int i = 0; i < size; i++) {

                UpperSpine.get(i).xRot += pHeadPitch;
                UpperSpine.get(i).yRot += pNetHeadYaw;
            }
    }

    //basically the same as above
    public void applyTailRotation(float pNetTailYaw, float pTailPitch, int size)
    {
        pNetTailYaw = Mth.clamp(pNetTailYaw, -30.0F, 30.0F);
        pTailPitch = Mth.clamp(pTailPitch, -25.0F, 45.0F);

        pNetTailYaw /= size;
        pTailPitch /= size;

        pNetTailYaw *= (float) (Math.PI / 180.0);
        pTailPitch *= (float) (Math.PI / 180.0);

        for (int i = 0; i < size; i++) {

            LowerSpine.get(i).xRot += pTailPitch;
            LowerSpine.get(i).yRot += pNetTailYaw;
        }
    }

    public float angleStoppedMod = 0;
    public void bodyXRot(float stopAngle, boolean flying , boolean moving) {
        float stopStep = 1f;
        float flightStep = 1f;

        //Always reset to less than 360°
        angleStoppedMod %= 360;

        if (flying) {
            angleStoppedMod += (float) Lerp.interpolation(angleStoppedMod,moving ? 0 : stopAngle, stopStep);
        }
        else {
            angleStoppedMod += (float) Lerp.interpolation(angleStoppedMod,0, flightStep);
        }

        angleStoppedMod *= (Mth.PI/180f);
    }


    public void setupRotations(PoseStack ps, float trunkPitch, float offsY, float offsZ)
    {
        ps.translate(0,0,0);
        ps.translate(0, offsY, offsZ); // change rotation point
        ps.mulPose(Axis.XP.rotationDegrees(-(trunkPitch + angleStoppedMod)/(float) (Math.PI / 180.0))); // rotate near the saddle so we can support the player
        LogUtils.getLogger().debug(angleStoppedMod + "");
        ps.translate(0, -offsY, -offsZ); // restore rotation point
    }


}
