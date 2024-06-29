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
public class DoubleJump {
    int type, pressed;

    public DoubleJump(int type, int pressed) {
        this.type = type;
        this.pressed = pressed;
    }
    public DoubleJump(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressed = buffer.readInt();
    }
    public static void buffer(DoubleJump message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressed);
    }
    public static void handler(DoubleJump message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> pressAction(Objects.requireNonNull(context.getSender()), message.type, message.pressed));
        context.setPacketHandled(true);
    }
    public static void pressAction(Player entity, int type, int pressed) {
        if (type == 0) {
            {
                boolean Value = true;
                entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.DoubleJump = Value;
                    capability.syncPlayerVariables(entity);
                });
            }
            LeonidsArtifacts.queueServerWork(1, () -> {
                {
                    boolean Value = false;
                    entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.DoubleJump = Value;
                        capability.syncPlayerVariables(entity);
                    });
                }
            });
        }
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Message.addNetworkMessage(DoubleJump.class, DoubleJump::buffer, DoubleJump::new, DoubleJump::handler);
    }
}