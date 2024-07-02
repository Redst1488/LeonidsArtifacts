package net.redst.leonidsartifacts.load;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.effect.Agile;
import net.redst.leonidsartifacts.effect.Burn;
import net.redst.leonidsartifacts.effect.Stunned;
import net.redst.leonidsartifacts.effect.Vaped;

public class InitEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LeonidsArtifacts.MODID);
    public static final RegistryObject<MobEffect> BURN = EFFECTS.register("burn", Burn::new);
    public static final RegistryObject<MobEffect> AGILE = EFFECTS.register("agile", Agile::new);
    public static final RegistryObject<MobEffect> STUNNED = EFFECTS.register("stunned", Stunned::new);
    public static final RegistryObject<MobEffect> VAPED = EFFECTS.register("vaped", Vaped::new);


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}