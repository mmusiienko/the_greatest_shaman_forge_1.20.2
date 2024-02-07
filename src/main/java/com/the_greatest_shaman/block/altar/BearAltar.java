package com.the_greatest_shaman.block.altar;

import com.the_greatest_shaman.block.entity.altar.BearAltarBlockEntity;
import com.the_greatest_shaman.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.BiFunction;

public class BearAltar extends Altar<BearAltarBlockEntity> {

    public BearAltar(BiFunction<BlockPos, BlockState, BearAltarBlockEntity> factory) {
        super(factory);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);

        if (!pLevel.isClientSide()) return;

        final float shift = 0.125f;
        if (pRandom.nextInt(6) == 0) {
            pLevel.addParticle(ModParticles.FIRE_PARTICLE.get(),
                    pPos.getX() + shift, pPos.getY() + 0.465f, pPos.getZ() + shift,
                    0f, 0f, 0f
            );
            pLevel.addParticle(ModParticles.FIRE_PARTICLE.get(),
                    pPos.getX() - shift + 1f, pPos.getY() + 0.465f, pPos.getZ() + shift,
                    0f, 0f, 0f
            );
            pLevel.addParticle(ModParticles.FIRE_PARTICLE.get(),
                    pPos.getX() + shift, pPos.getY() + 0.465f, pPos.getZ() - shift + 1f,
                    0f, 0f, 0f
            );
            pLevel.addParticle(ModParticles.FIRE_PARTICLE.get(),
                    pPos.getX() - shift + 1f, pPos.getY() + 0.465f, pPos.getZ() - shift + 1f,
                    0f, 0f, 0f
            );
        }
    }
}
