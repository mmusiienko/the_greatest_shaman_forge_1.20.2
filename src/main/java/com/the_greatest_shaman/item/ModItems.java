package com.the_greatest_shaman.item;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheGreatestShaman.MODID);
    public static final RegistryObject<Item> BEAR_TOTEM = ITEMS.register("bear_totem",
            () -> new Totem(new Item.Properties(), MobEffects.DAMAGE_BOOST));
    public static final RegistryObject<Item> SALMON_TOTEM = ITEMS.register("salmon_totem",
            () -> new Totem(new Item.Properties(), MobEffects.DOLPHINS_GRACE));
    public static final RegistryObject<Item> PIPE_OF_PEACE = ITEMS.register("pipe_of_peace",
            () -> new PipeOfPeace(new Item.Properties()));
    public static final RegistryObject<Item> TOMAHAWK = ITEMS.register("tomahawk",
            () -> new Tomahawk(2.0f,
                    -3.1f,
                    Tiers.IRON,
                    BlockTags.MINEABLE_WITH_AXE,
                    new Item.Properties().defaultDurability(300)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
