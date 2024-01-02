package com.the_greatest_shaman.sound.custom;

import com.the_greatest_shaman.sound.ModSound;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;


public class SigmaBlockSoundInstance  extends AbstractTickableSoundInstance {
    public SigmaBlockSoundInstance(BlockPos pPos) {
        super(ModSound.SIGMA_BLOCK_SOUND.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.looping = true;
        this.attenuation = Attenuation.LINEAR;
        this.volume = 1f;
        this.x = pPos.getX() + 0.5f;
        this.y = pPos.getY() + 0.5f;
        this.z = pPos.getZ() + 0.5f;
        this.delay = 0;
    }
    public void stopInstance() {
        this.stop();
    }

    @Override
    public void tick() {

    }
}
