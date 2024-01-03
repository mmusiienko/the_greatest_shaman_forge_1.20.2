package com.the_greatest_shaman.entity.projectile;

import com.the_greatest_shaman.entity.ModEntities;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectile extends AbstractArrow {
    private ItemStack tomahawk = new ItemStack(ModItems.TOMAHAWK.get());
    private LivingEntity shooter;
    private InteractionHand hand = InteractionHand.MAIN_HAND;
    private boolean rotating = true;
    private boolean topFaceHit = false;
    private boolean bottomFaceHit = false;
    public TomahawkProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TomahawkProjectile(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public TomahawkProjectile(LivingEntity pShooter, EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public TomahawkProjectile(LivingEntity pShooter, Level pLevel, ItemStack tomahawk, InteractionHand hand) {
        super(ModEntities.TOMAHAWK_PROJECTILE.get(), pShooter, pLevel);
        this.tomahawk = tomahawk.copy();
        this.shooter = pShooter;
        this.hand = hand;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!level().isClientSide()) {
            level().broadcastEntityEvent(this, (byte)3);
        }
        VoxelShape shape = level().getBlockState(pResult.getBlockPos()).getCollisionShape(level(), pResult.getBlockPos());
        rotating = false;
        topFaceHit = (shape.max(Direction.Axis.Y) == pResult.getLocation().y - pResult.getBlockPos().getY());
        bottomFaceHit = (shape.min(Direction.Axis.Y) == pResult.getLocation().y - pResult.getBlockPos().getY());
    }

    
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 5f);
        entity.invulnerableTime = 0;
        rotating = false;
        level().playSound(null, pResult.getLocation().x, pResult.getLocation().y, pResult.getLocation().z, SoundEvents.TRIDENT_HIT, SoundSource.PLAYERS, 1f, 1f);
        if (shooter != null) {
            tomahawk.hurtAndBreak(1, shooter, (p_41303_) -> {
                p_41303_.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                level().playSound(null, pResult.getEntity().getOnPos(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1f, 1f);
                discard();
            });
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.1D, -0.1D, -0.1D));
    }
    @Override
    public void playerTouch(@NotNull Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            if (!this.level().isClientSide && (this.inGround || this.isNoPhysics()) && this.shakeTime <= 0) {
                if (this.tryPickup(pEntity)) {
                    pEntity.take(this, 1);
                    this.discard();
                }

            }
        }
    }
    protected boolean tryPickup(@NotNull Player pPlayer) {
        return switch (this.pickup) {
            case ALLOWED -> handleItemTransfer(pPlayer);
            case CREATIVE_ONLY -> pPlayer.getAbilities().instabuild;
            default -> false;
        };
    }
    protected boolean handleItemTransfer(Player player) {
        if (player.getItemInHand(hand).getItem() == Items.AIR) {
            player.setItemInHand(hand, getPickupItem());
            return true;
        }
        return player.addItem(getPickupItem());
    }

    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    protected @NotNull ItemStack getPickupItem() {
        return this.tomahawk.copy();
    }

    public boolean isRotating() {
        return rotating;
    }

    public boolean isTopFaceHit() {
        return topFaceHit;
    }

    public boolean isBottomFaceHit() {
        return bottomFaceHit;
    }
    public ItemStack getTomahawk() {
        return tomahawk;
    }
}

