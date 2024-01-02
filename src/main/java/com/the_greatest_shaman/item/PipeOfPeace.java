package com.the_greatest_shaman.item;

import com.the_greatest_shaman.particle.ModParticles;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class PipeOfPeace extends Item {
    private final static float SHIFT = 0.4f;
    public PipeOfPeace(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack)
    {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack)
    {
        return 180;
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int x) {
        if (pLivingEntity instanceof Player player) {
            Vec3 vec = new Vec3(player.getLookAngle().x * SHIFT,  player.getLookAngle().y * SHIFT, player.getLookAngle().z * SHIFT);
            Vec3 offsetVec = vec.yRot((float) -Math.PI/6);
            if (pLevel.getRandom().nextInt(30) == 0) {
                pLevel.addParticle(
                        ModParticles.SMOKE_PARTICLE.get(),
                        pLivingEntity.getX() + offsetVec.x - 0.05f,
                        pLivingEntity.getY() +  pLevel.getRandom().nextFloat()* 0.2f + 1.4f + offsetVec.y,
                        pLivingEntity.getZ() + offsetVec.z - 0.05f, 0, 0.1f, 0
                );
                pLevel.addParticle(
                        ModParticles.SMOKE_PARTICLE.get(),
                        pLivingEntity.getX() + offsetVec.x,
                        pLivingEntity.getY() + pLevel.getRandom().nextFloat()* 0.2f + 1.4f + offsetVec.y,
                        pLivingEntity.getZ() + offsetVec.z, 0, 0.1f, 0
                );
                pLevel.addParticle(
                        ModParticles.SMOKE_PARTICLE.get(),
                        pLivingEntity.getX() + offsetVec.x + 0.05f,
                        pLivingEntity.getY() + pLevel.getRandom().nextFloat()* 0.2f + 1.4f + offsetVec.y,
                        pLivingEntity.getZ() + offsetVec.z + 0.05f, 0, 0.1f, 0
                );
            }
        }

    }
    
    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {

    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
