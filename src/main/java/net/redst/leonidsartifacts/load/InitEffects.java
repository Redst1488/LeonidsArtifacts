package net.redst.leonidsartifacts.load;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.effect.Burn;

public class InitEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LeonidsArtifacts.MODID);

    public static final RegistryObject<MobEffect> BURN = EFFECTS.register("burn", () -> new Burn());

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
