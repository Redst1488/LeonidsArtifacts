package net.redst.leonidsartifacts.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.network.Variables;
import net.redst.leonidsartifacts.utils.RadianceAura;

public class Broom extends SwordItem {

    private static int jumpAmount;
    private static boolean canDoubleJump;

    public Broom() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 7f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 0;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.8f, new Properties().rarity(Rarity.EPIC));

    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);

        if(entity instanceof Player) {
            LocalPlayer player = (LocalPlayer) entity;
            if (selected) {
                if (player.onGround()) {
                    jumpAmount = 0;
                } else {
                    if (jumpAmount == 0) {
                        jumpAmount = 1;
                        canDoubleJump = true;
                    }
                    if (player.input.jumping){
                        if(!canDoubleJump && jumpAmount < 2){
                            player.jumpFromGround();
                            jumpAmount++;
                        }
                        canDoubleJump = true;
                    } else {
                        canDoubleJump = false;
                    }
                }
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1, 0, false, false));
            }
        }

    }


}
