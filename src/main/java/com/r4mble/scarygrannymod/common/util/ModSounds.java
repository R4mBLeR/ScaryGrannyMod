package com.r4mble.scarygrannymod.common.util;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ScaryGrannyMod.MOD_ID);
    ;
    public static final RegistryObject<SoundEvent> GRANNY_DEATH =
            SOUNDS.register("granny_death", () -> new SoundEvent(new ResourceLocation(ScaryGrannyMod.MOD_ID, "granny_death")));
    ;


}
