package net.redst.leonidsartifacts.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Comparator;

public class RadianceAura {
    public static void Burn(LevelAccessor world, Player player) {
        final Vec3 center = new Vec3((player.getX()), (player.getY()), (player.getZ()));
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (Entity entity : _entfound) {
            if (entity != player) {
                player.setSecondsOnFire(1);
            }
        }
    }
}
