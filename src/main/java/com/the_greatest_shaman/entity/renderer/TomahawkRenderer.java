package com.the_greatest_shaman.entity.renderer;

import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class TomahawkRenderer extends EntityRenderer<TomahawkProjectile> {
    private static final ResourceLocation TOMAHAWK = new ResourceLocation("textures/item/tomahawk.png");
    private static final float SCALAR = 0.7f;
    private static final float ROTATION_SPEED = 40;

    public TomahawkRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(TomahawkProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180  + pEntityYaw));
        pPoseStack.scale(SCALAR, SCALAR, SCALAR);
        if (pEntity.isRotating()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.tickCount * ROTATION_SPEED + pEntity.getRotationVector().x));
        }
        if (pEntity.isTopFaceHit()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
        }
        if (pEntity.isBottomFaceHit()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(270));
        }
        Minecraft.getInstance().getItemRenderer().renderStatic(
                pEntity.getTomahawk(),
                ItemDisplayContext.GROUND,
                pPackedLight,
                OverlayTexture.NO_OVERLAY,
                pPoseStack,
                pBuffer,
                pEntity.level(),
                1432442344
        );
        pPoseStack.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TomahawkProjectile pEntity) {
        return TOMAHAWK;
    }
}
