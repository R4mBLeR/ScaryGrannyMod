package com.r4mble.scarygrannymod.common.entity.custom;


import com.r4mble.scarygrannymod.common.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrannyEntity extends MonsterEntity {
    public GrannyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75.00D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 10.00D)
                .add(Attributes.ARMOR, 3.00D)
                .add(Attributes.ATTACK_SPEED, 5.00D)
                .add(Attributes.FOLLOW_RANGE, 24.00D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new SwimGoal(this));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0));

    }

    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
        ItemStack sword = ModItems.GRANNY_BAT.get().getDefaultInstance();
        sword.setDamageValue(random.nextInt(100));
        this.spawnAtLocation(sword);
    }

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 3 + this.level.random.nextInt(300);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITCH_AMBIENT;
    }


    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITCH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WITCH_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.20F, 0.5F);
    }
}
