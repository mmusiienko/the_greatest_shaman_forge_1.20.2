package com.the_greatest_shaman.block.entity.altar;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class Quest {
    private final ItemStack itemTask;
    private final Class<? extends Entity> entityToKill;
    private final int entityToKillCount;
    private final String taskMessage;
    private final String rewardMessage;
    private final ItemStack reward;
    private final Double distanceToKillEntity;

    public Quest(ItemStack itemTask, Class<? extends Entity> entityToKill, int entityToKillCount, String taskMessage, String rewardMessage, ItemStack reward, Double distanceToKillEntity) {
        this.itemTask = itemTask;
        this.entityToKill = entityToKill;
        this.entityToKillCount = entityToKillCount;
        this.taskMessage = taskMessage;
        this.rewardMessage = rewardMessage;
        this.reward = reward;
        this.distanceToKillEntity = distanceToKillEntity;
    }

    public static Builder builder() {
        return new Builder();
    }
    public ItemStack getItemTask() {
        return itemTask;
    }

    public Class<? extends Entity> getEntityToKill() {
        return entityToKill;
    }

    public int getEntityToKillCount() {
        return entityToKillCount;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public String getRewardMessage() {
        return rewardMessage;
    }

    public ItemStack getReward() {
        return reward;
    }

    public Double getDistanceToKillEntity() {
        return distanceToKillEntity;
    }

    public static class Builder {
        private ItemStack itemTask;
        private Class<? extends Entity> entityTask;
        private int entityCount = 0;
        private String taskMessage;
        private String rewardMessage;
        private ItemStack reward;
        private Double distanceToKillEntity;
        public Builder() {
        }
        public Quest build() {
            return new Quest(itemTask, entityTask, entityCount, taskMessage, rewardMessage, reward, distanceToKillEntity);
        }

        public Builder setDistanceToKillEntity(Double distanceToKillEntity) {
            this.distanceToKillEntity = distanceToKillEntity;
            return this;
        }

        public Builder setItemTask(ItemStack itemTask) {
            this.itemTask = itemTask;
            return this;
        }

        public Builder setEntityTask(Class<? extends Entity> entityTask) {
            this.entityTask = entityTask;
            return this;
        }

        public Builder setEntityCount(int entityCount) {
            this.entityCount = entityCount;
            return this;
        }

        public Builder setTaskMessage(String taskMessage) {
            this.taskMessage = taskMessage;
            return this;
        }

        public Builder setRewardMessage(String rewardMessage) {
            this.rewardMessage = rewardMessage;
            return this;
        }

        public Builder setReward(ItemStack reward) {
            this.reward = reward;
            return this;
        }
    }
}
