package com.the_greatest_shaman.item;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.ModEntities;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> WISE_TREE_TOTEM = ITEMS.register("wise_tree_totem",
            () -> new Totem(new Item.Properties(), ModMobEffects.EXPERIENCE_SCALAR_EFFECT.get()));
    public static final RegistryObject<Item> PIPE_OF_PEACE = ITEMS.register("pipe_of_peace",
            () -> new PipeOfPeace(new Item.Properties()));
    public static final RegistryObject<Item> TOMAHAWK = ITEMS.register("tomahawk",
            () -> new Tomahawk(
                    2.0f,
                    -3.1f,
                    Tiers.IRON,
                    BlockTags.MINEABLE_WITH_AXE,
                    new Item.Properties().defaultDurability(300))
    );
    public static final RegistryObject<Item> FEATHER_HAT = ITEMS.register(
            "feather_hat",
            () -> new CustomHeadItem(new Item.Properties())
    );
    public static final RegistryObject<Item> CHIEFTAINS_HAT = ITEMS.register(
            "chieftains_hat",
            () -> new CustomHeadItem(new Item.Properties())
    );
    public static final RegistryObject<Item> REDSKIN_SPEAR = ITEMS.register(
            "redskin_spear",
            () -> new CustomSwordItem(Tiers.IRON, 3, -1f, new Item.Properties())
    );

    public static final RegistryObject<Item> REDSKIN_SHIELD = ITEMS.register(
            "redskin_shield",
            () -> new CustomShieldItem(new Item.Properties().defaultDurability(200))
    );
    public static final RegistryObject<Item> HARPOON = ITEMS.register(
            "harpoon",
            () -> new Harpoon(new Item.Properties())
    );
    public static final RegistryObject<Item> REDSKIN_THROWER_SPAWN_EGG = ITEMS.register(
            "redskin_thrower_spawn_egg",
            () -> new CustomSpawnEgg(ModEntities.REDSKIN_THROWER, new Item.Properties())
    );
    public static final RegistryObject<Item> REDSKIN_WARRIOR_SPAWN_EGG = ITEMS.register(
            "redskin_warrior_spawn_egg",
            () -> new CustomSpawnEgg(ModEntities.REDSKIN_WARRIOR, new Item.Properties())
    );
    public static final RegistryObject<Item> REDSKIN_CHIEFTAIN_SPAWN_EGG = ITEMS.register(
            "redskin_chieftain_spawn_egg",
            () -> new CustomSpawnEgg(ModEntities.REDSKIN_CHIEFTAIN, new Item.Properties())
    );
    public static final RegistryObject<Item> EAGLE_STAFF = ITEMS.register(
            "eagle_staff",
            () -> new EagleStaff(new Item.Properties())
    );
    public static final RegistryObject<Item> MEDALLION_OF_DEATH = ITEMS.register(
            "medallion_of_death",
            () -> new Totem(new Item.Properties(), ModMobEffects.BILL_COLLECTOR_EFFECT.get())
    );
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
