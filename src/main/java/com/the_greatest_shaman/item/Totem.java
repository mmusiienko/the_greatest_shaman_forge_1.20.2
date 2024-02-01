package com.the_greatest_shaman.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Totem extends Item {
    private final MobEffect mobEffect;
    public Totem(Properties pProperties, MobEffect mobEffect) {
        super(pProperties);
        this.mobEffect = mobEffect;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            for (ItemStack stack: player.getHandSlots()) {
                if (stack.is(this)) {
                    player.addEffect(new MobEffectInstance(mobEffect, 60));
                    break;
                }
            }
        }
    }


}
