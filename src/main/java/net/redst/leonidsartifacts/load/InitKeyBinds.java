package net.redst.leonidsartifacts.load;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redst.leonidsartifacts.LeonidsArtifacts;

import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.packages.FirstPress;
import net.redst.leonidsartifacts.network.packages.SecondPress;
import org.lwjgl.glfw.GLFW;

public class InitKeyBinds {
    public static final String KEY_CATEGORY_LA = "key.category.leonidsartifacts.key";
    public static final String KEY_FIRST_ARTIFACT = "key.leonidsartifacts.use_first_artifact";
    public static final String KEY_SECOND_ARTIFACT = "key.leonidsartifacts.use_second_artifact";
    public static final KeyMapping USE_FIRST_ARTIFACT = new KeyMapping(KEY_FIRST_ARTIFACT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_LA) {
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                Message.PACKET_HANDLER.sendToServer(new FirstPress(0, 0));
                assert Minecraft.getInstance().player != null;
                FirstPress.pressAction(Minecraft.getInstance().player, 0, 0);

            }
            isDownOld = isDown;
        }
    };
    public static final KeyMapping USE_SECOND_ARTIFACT = new KeyMapping(KEY_SECOND_ARTIFACT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_LA) {
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                Message.PACKET_HANDLER.sendToServer(new SecondPress(0, 0));
                assert Minecraft.getInstance().player != null;
                SecondPress.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };
    @Mod.EventBusSubscriber(modid = LeonidsArtifacts.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(USE_FIRST_ARTIFACT);
            event.register(USE_SECOND_ARTIFACT);
        }
    }
}
