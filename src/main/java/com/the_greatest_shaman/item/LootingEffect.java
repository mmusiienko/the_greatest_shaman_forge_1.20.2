package com.the_greatest_shaman.item;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class LootingEffect extends MobEffect {

    public LootingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3);
    }

    @Mod.EventBusSubscriber(modid = TheGreatestShaman.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    private static class MobSlainHandler {

        @SubscribeEvent
        public static void onMobSlainHandler(LivingDropsEvent event) {
            if (event.getEntity().level().isClientSide()) return;
            if (event.getSource().getEntity() instanceof LivingEntity entity) {
                if (!entity.hasEffect(ModMobEffects.BILL_COLLECTOR_EFFECT.get())) {
                    return;
                }
                for (ItemEntity itemEntityDropped: event.getDrops()) {
                    ItemEntity itemEntity = itemEntityDropped.copy();
                    ItemStack item = itemEntity.getItem();
                    boolean addOne = entity.getRandom().nextInt(4) == 0;
                    item.setCount(addOne ? 1 : 0);
                    itemEntity.setItem(item);
                    entity.level().addFreshEntity(itemEntity);
                }
            }
        }
    }
}