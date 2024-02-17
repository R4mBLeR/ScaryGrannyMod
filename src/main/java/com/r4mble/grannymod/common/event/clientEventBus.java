package com.r4mble.grannymod.common.event;


import com.r4mble.grannymod.GrannyMod;
import com.r4mble.grannymod.common.entity.custom.GrannyEntity;
import com.r4mble.grannymod.common.entity.custom.ModEntities;
import com.r4mble.grannymod.common.entity.render.GrannyRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GrannyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class clientEventBus {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GRANNY.get(), GrannyEntity.setCustomAttributes().build());
    }
}
