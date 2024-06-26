package net.redst.leonidsartifacts.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.redst.leonidsartifacts.load.InitEffects;
import net.redst.leonidsartifacts.utils.Dagger;

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

        if (entity instanceof LocalPlayer player) {
            if (selected) {
                if (player.onGround()) {
                    jumpAmount = 0;
                } else {
                    if (jumpAmount == 0) {
                        jumpAmount = 1;
                        canDoubleJump = true;
                    }
                    if (player.input.jumping) {
                        if (!canDoubleJump && jumpAmount < 2) {
                            player.jumpFromGround();
                            world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                            world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                            world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                            jumpAmount++;
                        }
                        canDoubleJump = true;
                    } else {
                        canDoubleJump = false;
                    }
                }
                player.addEffect(new MobEffectInstance(InitEffects.AGILE.get(), 20, 0, true, false));
            }
            if(!selected){
                player.removeEffect(InitEffects.AGILE.get());
            }
        }

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random(), pPlayer.getZ(), 0, 0, 0);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random(), pPlayer.getZ(), 0, 0, 0);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random(), pPlayer.getZ(), 0, 0, 0);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random()+0.5, pPlayer.getZ(), 0, 0, 0);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random()+0.5, pPlayer.getZ(), 0, 0, 0);
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY()+Math.random()+0.5, pPlayer.getZ(), 0, 0, 0);
        Dagger.Blink(pLevel, pPlayer, itemStack);
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

}
