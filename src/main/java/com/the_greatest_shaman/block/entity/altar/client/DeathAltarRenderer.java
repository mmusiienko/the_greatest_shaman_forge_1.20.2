package com.the_greatest_shaman.block.entity.altar.client;

import com.the_greatest_shaman.block.entity.altar.DeathAltarBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DeathAltarRenderer extends GeoBlockRenderer<DeathAltarBlockEntity> {
    public DeathAltarRenderer(BlockEntityRendererProvider.Context context) {
        super(new DeathAltarModel());
    }
}