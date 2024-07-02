package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class Vaped extends MobEffect {

        public Vaped() {super(MobEffectCategory.BENEFICIAL, -1);
        }
        @Override
        public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 0));
        }
    }
