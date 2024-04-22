package net.redst.leonidsartifacts.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Comparator;

public class KohakuBroomUse {
    public static void Use(LevelAccessor world, Player entity) {
        final Vec3 _center = new Vec3((entity.getX()), (entity.getY()), (entity.getZ()));
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entityiterator != entity) {
                entity.attack(entityiterator);
            }
        }
    }
}
