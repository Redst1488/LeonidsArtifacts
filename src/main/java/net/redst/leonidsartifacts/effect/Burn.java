package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Burn extends MobEffect {
    public Burn() {
        super(MobEffectCategory.HARMFUL, -1);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity == null)
            return;
        entity.setSecondsOnFire(1);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}