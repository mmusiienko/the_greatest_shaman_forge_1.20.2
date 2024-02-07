package com.the_greatest_shaman.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.the_greatest_shaman.entity.projectile.HarpoonProjectile;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HarpoonRenderer extends EntityRenderer<HarpoonProjectile> {

    private static final ResourceLocation TOMAHAWK = new ResourceLocation("textures/item/tomahawk.png");
    private static final float SCALAR = 2f;

    public HarpoonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(HarpoonProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
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
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.lineStrip());
        PoseStack.Pose pose = pPoseStack.last();
        Vec3 vec = owner.getLookAngle().scale(0.4f);
        Vec3 offsetVec = vec.yRot((float) -Math.PI / 3);
        float tx = Mth.lerp(pPartialTicks, (float)owner.xOld - (float)pEntity.xOld,(float)owner.getX() - (float)pEntity.getX()) + (float)offsetVec.x;
        float ty = Mth.lerp(pPartialTicks, (float) owner.yOld - (float) pEntity.yOld, (float) owner.getY() - (float) pEntity.getY()) + 1.4f + (float) offsetVec.y;
        float tz = Mth.lerp(pPartialTicks, (float)owner.zOld - (float)pEntity.zOld,(float)owner.getZ() - (float)pEntity.getZ()) + (float)offsetVec.z;
        drawLine(vertexConsumer, pose, new Vec3(0, 0, 0), new Vec3(tx, ty, tz), 92, 64, 51, 255);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    public void drawLine(VertexConsumer vertexConsumer, PoseStack.Pose pose, Vec3 start, Vec3 end, int r, int g, int b, int alpha) {
        vertexConsumer.vertex(pose.pose(), (float) start.x, (float) start.y, (float) start.z).color(r, g, b, alpha).normal(pose.normal(), 0, 0, 0).endVertex();
        vertexConsumer.vertex(pose.pose(), (float) end.x, (float) end.y, (float) end.z).color(r, g, b, alpha).normal(pose.normal(), 0, 0, 0).endVertex();
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HarpoonProjectile pEntity) {
        return TOMAHAWK;
    }

}
