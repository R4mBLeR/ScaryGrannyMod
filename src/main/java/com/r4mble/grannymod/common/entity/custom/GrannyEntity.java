package com.r4mble.grannymod.common.entity.custom;


import com.r4mble.grannymod.common.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrannyEntity extends ZombieEntity {
    public GrannyEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.00D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.00D)
                .createMutableAttribute(Attributes.ARMOR, 3.00D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 5.00D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 24.00D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true ) );
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
    }

    protected void dropSpecialItems(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropSpecialItems(p_213333_1_, p_213333_2_, p_213333_3_);
        ItemStack sword = ModItems.GRANNY_BAT.get().getDefaultInstance();
        this.entityDropItem(sword);
    }

    @Override
    public boolean isDrowning()
    {
        return false;
    }
    @Override
    protected int getExperiencePoints(PlayerEntity player)
    {
        return 3 + this.world.rand.nextInt(300);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.20F, 0.5F);
    }
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }
}
