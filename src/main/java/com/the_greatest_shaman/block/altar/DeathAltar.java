package com.the_greatest_shaman.block.altar;

import com.the_greatest_shaman.block.entity.altar.DeathAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public class DeathAltar extends Altar<DeathAltarBlockEntity> {

    public DeathAltar(BiFunction<BlockPos, BlockState, DeathAltarBlockEntity> factory) {
        super(factory);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
