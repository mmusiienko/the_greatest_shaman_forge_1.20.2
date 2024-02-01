package com.the_greatest_shaman.item;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheGreatestShaman.MODID);

    public static final RegistryObject<MobEffect> EXPERIENCE_SCALAR_EFFECT = MOD_EFFECTS.register(
            "wisdom", ExperienceScalarEffect::new
    );
    public static final RegistryObject<MobEffect> BILL_COLLECTOR_EFFECT = MOD_EFFECTS.register(
            "bill_collector", LootingEffect::new
    );

    public static void register(IEventBus eventBus) {
        MOD_EFFECTS.register(eventBus);
    }
}
