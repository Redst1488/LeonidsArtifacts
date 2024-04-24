package net.redst.leonidsartifacts.utils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.redst.leonidsartifacts.load.InitEffects;


import java.util.List;
import java.util.Comparator;

public class RadianceAura {
    public static void Burn(LevelAccessor world, Player player) {
        final Vec3 center = new Vec3((player.getX()), (player.getY()), (player.getZ()));
        List<LivingEntity> _entfound = world.getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (LivingEntity entity : _entfound) {
            if (entity != player) {
                entity.addEffect(new MobEffectInstance(InitEffects.BURN.get(), 20, 0, false, false));
            }
        }
    }
}
