package com.the_greatest_shaman;

import com.the_greatest_shaman.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheGreatestShaman.MODID);

    public static final RegistryObject<CreativeModeTab> THE_GREATEST_SHAMAN_TAB = TABS.register(
        "the_greatest_shaman_main_tab",
            () -> CreativeModeTab.builder()
                    .icon(ModItems.TOMAHAWK.get()::getDefaultInstance)
                    .title(Component.translatable("the_greatest_shaman_main_tab"))
                    .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
