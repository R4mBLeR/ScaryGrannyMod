package com.r4mble.scarygrannymod.common.event;


import com.r4mble.scarygrannymod.ScaryGrannyMod;
import com.r4mble.scarygrannymod.common.entity.custom.GrannyEntity;
import com.r4mble.scarygrannymod.common.entity.custom.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ScaryGrannyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class clientEventBus {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GRANNY.get(), GrannyEntity.setCustomAttributes().build());
    }
}
