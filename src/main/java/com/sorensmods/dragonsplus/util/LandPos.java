package com.sorensmods.dragonsplus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class LandPos {

    private final BlockPos posBlock;

    public Vec3 wantedPos;

    public LandPos(Vec3 pos, PathfinderMob entity)
    {
        posBlock = BlockPos.containing(pos);
        //Return original position if failed to find ground
        wantedPos = pos;

        for (int i = posBlock.getY(); i > entity.level().getMinBuildHeight(); i--)
        {
            BlockPos attempt = new BlockPos(posBlock.getX(), i, posBlock.getZ());

            if (entity.level().getBlockState(attempt).entityCanStandOn(entity.level(), attempt, entity)) {
                wantedPos = attempt.getCenter();
                return;
            }
        }
    }
}
