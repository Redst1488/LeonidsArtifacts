package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.redst.leonidsartifacts.load.InitEffects;

public class Stunned extends MobEffect {

    public Stunned() {
        super(MobEffectCategory.HARMFUL, -1);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (!entity.hasEffect(InitEffects.VAPED.get())) {
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1, 0));
        }
    }
}