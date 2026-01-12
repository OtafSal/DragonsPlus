package com.sorensmods.dragonsplus.entity;

import net.minecraft.world.entity.AnimationState;
import org.jetbrains.annotations.Nullable;

public class DragonAnimController {

    DragonAnimController()
    {
        IDLE = new DragonState(0);
        WALKING = new DragonState(0);
        SIT = new DragonState(0);
        FLYING = new DragonState(0);
        SOARING = new DragonState(0);
        FLYING_STILL = new DragonState(0);
    }

    private DragonState last = new DragonState(0);

    public class DragonState {
        public final AnimationState animation = new AnimationState();

        private int animTimeout = 0;
        public int animDuration;

        DragonState(int durationGet)
        {
            this.animDuration = durationGet;
        }

        public void Animate(int tick)
        {
            if (animTimeout <= 0) {
                last.animation.stop();
                last.animTimeout = 0;

                animation.start(tick);
                last = this;

                animTimeout = animDuration;
            } else {
                --animTimeout;
            }
        }
    }

    public DragonState IDLE, WALKING, SIT, FLYING, SOARING, FLYING_STILL;



}
