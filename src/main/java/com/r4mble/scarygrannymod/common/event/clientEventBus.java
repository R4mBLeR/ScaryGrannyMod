package com.r4mble.scarygrannymod.common.event;


import com.r4mble.scarygrannymod.ScaryGrannyMod;
import com.r4mble.scarygrannymod.common.entity.custom.GrannyEntity;
import com.r4mble.scarygrannymod.common.entity.custom.ModEntities;
import com.r4mble.scarygrannymod.common.entity.model.GrannyModel;
import com.r4mble.scarygrannymod.common.entity.render.GrannyRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ScaryGrannyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class clientEventBus {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GRANNY.get(), GrannyEntity.setCustomAttributes());
    }

    @SubscribeEvent
    public static void EntityRegisterLayersEvent(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GrannyModel.LAYER_LOCATION, GrannyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void EntityRegisterRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.GRANNY.get(), GrannyRender::new);
    }

}
