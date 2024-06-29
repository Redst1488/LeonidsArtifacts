package net.redst.leonidsartifacts.network.packages;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.Variables;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FirstPress {
    int type, pressed;
    public FirstPress(int type, int pressed) {
        this.type = type;
        this.pressed = pressed;
    }
    public FirstPress(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressed = buffer.readInt();
    }
    public static void buffer(FirstPress message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressed);
    }
    public static void handler(FirstPress message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> pressAction(Objects.requireNonNull(context.getSender()), message.type, message.pressed));
        context.setPacketHandled(true);
    }
    public static void pressAction(Player entity, int type, int pressed) {
        if (type == 0) {
            {
                boolean Value = true;
                entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.isFirstPressed = Value;
                    capability.syncPlayerVariables(entity);
                });
            }
            LeonidsArtifacts.queueServerWork(5, () -> {
                {
                    boolean Value = false;
                    entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.isFirstPressed = Value;
                        capability.syncPlayerVariables(entity);
                    });
                }
            });
        }
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Message.addNetworkMessage(FirstPress.class, FirstPress::buffer, FirstPress::new, FirstPress::handler);
    }
}