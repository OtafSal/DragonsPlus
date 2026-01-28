package com.sorensmods.dragonsplus.entity.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class CustomRenderTypes extends RenderType
{
    //private static final RenderType DISSOLVE = RenderType.dragonExplosionAlpha(DISSOLVE_TEXTURE);
    private static final Function<ResourceLocation, RenderType> GLOW_FUNC = Util.memoize(texture -> create("eyes", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, CompositeState.builder()
            .setShaderState(RENDERTYPE_EYES_SHADER)
            .setTextureState(new TextureStateShard(texture, false, false))
            .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
            .setWriteMaskState(COLOR_WRITE)
            .createCompositeState(false)));

    public static RenderType glow(ResourceLocation texture)
    {
        return GLOW_FUNC.apply(texture);
    }

    @SuppressWarnings("DataFlowIssue")
    private CustomRenderTypes()
    {
        // dummy
        super(null, null, null, 0, false, true, null, null);
    }
}

