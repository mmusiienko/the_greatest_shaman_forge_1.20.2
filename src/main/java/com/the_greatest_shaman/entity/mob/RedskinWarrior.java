package com.the_greatest_shaman.entity.mob;

import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RedskinWarrior extends PathfinderMob implements Enemy, RangedAttackMob {
    public RedskinWarrior(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.TOMAHAWK.get(), 1));
        setItemInHand(InteractionHand.OFF_HAND, new ItemStack(ModItems.TOMAHAWK.get(), 1));
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return PathfinderMob
                .createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.ATTACK_SPEED, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1f, 60, 20f));
        this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1d));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }
    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
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
    }
}
