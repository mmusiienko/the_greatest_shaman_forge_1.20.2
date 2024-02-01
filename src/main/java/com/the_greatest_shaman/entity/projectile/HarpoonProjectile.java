package com.the_greatest_shaman.entity.projectile;

import com.the_greatest_shaman.entity.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class HarpoonProjectile extends AbstractArrow {

    public HarpoonProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected HarpoonProjectile(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public HarpoonProjectile(LivingEntity pShooter, Level pLevel) {
        super(ModEntities.HOOK_PROJECTILE.get(), pShooter, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!level().isClientSide()) {
            level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, getOwner()), 5f);
        level().playSound(null, pResult.getLocation().x, pResult.getLocation().y, pResult.getLocation().z, SoundEvents.TRIDENT_HIT, SoundSource.PLAYERS, 1f, 1f);
        if (getOwner() != null) {

            pResult.getEntity().setDeltaMovement((getOwner().getOnPos().getCenter().subtract(entity.getOnPos().getCenter())).normalize().multiply(2, 2, 2));
        }
    }
}
