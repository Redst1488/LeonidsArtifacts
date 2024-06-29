package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class Agile extends MobEffect {

    public Agile() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }
    //@Override
    //public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
       //pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 1));
    //}
    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 1));
    }
}
