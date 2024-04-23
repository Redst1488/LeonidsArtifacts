package net.redst.leonidsartifacts.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.utils.RadianceAura;

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
        pTooltip.add(Component.translatable("tooltip.leonidsartifats.radiance"));
        super.appendHoverText(itemstack, world, pTooltip, flag);
    }
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        Player p = (Player)entity ;
        if (selected)
            RadianceAura.Burn(world, p);
    }

}
