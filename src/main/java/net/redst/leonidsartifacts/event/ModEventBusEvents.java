package net.redst.leonidsartifacts.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.load.InitParticles;
import net.redst.leonidsartifacts.particle.VaporParticle;

@Mod.EventBusSubscriber(modid = LeonidsArtifacts.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(InitParticles.VAPOR.get(),
                VaporParticle.Provider::new);
    }
}