package net.redst.leonidsartifacts.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.redst.leonidsartifacts.network.Variable;

public class Keybinds {
    public static class UseFirstPress {
        public static void Check(LevelAccessor world, Entity entity) {
            if (entity == null) {
                return;
            }
            {
                boolean Value = true;
                entity.getCapability(Variable.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.isFirstPressed = Value;
                    capability.syncPlayerVariables(entity);
                });
            }
            ServerTick.skipTime(20, () -> {});
            {
                boolean Value = false;
                entity.getCapability(Variable.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.isFirstPressed = Value;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
    }

    public static class UseSecondPressed {
        public static void Check(LevelAccessor world, Entity entity) {
            if (entity == null)
                return;
            {
                boolean Value = true;
                entity.getCapability(Variable.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.isSecondPressed = Value;
                    capability.syncPlayerVariables(entity);
                });
            }
            ServerTick.skipTime(20, () -> {
                {
                    boolean Value = false;
                    entity.getCapability(Variable.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.isSecondPressed = Value;
                        capability.syncPlayerVariables(entity);
                    });
                }
            });
        }
    }
}
