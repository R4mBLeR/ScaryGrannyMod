package com.r4mble.scarygrannymod.common;

import com.r4mble.scarygrannymod.common.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemTabs extends CreativeModeTab {
    public static final ModItemTabs SCARY_GRANNY_MOD_TAB = new ModItemTabs(CreativeModeTab.TABS.length, "mod_tab");

    public ModItemTabs(int length, String label) {
        super(length, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.ASHES.get());
    }
}

