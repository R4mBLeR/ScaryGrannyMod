package com.r4mble.grannymod.common.entity.custom;


import com.r4mble.grannymod.common.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.Random;

public class GrannyEntity extends ZombieEntity {
    public GrannyEntity(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_) {
        super(p_i48549_1_, p_i48549_2_);
        this.xpReward = 1000;

    }
    public static  AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return  MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.00D)
                .add(Attributes.ATTACK_DAMAGE, 10.00D)
                .add(Attributes.ARMOR, 10.00D);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true ) );
        this.goalSelector.addGoal( 2, new NearestAttackableTargetGoal<>( this, MonsterEntity.class, true ) );
        this.goalSelector.addGoal( 3, new NearestAttackableTargetGoal<>( this, AnimalEntity.class, true ) );
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(4, new ZombieAttackGoal(this, 1.0D, false));
    }
    protected boolean convertsInWater() {
        return false;
    }
    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
        ItemStack itemStack = Items.DIAMOND.getDefaultInstance();
        ItemStack sword = ModItems.GRANNY_SWORD.get().getDefaultInstance();
        Random random = new Random();
        int diamondCount = random.nextInt(32);
        this.spawnAtLocation(itemStack, diamondCount);
        this.spawnAtLocation(sword, 1);
    }
}