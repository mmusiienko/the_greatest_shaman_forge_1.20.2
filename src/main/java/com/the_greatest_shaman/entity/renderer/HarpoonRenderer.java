package com.the_greatest_shaman.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.the_greatest_shaman.entity.projectile.HarpoonProjectile;
import com.the_greatest_shaman.entity.projectile.TomahawkProjectile;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.lwjgl.opengl.GL11;

public class HarpoonRenderer extends EntityRenderer<HarpoonProjectile> {

    private static final ResourceLocation TOMAHAWK = new ResourceLocation("textures/item/tomahawk.png");
    private static final float SCALAR = 2f;
    private static final float ROTATION_SPEED = 40;

    public HarpoonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(HarpoonProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {

//        pPoseStack.pushPose();
        pPoseStack.pushPose();
        pPoseStack.pushPose();
        pPoseStack.scale(SCALAR, SCALAR, SCALAR);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180 + pEntityYaw));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getXRot()));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        Minecraft.getInstance().getItemRenderer().renderStatic(
                new ItemStack(ModItems.HARPOON.get()),
                ItemDisplayContext.GROUND,
                pPackedLight,
                OverlayTexture.NO_OVERLAY,
                pPoseStack,
                pBuffer,
                pEntity.level(),
                1432442344
        );
        pPoseStack.popPose();

        Entity owner = pEntity.getOwner();
        if (owner == null) return;
        VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.lineStrip());
        PoseStack.Pose posestack$pose1 = pPoseStack.last();
        Vec3 vec = owner.getLookAngle().scale(0.4f);
        Vec3 offsetVec = vec.yRot((float) -Math.PI/6);
        float tx = Mth.lerp(pPartialTicks, (float)owner.xOld - (float)pEntity.xOld,(float)owner.getX() - (float)pEntity.getX()) + (float)offsetVec.x;
        float ty = Mth.lerp(pPartialTicks, (float)owner.yOld - (float)pEntity.yOld,(float)owner.getY() - (float)pEntity.getY()) + 1.6f + (float)offsetVec.y;
        float tz = Mth.lerp(pPartialTicks, (float)owner.zOld - (float)pEntity.zOld,(float)owner.getZ() - (float)pEntity.getZ()) + (float)offsetVec.z;
        Matrix4f matrix4f = posestack$pose1.pose();
        vertexconsumer1.vertex(matrix4f, 0, 0, 0).color(92, 64, 51, 255).normal(posestack$pose1.normal(),0, 0, 0).endVertex();
        vertexconsumer1.vertex(matrix4f, tx, ty, tz).color(92, 64, 51, 255).normal(posestack$pose1.normal(),0, 0, 0).endVertex();
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }

    private static float fraction(int pNumerator, int pDenominator) {
        return (float)pNumerator / (float)pDenominator;
    }
    private static void stringVertex(float pX, float pY, float pZ, VertexConsumer pConsumer, PoseStack.Pose pPose, float p_174124_, float p_174125_) {
        float f = pX * p_174124_;
        float f1 = pY * (p_174124_ * p_174124_ + p_174124_) * 0.5F + 0.25F;
        float f2 = pZ * p_174124_;
        float f3 = pX * p_174125_ - f;
        float f4 = pY * (p_174125_ * p_174125_ + p_174125_) * 0.5F + 0.25F - f1;
        float f5 = pZ * p_174125_ - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 /= f6;
        f4 /= f6;
        f5 /= f6;
        pConsumer.vertex(pPose.pose(), f, f1, f2).color(0, 0, 0, 255).normal(pPose.normal(), f3, f4, f5).endVertex();
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HarpoonProjectile pEntity) {
        return TOMAHAWK;
    }

}
