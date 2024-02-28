package com.r4mble.scarygrannymod.common.item;

import com.r4mble.scarygrannymod.common.entity.custom.GrannyEntity;
import com.r4mble.scarygrannymod.common.entity.custom.ModEntities;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TotemOfGranny extends Item {
    public TotemOfGranny(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level world = context.getLevel();
        ItemStack totem = context.getItemInHand();
        world.setRainLevel(10);
        world.setThunderLevel(10);
        totem.setCount(0);
        GrannyEntity granny = new GrannyEntity(ModEntities.GRANNY.get(), world);
        granny.moveTo(context.getClickLocation());
        world.addFreshEntity(granny);
        return super.onItemUseFirst(stack, context);
    }
}
