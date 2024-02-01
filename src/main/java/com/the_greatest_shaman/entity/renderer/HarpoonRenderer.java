package com.the_greatest_shaman.entity.renderer;

import com.the_greatest_shaman.entity.projectile.HarpoonProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HarpoonRenderer extends EntityRenderer<HarpoonProjectile> {
    public HarpoonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpoonProjectile pEntity) {
        return new ResourceLocation("textures/item/tomahawk.png");
    }
}
