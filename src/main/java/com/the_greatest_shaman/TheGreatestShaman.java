package com.the_greatest_shaman;

import com.mojang.logging.LogUtils;
import com.the_greatest_shaman.block.ModBlocks;
import com.the_greatest_shaman.block.entity.ModBlockEntities;
import com.the_greatest_shaman.entity.ModEntities;
import com.the_greatest_shaman.item.ModItems;
import com.the_greatest_shaman.item.ModMobEffects;
import com.the_greatest_shaman.particle.ModParticles;
import com.the_greatest_shaman.sound.ModSound;
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
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheGreatestShaman.MODID)
public class TheGreatestShaman
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "the_greatest_shaman";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public TheGreatestShaman()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSound.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        // Register the commonSetup method for modloading
        GeckoLib.initialize();
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
        if (event.getTabKey() == ModCreativeTabs.THE_GREATEST_SHAMAN_TAB.getKey()) {
            ModItems.ITEMS.getEntries().forEach(entry -> event.accept(entry.get()));
            ModBlocks.BLOCKS.getEntries().forEach(entry -> event.accept(entry.get()));
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
}
