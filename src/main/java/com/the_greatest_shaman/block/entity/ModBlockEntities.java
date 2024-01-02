package com.the_greatest_shaman.block.entity;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.block.ModBlocks;
import com.the_greatest_shaman.block.entity.altar.BearAltarBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheGreatestShaman.MODID);

    public static final RegistryObject<BlockEntityType<SigmaBlockEntity>> SIGMABLOCK_ENTITY =
            BLOCK_ENTITIES.register("sigmablock_entity", () ->
                    BlockEntityType.Builder.of(SigmaBlockEntity::new,
                            ModBlocks.SIGMA_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<BearAltarBlockEntity>> BEAR_ALTAR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("bear_altar_block_entity", () ->
                    BlockEntityType.Builder.of(BearAltarBlockEntity::new,
                            ModBlocks.BEAR_ALTAR.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}