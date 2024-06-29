package net.redst.leonidsartifacts.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Variables {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        Message.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
    }
    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent event) {
        event.register(PlayerVariables.class);
    }
    @Mod.EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }
        @SubscribeEvent
        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }
        @SubscribeEvent
        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }
        @SubscribeEvent
        public static void clonePlayer(PlayerEvent.Clone event) {
            event.getOriginal().revive();
            PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
            PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
            clone.isFirstPressed = original.isFirstPressed;
            clone.isSecondPressed = original.isSecondPressed;
            clone.RadianceActive = original.RadianceActive;
            clone.DoubleJump = original.DoubleJump;
            clone.canDoubleJump = original.canDoubleJump;
            clone.jumpAmount = original.jumpAmount;
            event.isWasDeath();
        }
    }
    public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
    });
    @Mod.EventBusSubscriber
    private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
                event.addCapability(new ResourceLocation("leonidsartifacts", "player_variables"), new PlayerVariablesProvider());
        }
        private final PlayerVariables playerVariables = new PlayerVariables();
        private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);
        @Override
        public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
            return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
        }
        @Override
        public Tag serializeNBT() {
            return playerVariables.writeNBT();
        }
        @Override
        public void deserializeNBT(Tag nbt) {
            playerVariables.readNBT(nbt);
        }
    }
    public static class PlayerVariables {
        public boolean isFirstPressed = false;
        public boolean isSecondPressed = false;
        public boolean RadianceActive = true;
        public boolean DoubleJump = false;
        public boolean canDoubleJump = false;
        public int jumpAmount = 0;
        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                Message.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
        }
        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putBoolean("isFirstPressed", isFirstPressed);
            nbt.putBoolean("isSecondPressed", isSecondPressed);
            nbt.putBoolean("RadianceActive", RadianceActive);
            nbt.putBoolean("DoubleJump", DoubleJump);
            nbt.putBoolean("canDoubleJump", canDoubleJump);
            nbt.putInt("jumpAmount", jumpAmount);
            return nbt;
        }
        public void readNBT(Tag Tag) {
            CompoundTag nbt = (CompoundTag) Tag;
            isFirstPressed = nbt.getBoolean("isFirstPressed");
            isSecondPressed = nbt.getBoolean("isSecondPressed");
            RadianceActive = nbt.getBoolean("RadianceActive");
            DoubleJump = nbt.getBoolean("DoubleJump");
            canDoubleJump = nbt.getBoolean("canDoubleJump");
            jumpAmount = nbt.getInt("jumpAmount");
        }
    }
    public static class PlayerVariablesSyncMessage {
        private final PlayerVariables data;
        public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
            this.data = new PlayerVariables();
            this.data.readNBT(buffer.readNbt());
        }
        public PlayerVariablesSyncMessage(PlayerVariables data) {
            this.data = data;
        }
        public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag) message.data.writeNBT());
        }
        public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer()) {
                    assert Minecraft.getInstance().player != null;
                    PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
                    variables.isFirstPressed = message.data.isFirstPressed;
                    variables.isSecondPressed = message.data.isSecondPressed;
                    variables.RadianceActive = message.data.RadianceActive;
                    variables.DoubleJump = message.data.DoubleJump;
                    variables.canDoubleJump = message.data.canDoubleJump;
                    variables.jumpAmount = message.data.jumpAmount;
                }
            });
            context.setPacketHandled(true);
        }
    }
}