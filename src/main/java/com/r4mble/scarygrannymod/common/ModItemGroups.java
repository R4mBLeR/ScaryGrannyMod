package com.r4mble.scarygrannymod.common;

import com.r4mble.scarygrannymod.common.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
    public static final ItemGroup GRANNY_MOD_TAB = new ItemGroup("mod_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.ASHES.get());
        }
    };
}

