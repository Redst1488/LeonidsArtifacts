package net.redst.leonidsartifacts.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.load.InitEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At("HEAD"), method = "skipAttackInteraction", cancellable = true)
    public void skipAttackInteraction(Entity pEntity, CallbackInfoReturnable<Boolean> cir) {
        if (pEntity instanceof Player) {
            Player player = (Player)pEntity;
            if (player.hasEffect(InitEffects.BURN.get())) {
                LeonidsArtifacts.LOGGER.info("Evasion");
                cir.setReturnValue(Boolean.TRUE);
            }
        }
    }
}