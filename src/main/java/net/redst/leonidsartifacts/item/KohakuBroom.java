package net.redst.leonidsartifacts.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.utils.KohakuBroomUse;


public class KohakuBroom extends SwordItem {
    public KohakuBroom() {
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
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        KohakuBroomUse.Use(pLevel, pPlayer);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
