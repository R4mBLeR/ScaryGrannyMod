package com.r4mble.grannymod.common.item;

import com.r4mble.grannymod.common.entity.custom.GrannyEntity;
import com.r4mble.grannymod.common.entity.custom.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
        GrannyEntity granny = new GrannyEntity(ModEntities.GRANNY.get(), world);
        granny.moveTo(context.getClickLocation());
        ItemStack sword = ModItems.GRANNY_SWORD.get().getDefaultInstance();
        granny.setItemInHand(Hand.MAIN_HAND, sword);
        world.addFreshEntity(granny);

        return super.onItemUseFirst(stack,context);
    }
}
