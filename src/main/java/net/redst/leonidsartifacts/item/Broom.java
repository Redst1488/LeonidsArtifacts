package net.redst.leonidsartifacts.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.redst.leonidsartifacts.load.InitEffects;
import net.redst.leonidsartifacts.network.Variables;

public class Broom extends SwordItem {
    public Broom() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 4f;
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

        if (entity instanceof Player player) {
            if (selected) {
                if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).DoubleJump) {
                        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0, Math.random() * -0.05, 0);
                    player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.DoubleJump = false;
                        capability.syncPlayerVariables(player);
                    });
                }
                player.addEffect(new MobEffectInstance(InitEffects.AGILE.get(), 1, 0, false, false));
            }
            if (!selected) {
                player.removeEffect(InitEffects.AGILE.get());
            }
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        int range = 15;
        if (pLevel instanceof ServerLevel level) {
            if (pPlayer instanceof ServerPlayer serverPlayer) {
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random(), pPlayer.getZ(), 0, 0, 0);
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random(), pPlayer.getZ(), 0, 0, 0);
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random(), pPlayer.getZ(), 0, 0, 0);
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random() + 0.5, pPlayer.getZ(), 0, 0, 0);
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random() + 0.5, pPlayer.getZ(), 0, 0, 0);
                pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pPlayer.getX(), pPlayer.getY() + Math.random() + 0.5, pPlayer.getZ(), 0, 0, 0);
                pPlayer.getCooldowns().addCooldown(pPlayer.getMainHandItem().getItem(), 160);

                for (int i = range; i > 0; --i) {
                    BlockPos pose = pPlayer.level().clip(new ClipContext(pPlayer.getEyePosition(1f), pPlayer.getEyePosition(1f).add(pPlayer.getViewVector(1f).scale(i)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, pPlayer)).getBlockPos();
                    Block target = level.getBlockState(pose).getBlock();
                    if (target instanceof AirBlock) {
                        serverPlayer.connection.teleport(pose.getCenter().x(), pose.getCenter().y(), pose.getCenter().z(), pPlayer.getYRot(), pPlayer.getXRot());
                        break;
                    }
                }
            }
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}