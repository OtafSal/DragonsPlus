package com.sorensmods.dragonsplus.item;

import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ModItems = DeferredRegister.create(ForgeRegistries.ITEMS, DragonsPlus.MOD_ID);

    //Ender Dragon spawn egg
    public static final RegistryObject<Item> EndDragonEgg = ModItems.register("modenderdragon_spawnegg", () ->
            new ForgeSpawnEggItem(ModEntities.ModEnderDragonRegister,0x171717, 0xDF00FF, new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ModItems.register(eventBus);
    }
}
