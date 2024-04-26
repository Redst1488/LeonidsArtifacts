package net.redst.leonidsartifacts.load;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.packet.UseFirstArtifact;
import net.redst.leonidsartifacts.network.packet.UseSecondArtifact;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class InitKeyBinds {
    public static final KeyMapping USE_FIRST_ARTIFACT = new KeyMapping("key.leonidsartifacts.use_first_artifact", GLFW.GLFW_KEY_UNKNOWN, "key.category.leonidsartifacts.key");
    public static final KeyMapping USE_SECOND_ARTIFACT = new KeyMapping("key.leonidsartifacts.use_second_artifact", GLFW.GLFW_KEY_UNKNOWN, "key.category.leonidsartifacts.key");

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(USE_FIRST_ARTIFACT);
        event.register(USE_SECOND_ARTIFACT);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                if (USE_FIRST_ARTIFACT.consumeClick()) {
                    Message.PACKET_HANDLER.sendToServer(new UseFirstArtifact(0, 0));
                    UseFirstArtifact.pressAction(Minecraft.getInstance().player, 0, 0);
                }
                if (USE_SECOND_ARTIFACT.consumeClick()){
                    Message.PACKET_HANDLER.sendToServer(new UseSecondArtifact(0, 0));
                    UseSecondArtifact.pressAction(Minecraft.getInstance().player, 0, 0);
                }
            }
        }
    }

}
