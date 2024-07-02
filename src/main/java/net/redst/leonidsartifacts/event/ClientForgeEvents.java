package net.redst.leonidsartifacts.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.load.InitItems;
import net.redst.leonidsartifacts.network.Variables;

@Mod.EventBusSubscriber(modid = LeonidsArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {
    @SubscribeEvent
    public static void DoubleJump(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;

            if (player.getMainHandItem().getItem() == InitItems.BROOM.get()) {
                if (player.onGround()) {
                    player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.jumpAmount = 0;
                        capability.syncPlayerVariables(player);
                    });
                } else {
                    if (((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).jumpAmount == 0)) {
                        player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.jumpAmount = 1;
                            capability.syncPlayerVariables(player);
                        });
                        player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.canDoubleJump = true;
                            capability.syncPlayerVariables(player);
                        });
                    }
                    if (player.input.jumping) {
                        if (!(player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).canDoubleJump) {
                            if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).jumpAmount < 2) {
                                player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                    capability.jumpAmount++;
                                    capability.syncPlayerVariables(player);
                                });
                                player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                    capability.DoubleJump = true;
                                    capability.syncPlayerVariables(player);
                                });
                                player.jumpFromGround();
                            }
                        }
                        player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.canDoubleJump = true;
                            capability.syncPlayerVariables(player);
                        });

                    } else {
                        player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.canDoubleJump = false;
                            capability.syncPlayerVariables(player);
                        });
                    }
                }
            }
        }
    }
}