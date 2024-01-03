package com.the_greatest_shaman.event;



import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.ModEntities;
import com.the_greatest_shaman.entity.mob.RedskinWarrior;
import com.the_greatest_shaman.particle.FireParticle;
import com.the_greatest_shaman.particle.ModParticles;
import com.the_greatest_shaman.particle.SmokeParticle;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = TheGreatestShaman.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerParticleProviders(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.FIRE_PARTICLE.get(),
                FireParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.SMOKE_PARTICLE.get(),
                SmokeParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.REDSKIN_WARRIOR.get(), RedskinWarrior.createAttributes().build());
    }
}