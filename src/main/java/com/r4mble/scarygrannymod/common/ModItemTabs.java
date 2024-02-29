package com.r4mble.scarygrannymod.common;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import com.r4mble.scarygrannymod.common.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItemTabs extends CreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScaryGrannyMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SCARY_GRANNY_MOD_TAB = MOD_TABS.register("mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ASHES.get()))
                    .title(Component.translatable("itemGroup.mod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ASHES.get());
                        pOutput.accept(ModItems.TOTEM_OF_GRANNY.get());
                        pOutput.accept(ModItems.ASHES_DIAMOND.get());
                        pOutput.accept(ModItems.GRANNY_BAT.get());

                    })
                    .build());

    protected ModItemTabs(Builder builder) {
        super(builder);
    }
}

