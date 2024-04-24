package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redst.leonidsartifacts.load.InitEffects;

import java.util.Comparator;
import java.util.List;

public class Radiance extends SwordItem {
    public Radiance() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 0f;
            }

            public float getAttackDamageBonus() {
                return 4f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 20;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2f, new Item.Properties());
    }

    public void appendHoverText(ItemStack itemstack, Level world, List<Component> pTooltip, TooltipFlag flag) {
        pTooltip.add(Component.translatable("tooltip.leonidsartifacts.radiance"));
        super.appendHoverText(itemstack, world, pTooltip, flag);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        Player player = (Player) entity;
        if (selected) {
            final Vec3 center = new Vec3((player.getX()), (player.getY()), (player.getZ()));
            List<LivingEntity> _entfound = world.getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
            for (LivingEntity entities : _entfound) {
                if (entities != player) {
                    if (!(entities.hasEffect(InitEffects.BURN.get()))) {
                        entities.addEffect(new MobEffectInstance(InitEffects.BURN.get(), 20, 0, false, false));
                    }
                }
            }
        }
    }
}
