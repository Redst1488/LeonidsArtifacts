package net.redst.leonidsartifacts.load;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LeonidsArtifacts.MODID);
    public static final RegistryObject<SoundEvent> BLINK = SOUNDS.register("blink", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("leonidsartifacts", "blink")));
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
