package com.the_greatest_shaman.item;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ExperienceScalarEffect extends MobEffect {

    public ExperienceScalarEffect() {
        super(MobEffectCategory.BENEFICIAL, 3);
    }

    @Mod.EventBusSubscriber(modid = TheGreatestShaman.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    private static class ExperienceGainHandler {

        @SubscribeEvent
        public static void onExperienceGain(PlayerXpEvent.PickupXp event) {
            if (event.getEntity().hasEffect(ModMobEffects.EXPERIENCE_SCALAR_EFFECT.get())) {
                event.getEntity().giveExperiencePoints((int)(event.getOrb().getValue() * 0.2f));
            }
        }
    }
}
