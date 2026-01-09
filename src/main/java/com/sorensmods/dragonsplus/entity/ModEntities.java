package com.sorensmods.dragonsplus.entity;

import com.sorensmods.dragonsplus.DragonsPlus;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ModEntityTypes =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DragonsPlus.MOD_ID);

    //Register ender dragon entity type
    public static final RegistryObject<EntityType<ModEnderDragon>> ModEnderDragonRegister = ModEntityTypes.register("modenderdragon",
            () -> EntityType.Builder.of(ModEnderDragon::new, MobCategory.CREATURE)
                    .sized(3f, 3f)
                    .build("modenderdragon"));



    public static void register(IEventBus eventBus)
    {
        ModEntityTypes.register(eventBus);
    }
}
