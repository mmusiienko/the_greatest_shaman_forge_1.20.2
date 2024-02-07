package com.the_greatest_shaman.block;

import com.the_greatest_shaman.TheGreatestShaman;
import com.the_greatest_shaman.block.altar.Altar;
import com.the_greatest_shaman.block.altar.BearAltar;
import com.the_greatest_shaman.block.entity.altar.BearAltarBlockEntity;
import com.the_greatest_shaman.block.entity.altar.DeathAltarBlockEntity;
import com.the_greatest_shaman.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheGreatestShaman.MODID);
    public static final RegistryObject<Block> SIGMA_BLOCK = registerBlock("sigmablock",
            () -> new SigmaBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> BEAR_ALTAR = registerBlock("bear_altar",
            () -> new BearAltar(BearAltarBlockEntity::new));
    public static final RegistryObject<Block> DEATH_ALTAR = registerBlock("death_altar",
            () -> new Altar<>(DeathAltarBlockEntity::new));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
