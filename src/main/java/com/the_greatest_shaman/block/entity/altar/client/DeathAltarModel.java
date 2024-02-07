package com.the_greatest_shaman.block.entity.altar.client;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.block.entity.altar.DeathAltarBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class DeathAltarModel extends DefaultedBlockGeoModel<DeathAltarBlockEntity> {

    public DeathAltarModel() {
        super(new ResourceLocation(TheGreatestShaman.MODID, "death_altar"));
    }

    @Override
    public ResourceLocation getModelResource(DeathAltarBlockEntity animatable) {
        return super.getModelResource(animatable);
    }

    @Override
    public ResourceLocation getTextureResource(DeathAltarBlockEntity animatable) {
        return super.getTextureResource(animatable);
    }

    @Override
    public ResourceLocation getAnimationResource(DeathAltarBlockEntity animatable) {
        return super.getAnimationResource(animatable);
    }

}