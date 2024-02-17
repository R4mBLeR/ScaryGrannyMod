package com.r4mble.grannymod.common.entity.custom;


import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.function.Predicate;

public class GrannyEntity extends ZombieEntity {
    private static final DataParameter<Boolean> DATA_BABY_ID;
    private static final DataParameter<Integer> DATA_SPECIAL_TYPE_ID;
    private static final DataParameter<Boolean> DATA_DROWNED_CONVERSION_ID;
    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE;
    private final BreakDoorGoal breakDoorGoal;
    private boolean canBreakDoors;
    private int inWaterTime;
    private int conversionTime;
    public GrannyEntity(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_) {
        super(p_i48549_1_, p_i48549_2_);
        this.breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
        this.xpReward = 1000;
    }
    public static  AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return  MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.00D)
                .add(Attributes.MOVEMENT_SPEED, 1.00D)
                .add(Attributes.ATTACK_DAMAGE, 20.00D)
                .add(Attributes.ARMOR, 10.00D)
                .add(Attributes.ATTACK_SPEED, 10.00D)
                .add(Attributes.FOLLOW_RANGE, 16.00D);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new OpenDoorGoal(this, false));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_BABY_ID, false);
        this.getEntityData().define(DATA_SPECIAL_TYPE_ID, 0);
        this.getEntityData().define(DATA_DROWNED_CONVERSION_ID, false);
    }

    public boolean isUnderWaterConverting() {
        return (Boolean)this.getEntityData().get(DATA_DROWNED_CONVERSION_ID);
    }

    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }

    public void setCanBreakDoors(boolean p_146070_1_) {
        if (this.supportsBreakDoorGoal() && GroundPathHelper.hasGroundPathNavigation(this)) {
            if (this.canBreakDoors != p_146070_1_) {
                this.canBreakDoors = p_146070_1_;
                ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(p_146070_1_);
                if (p_146070_1_) {
                    this.goalSelector.addGoal(1, this.breakDoorGoal);
                } else {
                    this.goalSelector.removeGoal(this.breakDoorGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.removeGoal(this.breakDoorGoal);
            this.canBreakDoors = false;
        }

    }
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return this.isInvulnerableTo(p_70097_1_) ? false : super.hurt(p_70097_1_, p_70097_2_);
    }
    protected boolean supportsBreakDoorGoal() {
        return true;
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_BABY_ID.equals(p_184206_1_)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlotType.HEAD);
                            this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }

        super.aiStep();
    }

    private void startUnderWaterConversion(int p_204704_1_) {
        this.conversionTime = p_204704_1_;
        this.getEntityData().set(DATA_DROWNED_CONVERSION_ID, true);
    }
    protected boolean isSunSensitive() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        if (this.random.nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.random.nextInt(3);
            if (i == 0) {
                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else {
                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
            }
        }

    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("IsBaby", this.isBaby());
        p_213281_1_.putBoolean("CanBreakDoors", this.canBreakDoors());
        p_213281_1_.putInt("InWaterTime", this.isInWater() ? this.inWaterTime : -1);
        p_213281_1_.putInt("DrownedConversionTime", this.isUnderWaterConverting() ? this.conversionTime : -1);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBaby(p_70037_1_.getBoolean("IsBaby"));
        this.setCanBreakDoors(p_70037_1_.getBoolean("CanBreakDoors"));
        this.inWaterTime = p_70037_1_.getInt("InWaterTime");
        if (p_70037_1_.contains("DrownedConversionTime", 99) && p_70037_1_.getInt("DrownedConversionTime") > -1) {
            this.startUnderWaterConversion(p_70037_1_.getInt("DrownedConversionTime"));
        }

    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 1.74F;
    }
    protected void handleAttributes(float p_207304_1_) {
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * 0.05000000074505806, AttributeModifier.Operation.ADDITION));
        double d0 = this.random.nextDouble() * 1.5 * (double)p_207304_1_;
        if (d0 > 1.0) {
            this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random zombie-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        if (this.random.nextFloat() < p_207304_1_ * 0.05F) {
            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25 + 0.5, AttributeModifier.Operation.ADDITION));
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0 + 1.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
            this.setCanBreakDoors(this.supportsBreakDoorGoal());
        }

    }

    public double getMyRidingOffset() {
        return -0.45;
    }

    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
        Entity entity = p_213333_1_.getEntity();
        ItemStack itemstack = Items.DIAMOND.getDefaultInstance();
        this.spawnAtLocation(itemstack, 32);
    }

    static {
        DATA_BABY_ID = EntityDataManager.defineId(ZombieEntity.class, DataSerializers.BOOLEAN);
        DATA_SPECIAL_TYPE_ID = EntityDataManager.defineId(ZombieEntity.class, DataSerializers.INT);
        DATA_DROWNED_CONVERSION_ID = EntityDataManager.defineId(ZombieEntity.class, DataSerializers.BOOLEAN);
        DOOR_BREAKING_PREDICATE = (p_213697_0_) -> {
            return p_213697_0_ == Difficulty.HARD;
        };
    }

}