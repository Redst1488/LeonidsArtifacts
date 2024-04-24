package net.redst.leonidsartifacts.mixin;


import net.minecraft.world.damagesource.DamageSource;


import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerMixin {
    @Inject(at = @At("HEAD"), method = "hurt")
    public void hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir){

    }

}