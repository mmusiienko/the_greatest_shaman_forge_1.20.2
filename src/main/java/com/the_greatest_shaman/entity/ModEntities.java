package com.the_greatest_shaman.entity;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.mob.RedskinWarrior;
import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheGreatestShaman.MODID);
    public static final RegistryObject<EntityType<TomahawkProjectile>> TOMAHAWK_PROJECTILE =
            ENTITY_TYPES.register("tomahawk_projectile",
                    () -> EntityType.Builder.<TomahawkProjectile>of(TomahawkProjectile::new, MobCategory.MISC).sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build("tomahawk_projectile"));

    public static final RegistryObject<EntityType<RedskinWarrior>> REDSKIN_WARRIOR =
            ENTITY_TYPES.register("redskin_warrior",
                    () -> EntityType.Builder.of(RedskinWarrior::new, MobCategory.MISC).sized(0.6F, 1.8F).build(new ResourceLocation(TheGreatestShaman.MODID, "redskin_warrior").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
