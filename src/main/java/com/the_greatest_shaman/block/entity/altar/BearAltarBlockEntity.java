package com.the_greatest_shaman.block.entity.altar;

import com.the_greatest_shaman.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class BearAltarBlockEntity extends AltarBlockEntity {
    private static final Quest diamondQuest = Quest
            .builder()
            .setTaskMessage("Bring me some shiny diamonds SLAVE!")
            .setItemTask(new ItemStack(Items.DIAMOND, 64))
            .setRewardMessage("Now i am finally RICH! You deserve this reward.")
            .setReward(new ItemStack(Items.DIRT, 64))
            .build();
    private static final Quest pigQuest = Quest
            .builder()
            .setTaskMessage("Kill the disbeliever (mr PIG) close to my altar, ")
            .setEntityTask(Pig.class)
            .setEntityCount(1)
            .setDistanceToKillEntity(100.0)
            .setRewardMessage("And now he is GONE. Good job. Take this.")
            .setReward(new ItemStack(Items.BEEF, 64))
            .build();

    public BearAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BEAR_ALTAR_BLOCK_ENTITY.get(), pPos, pBlockState,
                new AltarQuestSequence()
                        .add(diamondQuest)
                        .add(pigQuest)
                        );
    }

}
