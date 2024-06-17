package net.redst.leonidsartifacts.utils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.redst.leonidsartifacts.load.InitEffects;

import java.util.Comparator;
import java.util.List;
public class RadianceAura {
    public static void Burn (Player player, Level world) {
        final Vec3 center = new Vec3((player.getX()), (player.getY()), (player.getZ()));
        List<LivingEntity> entityFound = world.getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (LivingEntity entities : entityFound) {
            if (entities != player) {
                if (!(entities.hasEffect(InitEffects.BURN.get()))) {
                    entities.addEffect(new MobEffectInstance(InitEffects.BURN.get(), 20, 0, false, false));
                }
            }
        }
    }
}
