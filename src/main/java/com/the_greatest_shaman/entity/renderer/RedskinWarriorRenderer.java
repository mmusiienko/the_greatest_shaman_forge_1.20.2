package com.the_greatest_shaman.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.mob.RedskinWarrior;
import com.the_greatest_shaman.entity.model.ModMobModelLayers;
import com.the_greatest_shaman.entity.model.RedskinWarriorModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RedskinWarriorRenderer extends HumanoidMobRenderer<RedskinWarrior, RedskinWarriorModel<RedskinWarrior>> {
    public RedskinWarriorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RedskinWarriorModel<>(pContext.bakeLayer(ModMobModelLayers.REDSKIN_WARRIOR_LAYER), false), 0.45f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RedskinWarrior pEntity) {
        return new ResourceLocation(TheGreatestShaman.MODID,"/textures/entity/redskin_warrior.png");
    }

    @Override
    public void render(RedskinWarrior pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}