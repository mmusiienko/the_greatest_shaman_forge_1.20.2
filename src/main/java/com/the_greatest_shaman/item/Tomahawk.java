package com.the_greatest_shaman.item;

import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;

import java.util.*;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class Tomahawk extends DiggerItem {
    boolean isMainHandTime;
    public Tomahawk(float pAttackDamageModifier, float pAttackSpeedModifier, Tier pTier, TagKey<Block> pBlocks, Item.Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, pBlocks, pProperties);
        this.isMainHandTime = true;
    }

    private void changeIsMainHandTimeImmediatly() {
        this.isMainHandTime = !this.isMainHandTime;
    }
    private void changeIsMainHandTime() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                changeIsMainHandTimeImmediatly();
                timer.cancel();
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (ItemStack.isSameItemSameTags(entity.getItemInHand(InteractionHand.OFF_HAND), stack))  {
            return false;
        }
        return !this.isMainHandTime;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!entity.level().isClientSide) {
            return !isMainHandTime;
        }

        if (!isMainHandTime) {
            player.swing(InteractionHand.OFF_HAND);
            changeIsMainHandTime();
            return true;
        }

        return false;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!pLevel.isClientSide) {
            TomahawkProjectile tomahawk = new TomahawkProjectile(pPlayer, pLevel, itemstack, pHand);
            tomahawk.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), -5f, 1.5f, 1.0F);
            pPlayer.setItemInHand(pHand, new ItemStack(Items.AIR, 0));
            pLevel.addFreshEntity(tomahawk);
        }
        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }



    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            for (ItemStack stack: player.getHandSlots()) {
                if (stack.is(this)) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60));
                    break;
                }
            }
        }

    }
}

