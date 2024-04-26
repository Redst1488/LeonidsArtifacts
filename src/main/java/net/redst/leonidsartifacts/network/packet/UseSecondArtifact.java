package net.redst.leonidsartifacts.network.packet;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.redst.leonidsartifacts.network.Message;
import net.redst.leonidsartifacts.network.Variable;
import net.redst.leonidsartifacts.utils.Keybinds;
import net.redst.leonidsartifacts.utils.ServerTick;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UseSecondArtifact {
    int type, pressedms;

    public UseSecondArtifact(int type, int pressedms) {
        this.type = type;
        this.pressedms = pressedms;
    }

    public UseSecondArtifact(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public static void buffer(UseSecondArtifact message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressedms);
    }

    public static void handler(UseSecondArtifact message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.pressedms);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {
            Keybinds.UseSecondPressed.Check(world,entity);
        }
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Message.addNetworkMessage(UseSecondArtifact.class, UseSecondArtifact::buffer, UseSecondArtifact::new, UseSecondArtifact::handler);
    }
}
