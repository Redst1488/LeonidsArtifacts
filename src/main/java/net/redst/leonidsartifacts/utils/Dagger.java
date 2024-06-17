package net.redst.leonidsartifacts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.redst.leonidsartifacts.load.InitSounds;

public class Dagger {
    private static final int range = 15;
    public static void Blink(LevelAccessor world, Player player, ItemStack stack) {
        if (world instanceof ServerLevel level) {
            if (player instanceof ServerPlayer serverPlayer) {
                level.sendParticles(ParticleTypes.LARGE_SMOKE, (player.getX()), (player.getY()), (player.getZ()), 100, 1, 1, 1, 0.1);
                level.playSound(null, BlockPos.containing(player.getX(), player.getY(), player.getZ()), InitSounds.BLINK.get(), SoundSource.NEUTRAL, 1, 1);
                player.getCooldowns().addCooldown(stack.getItem(), 160);

                for (int i = range; i > 0; i--) {
                    BlockPos pose = player.level().clip(new ClipContext(player.getEyePosition(1f), player.getEyePosition(1f).add(player.getViewVector(1f).scale(i)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getBlockPos();
                    Block target = (level.getBlockState(pose).getBlock());

                    if (target instanceof AirBlock) {
                        serverPlayer.connection.teleport((pose.getCenter().x), (pose.getCenter().y), (pose.getCenter().z), player.getYRot(), player.getXRot());
                        break;
                    }
                }
            }
        }
    }
}