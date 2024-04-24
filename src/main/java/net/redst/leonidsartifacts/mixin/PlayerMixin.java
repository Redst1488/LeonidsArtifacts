package net.redst.leonidsartifacts.mixin;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.load.InitEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(at = @At("HEAD"), method = "attack")
    public void attack(Entity pTarget, CallbackInfo ci) {
        Player player = (Player)(Object)this;
        if (player.hasEffect(InitEffects.BURN.get())) {
            LeonidsArtifacts.LOGGER.info("Evasion!");

        }
    }
}

