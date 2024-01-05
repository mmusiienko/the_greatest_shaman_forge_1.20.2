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

public class RedskinChieftain extends AbstractRedskin {
    public RedskinChieftain(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.TRIDENT));
        setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.CHIEFTAINS_HAT.get()));
    }
    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return PathfinderMob
                .createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.ATTACK_DAMAGE, 12)
                .add(Attributes.ATTACK_SPEED, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 35);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1f, false));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation() {
        return new ResourceLocation(TheGreatestShaman.MODID,"/textures/entity/redskin_chieftain.png");
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity pEntity) {
        return distanceToSqr(pEntity) <= 2f;
    }
}
