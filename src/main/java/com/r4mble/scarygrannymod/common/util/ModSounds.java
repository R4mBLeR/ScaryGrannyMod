package com.r4mble.scarygrannymod.common.util;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ScaryGrannyMod.MOD_ID);
    public static final RegistryObject<SoundEvent> GRANNY_DEATH =
            SOUNDS.register("granny_death", () -> new SoundEvent(new ResourceLocation(ScaryGrannyMod.MOD_ID, "granny_death")));

}
