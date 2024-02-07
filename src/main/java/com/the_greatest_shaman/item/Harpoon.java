package com.the_greatest_shaman.item;

import com.the_greatest_shaman.entity.projectile.HarpoonProjectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class Harpoon extends Item {
    public Harpoon(Properties pProperties) {
        super(pProperties);
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
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        int i = this.getUseDuration(pStack) - pTimeCharged;
        if (i >= 14) {
            pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(),
                    SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                HarpoonProjectile harpoon = new HarpoonProjectile(pLivingEntity, pLevel, pStack);
                pStack.getOrCreateTag().putBoolean("thrown", true);
                harpoon.shootFromRotation(pLivingEntity, pLivingEntity.getXRot(), pLivingEntity.getYRot(), 0, 3f, 1.0F);
                pLevel.addFreshEntity(harpoon);
            }
            if (pLivingEntity instanceof Player player) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack)
    {
        return UseAnim.SPEAR;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        CompoundTag tag = pPlayer.getItemInHand(pHand).getTag();
        if (tag == null || tag.getBoolean("thrown")) {
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
        }
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pHand));
    }

}
