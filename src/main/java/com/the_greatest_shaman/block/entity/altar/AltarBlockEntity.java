package com.the_greatest_shaman.block.entity.altar;

import com.the_greatest_shaman.TheGreatestShaman;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IdMappingEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AltarBlockEntity extends BlockEntity {
    public static Set<AltarBlockEntity> ALTAR_BLOCK_ENTITIES = new HashSet<>();
    private static final Queue<String> messageQueue = new LinkedList<>();
    private final AltarQuestSequence quests;
    private int currentQuestIndex = -1;
    private int entityToKillCount = 0;
    private int ticksSinceLastMessageDisplay = 0;

    public AltarBlockEntity(BlockEntityType pType, BlockPos pPos, BlockState pBlockState, AltarQuestSequence quests) {
        super(pType, pPos, pBlockState);
        this.quests = quests;
        ALTAR_BLOCK_ENTITIES.add(this);
    }


    private void decreaseEntityToKillCount() {
        entityToKillCount--;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("entityToKillCount", entityToKillCount);
        pTag.putInt("currentQuestIndex", currentQuestIndex);
        super.saveAdditional(pTag);
    }
    @Override
    public void onChunkUnloaded() {ALTAR_BLOCK_ENTITIES.remove(this);}


    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        entityToKillCount = pTag.getInt("entityToKillCount");
        currentQuestIndex = pTag.getInt("currentQuestIndex");
    }

//    @Override
//    public @NotNull CompoundTag getUpdateTag() {
//        CompoundTag tag = new CompoundTag();
//        tag.putInt("entityToKillCount", entityToKillCount);
//        tag.putInt("currentQuestIndex", currentQuestIndex);
//        return tag;
//    }
//
//    @Nullable
//    @Override
//    public Packet<ClientGamePacketListener> getUpdatePacket() {
//        return ClientboundBlockEntityDataPacket.create(this);
//    }
    private void completeQuest() {
        currentQuestIndex++;
        if (currentQuestIndex < quests.size()) {
            entityToKillCount = getCurrentQuest().getEntityToKillCount();
        }
        setChanged();
    }
    private Quest getCurrentQuest() {
        return quests.getQuest(currentQuestIndex);
    }
    public InteractionResult interact(Player pPlayer) {
        if (currentQuestIndex == -1 && !quests.isEmpty()) {
            displayGreeting(pPlayer);
            completeQuest();
            displayQuestTask(pPlayer);
            return InteractionResult.SUCCESS;
        }

        if (currentQuestIndex >= quests.size()) {
            displayNoQuestsMessage(pPlayer);
            return InteractionResult.SUCCESS;
        }

        return handleTaskCompletion(pPlayer);
    }
    private InteractionResult handleTaskCompletion(Player pPlayer) {
        ItemStack itemStack = pPlayer.getMainHandItem();
        int has = 0;
        int needed = 0;
        boolean itemTaskCompleted = true;
        if (getCurrentQuest().getItemTask() != null) {
            has = itemStack.getCount();
            needed = getCurrentQuest().getItemTask().getCount();
            itemTaskCompleted = (itemStack.getItem() == getCurrentQuest().getItemTask().getItem()) && (has >= needed);
        }

        boolean taskCompleted = itemTaskCompleted && (entityToKillCount == 0);
        if (taskCompleted) {
            if (getCurrentQuest().getItemTask() != null) {
                itemStack.shrink(needed);
            }
            pPlayer.addItem(getCurrentQuest().getReward().copy());
            String message = getCurrentQuest().getRewardMessage();
            completeQuest();
            displayCompletionMessage(pPlayer, message);
        }
        displayQuestTask(pPlayer);
        return InteractionResult.SUCCESS;
    }

//    public void tick() {
//        ticksSinceLastMessageDisplay = Math.min(ticksSinceLastMessageDisplay + 1, 200);
//        if (ticksSinceLastMessageDisplay < 40) {
//            return;
//        }
//        if (!messageQueue.isEmpty()) {
//           level.getServer().getPlayerList().broadcastChatMessage(PlayerChatMessage.system(messageQueue.remove()), );
//        }
//
//    }
    private void enqueueMessage(String message) {
        messageQueue.add(message);
    }
    private void displayGreeting(Player pPlayer) {
        pPlayer.displayClientMessage(Component.literal(quests.getGreetingMessage()), false);
    }
    private void displayNoQuestsMessage(Player pPlayer) {
        pPlayer.displayClientMessage(Component.literal("There is no quests left :("), false);
    }
    private void displayQuestTask(Player pPlayer) {
        if (getCurrentQuest() != null) {
            pPlayer.displayClientMessage(Component.literal(getCurrentQuest().getTaskMessage()), false);
        } else {
            pPlayer.displayClientMessage(Component.literal("You did every quest for me."), false);
        }
    }

    private void displayCompletionMessage(Player pPlayer, String message) {
        pPlayer.displayClientMessage(Component.literal(message), false);
    }

    private void displayMobKillCompletionMessage(Player pPlayer) {
        pPlayer.displayClientMessage(Component.literal("Great job, now get your ass back here."), false);
    }

    @Mod.EventBusSubscriber(modid = TheGreatestShaman.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    private static class MobKillHandler {
        @SubscribeEvent
        public static void worldLoad(IdMappingEvent event) {
            ALTAR_BLOCK_ENTITIES = new HashSet<>();
        }

        @SubscribeEvent
        public static void onMobKill(LivingDeathEvent event) {
            ALTAR_BLOCK_ENTITIES.forEach(
                    altar -> {

                        if (altar.level != null && altar.level.isClientSide()) {
                            return;
                        }
                        if (altar.getCurrentQuest() == null) return;

                        if (altar.getCurrentQuest().getEntityToKill() == null || altar.getCurrentQuest().getEntityToKillCount() <= 0) {
                            return;
                        }

                        Class<? extends Entity> entityClass = altar.getCurrentQuest().getEntityToKill();
                        if (!(entityClass.isInstance(event.getEntity()))) {
                            return;
                        }

                        Entity entity = event.getSource().getEntity();
                        if (entity instanceof Player player) {
                            double distance = Math.sqrt(Math.pow(altar.worldPosition.getX() - entity.getBlockX(), 2)
                                    + Math.pow(altar.worldPosition.getY() - entity.getBlockY(), 2)
                                    + Math.pow(altar.worldPosition.getZ() - entity.getBlockZ(), 2)
                            );

                            if (distance > altar.getCurrentQuest().getDistanceToKillEntity()) {
                                return;
                            }

                            altar.decreaseEntityToKillCount();
                            if (altar.entityToKillCount == 0) {
                                altar.displayMobKillCompletionMessage(player);
                            }
                        }


                    }
            );

        }


    }



}
