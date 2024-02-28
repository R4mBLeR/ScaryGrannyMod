package com.r4mble.scarygrannymod.common.entity.custom;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITITES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, ScaryGrannyMod.MOD_ID);
    public static final RegistryObject<EntityType<GrannyEntity>> GRANNY = ENTITITES.register("granny",
            () -> EntityType.Builder.of(GrannyEntity::new,
                            EntityClassification.MISC).sized(1, 2).fireImmune()
                    .build(new ResourceLocation(ScaryGrannyMod.MOD_ID, "granny").toString()));
}

