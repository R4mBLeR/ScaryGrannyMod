package com.r4mble.scarygrannymod.common.entity.custom;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ScaryGrannyMod.MOD_ID);
    public static final RegistryObject<EntityType<GrannyEntity>> GRANNY = ENTITIES.register("granny",
            () -> EntityType.Builder.of(GrannyEntity::new,
                            MobCategory.MISC).sized(1, 2).fireImmune()
                    .build(new ResourceLocation(ScaryGrannyMod.MOD_ID, "granny").toString()));
}

