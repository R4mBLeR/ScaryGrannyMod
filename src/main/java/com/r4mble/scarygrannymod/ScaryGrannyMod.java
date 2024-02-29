package com.r4mble.scarygrannymod;

import com.r4mble.scarygrannymod.common.ModItemTabs;
import com.r4mble.scarygrannymod.common.entity.custom.ModEntities;
import com.r4mble.scarygrannymod.common.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(ScaryGrannyMod.MOD_ID)
public class ScaryGrannyMod {
    public static final String MOD_ID = "scarygrannymod";

    public ScaryGrannyMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegister(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void ModRegister(IEventBus eventBus) {
        ModItemTabs.MOD_TABS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModEntities.ENTITIES.register(eventBus);
    }
}
