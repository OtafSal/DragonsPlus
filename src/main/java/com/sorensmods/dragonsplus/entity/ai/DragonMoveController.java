package com.sorensmods.dragonsplus.entity.ai;

import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.entity.GenericDragon;
import com.sorensmods.dragonsplus.util.Lerp;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import org.jline.utils.Log;

public class DragonMoveController extends MoveControl
{
    private final GenericDragon dragon;
    float yaw = 0;

    public DragonMoveController(GenericDragon dragon)
    {
        super(dragon.entity);
        this.dragon = dragon;
    }



    @Override
    public void tick()
    {

        // original movement behavior if the entity isn't flying
        if (!dragon.isFlying())
        {
            super.tick();
            return;
        }

          if (hasWanted())
        {
            operation = MoveControl.Operation.WAIT;
            double xDif = wantedX - mob.getX();
            double yDif = wantedY - mob.getY();
            double zDif = wantedZ - mob.getZ();
            double sq = xDif * xDif + yDif * yDif + zDif * zDif;
            if (sq < (double) 2.5000003E-7F)
            {
                mob.setYya(0.0F);
                mob.setZza(0.0F);
                return;
            }


            float speed = (float) (speedModifier * mob.getAttributeValue(Attributes.FLYING_SPEED));
            double distSq = Math.sqrt(xDif * xDif + zDif * zDif);
            mob.setSpeed(speed);
            if (Math.abs(yDif) > (double) 1.0E-5F || Math.abs(distSq) > (double) 1.0E-5F)
                mob.setYya((float) yDif * speed);

            yaw += (float) Lerp.interpolation(yaw, (Mth.atan2(zDif, xDif) * (double) (180F / (float) Math.PI)) - 90.0F, 10);

            //mob.setYRot(rotlerp(mob.getYRot(), yaw, 6));
            //mob.setXRot(rotlerp(dragon.angleX, pitch, 6));

            mob.yHeadRot = yaw;
            // rotate body towards the head
            mob.setYRot(Mth.rotateIfNecessary(mob.yHeadRot, mob.getYRot(), 4));
        }
        else
        {
            mob.setYya(0);
            mob.setZza(0);
        }
    }
}