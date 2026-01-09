package com.sorensmods.dragonsplus.entity.client.enderdragonRendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.ModEnderDragon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModEnderDragonRenderer extends MobRenderer<ModEnderDragon, ModEnderDragonModel<ModEnderDragon>> {
    public ModEnderDragonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ModEnderDragonModel<>(pContext.bakeLayer(ModEnderDragonModel.LAYER_LOCATION)), 1.5f);
    }

    @Override
    public void render(ModEnderDragon pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
        {
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }
        else
        {
            pPoseStack.scale(1f,1f,1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ModEnderDragon pEntity) {
        return ResourceLocation.fromNamespaceAndPath(DragonsPlus.MOD_ID, "textures/entity/modenderdragon/modenderdragonbody.png");
    }
}
