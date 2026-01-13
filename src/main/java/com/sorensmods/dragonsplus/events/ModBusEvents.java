package com.sorensmods.dragonsplus.events;

import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.custom.ModEnderDragon;
import com.sorensmods.dragonsplus.entity.ModEntities;
import com.sorensmods.dragonsplus.entity.client.enderdragonRendering.ModEnderDragonModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = DragonsPlus.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ModEnderDragonModel.LAYER_LOCATION, ModEnderDragonModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.ModEnderDragonRegister.get(), ModEnderDragon.Properties().build());
    }
}
