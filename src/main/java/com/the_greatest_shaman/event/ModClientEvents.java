package com.the_greatest_shaman.event;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.entity.ModEntities;
import com.the_greatest_shaman.entity.model.ModMobModelLayers;
import com.the_greatest_shaman.entity.model.RedskinModel;
import com.the_greatest_shaman.entity.renderer.RedskinRenderer;
import com.the_greatest_shaman.entity.renderer.TomahawkRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheGreatestShaman.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModMobModelLayers.REDSKIN_LAYER, RedskinModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(ModEntities.TOMAHAWK_PROJECTILE.get(), TomahawkRenderer::new);
        EntityRenderers.register(ModEntities.REDSKIN_THROWER.get(), RedskinRenderer::new);
        EntityRenderers.register(ModEntities.REDSKIN_WARRIOR.get(), RedskinRenderer::new);
        EntityRenderers.register(ModEntities.REDSKIN_CHIEFTAIN.get(), RedskinRenderer::new);
    }
}