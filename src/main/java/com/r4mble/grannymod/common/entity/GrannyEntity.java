package com.r4mble.grannymod.common.entity;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;

public class GrannyEntity extends ZombieEntity {
    public GrannyEntity(EntityType<? extends GrannyEntity> type, World worldIn) {
        super(type, worldIn);
    }
    /*
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.add(Attributes.MAX_HEALTH, 50.0).add(Attributes.FOLLOW_RANGE, 50.0).add(Attributes.MOVEMENT_SPEED, 0.4).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ARMOR, 5.0).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }*/
}