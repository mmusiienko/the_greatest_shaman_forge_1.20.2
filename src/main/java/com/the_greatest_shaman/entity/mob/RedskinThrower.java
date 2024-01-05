package com.the_greatest_shaman.entity.mob;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RedskinThrower extends AbstractRedskin implements RangedAttackMob {
    public static final int RANGED_ATTACK_COOLDOWN = 60;
    public int attackAnimationTimeout = 0;

    public RedskinThrower(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.TOMAHAWK.get()));
        setItemInHand(InteractionHand.OFF_HAND, new ItemStack(ModItems.TOMAHAWK.get()));
        setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.FEATHER_HAT.get()));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RedskinTomahawkAttackGoal(this, 1f, RANGED_ATTACK_COOLDOWN, 20f));
    }
    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            if (this.isAttacking()) {
                if (attackAnimationTimeout <= 0) {
                    attackAnimationTimeout = RANGED_ATTACK_COOLDOWN;
//                throwAnimationState.start(this.tickCount);
                } else {
                    --this.attackAnimationTimeout;
                }

            } else {
                attackAnimationTimeout = Math.max(attackAnimationTimeout - 1, 0);
            }
        }

    }
    @Override
    public @NotNull ResourceLocation getTextureLocation() {
        return new ResourceLocation(TheGreatestShaman.MODID,"/textures/entity/redskin_thrower.png");
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return PathfinderMob
                .createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ATTACK_SPEED, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 40);
    }

    @Override
    public boolean isAggressive() {
        return super.isAggressive();
    }

    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        TomahawkProjectile tomahawkMain = new TomahawkProjectile(this, level(), new ItemStack(ModItems.TOMAHAWK.get(), 1), InteractionHand.MAIN_HAND);
        TomahawkProjectile tomahawkOff = new TomahawkProjectile(this, level(), new ItemStack(ModItems.TOMAHAWK.get(), 1), InteractionHand.OFF_HAND);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY() - tomahawkMain.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        tomahawkMain.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        level().addFreshEntity(tomahawkMain);
        tomahawkOff.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        level().addFreshEntity(tomahawkOff);
        level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 1F, 1f);
        setAttacking(true);
    }


    static class RedskinTomahawkAttackGoal extends RangedAttackGoal {
        private final RedskinThrower redskinThrower;
        public RedskinTomahawkAttackGoal(RangedAttackMob redskinThrower, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
            super(redskinThrower, pSpeedModifier, pAttackInterval, pAttackRadius);
            this.redskinThrower = (RedskinThrower) redskinThrower;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return super.canUse() && redskinThrower.getMainHandItem().is(ModItems.TOMAHAWK.get());
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            super.start();
            redskinThrower.setAggressive(true);
            redskinThrower.startUsingItem(InteractionHand.MAIN_HAND);
        }

        @Override
        public void tick() {
            super.tick();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            super.stop();
            redskinThrower.stopUsingItem();
            redskinThrower.setAggressive(false);
            redskinThrower.setAttacking(false);
        }
    }
}
