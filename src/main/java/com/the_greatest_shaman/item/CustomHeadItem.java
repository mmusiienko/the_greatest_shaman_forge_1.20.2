package com.the_greatest_shaman.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CustomHeadItem extends Item implements Equipable {
    public CustomHeadItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public InteractionResultHolder<ItemStack> swapWithEquipmentSlot(Item pItem, Level pLevel, Player pPlayer, InteractionHand pHand) {
        return Equipable.super.swapWithEquipmentSlot(pItem, pLevel, pPlayer, pHand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return swapWithEquipmentSlot(this, pLevel, pPlayer, pUsedHand);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
