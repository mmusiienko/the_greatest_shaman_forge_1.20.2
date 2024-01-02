package com.the_greatest_shaman.particle;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheGreatestShaman.MODID);

    public static final RegistryObject<SimpleParticleType> FIRE_PARTICLE = PARTICLE_TYPES.register("fire_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SMOKE_PARTICLE = PARTICLE_TYPES.register("smoke_particle", () -> new SimpleParticleType(true));
    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
