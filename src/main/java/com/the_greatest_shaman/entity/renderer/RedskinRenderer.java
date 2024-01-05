package com.the_greatest_shaman.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.the_greatest_shaman.entity.mob.AbstractRedskin;
import com.the_greatest_shaman.entity.model.ModMobModelLayers;
import com.the_greatest_shaman.entity.model.RedskinModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RedskinRenderer extends MobRenderer<AbstractRedskin, RedskinModel<AbstractRedskin>> {


    public RedskinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RedskinModel<>(pContext.bakeLayer(ModMobModelLayers.REDSKIN_LAYER)), 0.45f);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractRedskin pEntity) {
        return pEntity.getTextureLocation();
    }

    @Override
    public void render(AbstractRedskin pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}