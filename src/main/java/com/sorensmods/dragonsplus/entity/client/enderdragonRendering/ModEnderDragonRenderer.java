package com.sorensmods.dragonsplus.entity.client.enderdragonRendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.custom.ModEnderDragon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class ModEnderDragonRenderer extends MobRenderer<ModEnderDragon, ModEnderDragonModel<ModEnderDragon>> {
    public ModEnderDragonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ModEnderDragonModel<>(pContext.bakeLayer(ModEnderDragonModel.LAYER_LOCATION)), 1.5f);

        addLayer(SADDLE_LAYER);
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
    public final RenderLayer<ModEnderDragon, ModEnderDragonModel<ModEnderDragon>> SADDLE_LAYER = new RenderLayer<>(this)
    {
        @Override
        public void render(PoseStack ps, MultiBufferSource buffer, int light, ModEnderDragon dragon, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
        {
            if (dragon.isSaddled())
                renderColoredCutoutModel(model, ResourceLocation.fromNamespaceAndPath(DragonsPlus.MOD_ID, "textures/entity/modenderdragon/modenderdragonsaddle.png"), ps, buffer, light, dragon, -1);
        }
    };

    @Override
    public ResourceLocation getTextureLocation(ModEnderDragon pEntity) {
        return ResourceLocation.fromNamespaceAndPath(DragonsPlus.MOD_ID, "textures/entity/modenderdragon/modenderdragonbody.png");
    }

    @Override
    protected void setupRotations(ModEnderDragon pEntity, PoseStack pPoseStack, float pBob, float pYBodyRot, float pPartialTick, float pScale) {
        super.setupRotations(pEntity, pPoseStack, pBob, pYBodyRot, pPartialTick, pScale);

        pPoseStack.translate(0, pEntity.anims.setYOfs(pEntity.isInSittingPose(), -1.5f, 0.015f), 0);
        pEntity.anims.setupRotations(pPoseStack, 3.5f, 0f);
    }
}
