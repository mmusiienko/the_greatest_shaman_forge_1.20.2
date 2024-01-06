package com.the_greatest_shaman;

import com.the_greatest_shaman.block.ModBlocks;
import com.the_greatest_shaman.block.entity.ModBlockEntities;
import com.the_greatest_shaman.entity.ModEntities;
import com.the_greatest_shaman.item.ModItems;
import com.the_greatest_shaman.particle.ModParticles;
import com.the_greatest_shaman.sound.ModSound;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheGreatestShaman.MODID)
public class TheGreatestShaman
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "the_greatest_shaman";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Map<UUID, Data> swing = new HashMap<>();
    public static Map<UUID, Data> swingLocal = new HashMap<>();

    public TheGreatestShaman()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSound.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEntities.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.SIGMA_BLOCK);
            event.accept(ModBlocks.BEAR_ALTAR);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BEAR_TOTEM);
            event.accept(ModItems.SALMON_TOTEM);
            event.accept(ModItems.PIPE_OF_PEACE);
        }

    }

    public static Data get(Player entity) {
        if (entity.isLocalPlayer()) {
            if (!swingLocal.containsKey(entity.getUUID())) {
                swingLocal.put(entity.getUUID(), new Data());
            }
            return swingLocal.get(entity.getUUID());
        } else {
            if (!swing.containsKey(entity.getUUID())) {
                swing.put(entity.getUUID(), new Data());
            }
            return swing.get(entity.getUUID());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

    }

    public static class Data {
        public boolean doOverride;
        //
        public int missTime;
        //
        public int swingTime;
        public boolean swinging;
        public float attackAnim;
        public float attackAnim_;
        public int attackStrengthTicker;
        public InteractionHand swingingArm;
        //
        public int ticksSinceLastActiveStack;
        public InteractionHand handOfLastActiveStack;
    }
}
