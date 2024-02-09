package com.r4mble.grannymod.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class TotemOfGranny extends Item {
    public TotemOfGranny(Properties properties)
    {
        super(properties);
    }
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        ItemStack totem = context.getItemInHand();
        world.setRainLevel(10);
        world.setThunderLevel(10);
        totem.setCount(0);
        PigEntity pig = new PigEntity(EntityType.PIG, world);
        pig.moveTo(context.getClickLocation());
        world.addFreshEntity(pig);

        return super.onItemUseFirst(stack,context);
    }
}
