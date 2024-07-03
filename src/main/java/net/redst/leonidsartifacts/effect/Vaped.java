package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class Vaped extends MobEffect {

    public Vaped() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 0));
    }
}
