package com.r4mble.scarygrannymod.common.item;

import com.r4mble.scarygrannymod.ScaryGrannyMod;
import com.r4mble.scarygrannymod.common.ModItemTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ScaryGrannyMod.MOD_ID);
    public static final RegistryObject<Item> ASHES = ITEMS.register("ashes",
            () -> new Item(basicProperties()));
    public static final RegistryObject<Item> TOTEM_OF_GRANNY = ITEMS.register("totem_of_granny",
            () -> new TotemOfGranny(basicProperties().stacksTo(1)));
    public static final RegistryObject<Item> ASHES_DIAMOND = ITEMS.register("ashes_diamond",
            () -> new Item(basicProperties()));
    public static final RegistryObject<Item> GRANNY_BAT = ITEMS.register("granny_bat",
            () -> new SwordItem(Tiers.IRON, 10, 1.6f, basicProperties().defaultDurability(100)));

    public static Item.Properties basicProperties() {
        return new Item.Properties().tab(ModItemTabs.SCARY_GRANNY_MOD_TAB);
    }
}
