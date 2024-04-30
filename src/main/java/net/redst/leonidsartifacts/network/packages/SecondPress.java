package net.redst.leonidsartifacts.network.packages;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.Variables;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SecondPress {
    int type, pressed;
    public SecondPress(int type, int pressed) {
        this.type = type;
        this.pressed = pressed;
    }
    public SecondPress(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressed = buffer.readInt();
    }
    public static void buffer(SecondPress message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressed);
    }
    public static void handler(SecondPress message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(Objects.requireNonNull(context.getSender()), message.type, message.pressed);
        });
        context.setPacketHandled(true);
    }
    public static void pressAction(Player entity, int type, int pressed) {
        Level world = entity.level();
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {
            {
                boolean Value = true;
                entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.isSecondPressed = Value;
                    capability.syncPlayerVariables(entity);
                });;
            }
            LeonidsArtifacts.queueServerWork(5, () -> {
                {
                    boolean Value = false;
                    entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.isSecondPressed = Value;
                        capability.syncPlayerVariables(entity);
                    });
                }
            });
        }
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Message.addNetworkMessage(SecondPress.class, SecondPress::buffer, SecondPress::new, SecondPress::handler);
    }
}
