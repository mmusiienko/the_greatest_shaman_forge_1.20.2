package com.the_greatest_shaman.mixin;


import com.the_greatest_shaman.HandPlatform;
import com.the_greatest_shaman.TheGreatestShaman;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Minecraft.class)
public abstract class ClickMixin {

    @Shadow
    public LocalPlayer player;
    @Shadow
    public HitResult hitResult;
    @Shadow
    public MultiPlayerGameMode gameMode;

    private boolean mainHandTime = true;

    @Inject(method = "startAttack", at = @At(value = "HEAD"), cancellable = true)
    public void startAttack(CallbackInfoReturnable<Boolean> cir) {
        if (!this.player.isHandsBusy() && !this.player.isCrouching()
                && HandPlatform.canUseOffhand(player)
                && HandPlatform.canSwingHand(this.player, InteractionHand.OFF_HAND)
                && !mainHandTime) {
            TheGreatestShaman.Data data = TheGreatestShaman.get(this.player);
            if (data.missTime <= 0 && this.hitResult != null) {
                mainHandTime = !mainHandTime;

                switch (this.hitResult.getType()) {
                    case ENTITY:
                        data.doOverride = true;
                        this.gameMode.attack(this.player, ((EntityHitResult) this.hitResult).getEntity());
                        this.player.swing(InteractionHand.OFF_HAND);
                        Minecraft.getInstance().options.keyUse.release();
                        break;
                    case BLOCK:
                        return;
                    case MISS:
                        ItemStack stack = this.player.getMainHandItem();
                        UseAnim useAnimation = stack.getUseAnimation();
                        if(useAnimation != UseAnim.NONE) {
                            return;
                        }

                        if (Objects.requireNonNull(this.gameMode).hasMissTime()) {
                            data.missTime = 10;
                        }
                        this.player.swing(InteractionHand.OFF_HAND);
                        Minecraft.getInstance().options.keyUse.release();
                        break;
                }
            }
//            cir.cancel();
        }
        else {
            mainHandTime = !mainHandTime;
        }
    }
}
