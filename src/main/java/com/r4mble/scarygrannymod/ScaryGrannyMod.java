package com.r4mble.scarygrannymod;

import com.r4mble.scarygrannymod.common.entity.custom.ModEntities;
import com.r4mble.scarygrannymod.common.entity.render.GrannyRender;
import com.r4mble.scarygrannymod.common.item.ModItems;
import com.r4mble.scarygrannymod.common.util.ModSounds;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(ScaryGrannyMod.MOD_ID)
public class ScaryGrannyMod {
    public static final String MOD_ID = "scarygrannymod";
    private static final Logger LOGGER = LogManager.getLogger();

    public ScaryGrannyMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModRegister(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void ModRegister(IEventBus eventBus) {
        ModItems.ITEMS.register(eventBus);
        ModEntities.ENTITITES.register(eventBus);
        ModSounds.SOUNDS.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SCARYGRANNYMOD");
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GRANNY.get(), GrannyRender::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            LOGGER.info("HELLO from Register Block");
        }
    }
}
