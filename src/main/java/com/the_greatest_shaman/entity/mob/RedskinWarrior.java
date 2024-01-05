package com.the_greatest_shaman.entity.mob;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RedskinWarrior extends AbstractRedskin {

    public RedskinWarrior(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.TOMAHAWK.get()));
        setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SHIELD));
        setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.FEATHER_HAT.get()));
        setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1f, false));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation() {
        return new ResourceLocation(TheGreatestShaman.MODID,"/textures/entity/redskin_warrior.png");
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return PathfinderMob
                .createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.ATTACK_SPEED, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 30);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity pEntity) {
        return distanceToSqr(pEntity) <= 1.5f;
    }

    @Override
    public float getAttackAnim(float pPartialTick) {
        return super.getAttackAnim(pPartialTick);
    }
}
