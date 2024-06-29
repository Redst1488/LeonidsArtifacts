package net.redst.leonidsartifacts.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.Variables;
import net.redst.leonidsartifacts.network.packages.DoubleJump;

@Mod.EventBusSubscriber(modid = LeonidsArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void DoubleJump(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;

            if (player.input.jumping) {
                if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).canDoubleJump) {
                    if ((player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new Variables.PlayerVariables())).jumpAmount < 2) {
                        player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.jumpAmount++;
                            capability.syncPlayerVariables(player);
                        });
                        Message.PACKET_HANDLER.sendToServer(new DoubleJump(0, 0));
                        DoubleJump.pressAction(player, 0, 0);
                    }
                }
            }
        }
    }
}