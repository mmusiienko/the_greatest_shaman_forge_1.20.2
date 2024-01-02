package com.the_greatest_shaman.block.entity;

import com.the_greatest_shaman.sound.custom.SigmaBlockSoundInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SigmaBlockEntity extends BlockEntity {

    private SigmaBlockSoundInstance soundInstance;

    public SigmaBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SIGMABLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void stopSoundInstance() {
        if (soundInstance != null) {
            soundInstance.stopInstance();
        }
    }

    public void interact(BlockPos pPos) {
        if (soundInstance == null) {
            soundInstance = new SigmaBlockSoundInstance(pPos);
            Minecraft.getInstance().getSoundManager().queueTickingSound(soundInstance);
        } else {
            stopSoundInstance();
            soundInstance = null;
        }
    }
}
