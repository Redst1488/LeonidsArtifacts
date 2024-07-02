package net.redst.leonidsartifacts.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Agile extends MobEffect {

    public Agile() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }


    AttributeModifier modifier = new AttributeModifier("AgileBroom", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {

        entity.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(modifier);
    }
    @Override
    public void removeAttributeModifiers(LivingEntity player, AttributeMap pAttributeMap, int pAmplifier) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(modifier.getId());
    }
}
