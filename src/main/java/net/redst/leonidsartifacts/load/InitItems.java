package net.redst.leonidsartifacts.load;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;
import net.redst.leonidsartifacts.item.*;

public class InitItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeonidsArtifacts.MODID);
    public static final RegistryObject<Item> SIGMIUM_DUST = ITEMS.register("sigmium_dust", () -> new SigmiumDust());
    public static final RegistryObject<Item> SIGMIUM_INGOT = ITEMS.register("sigmium_ingot", () -> new SigmiumIngot());
    public static final RegistryObject<Item> RAW_SIGMIUM = ITEMS.register("raw_sigmium", () -> new RawSigmium());
    public static final RegistryObject<Item> KOHAKU_BROOM = ITEMS.register("kohaku_broom", () -> new KohakuBroom());
    public static final RegistryObject<Item> RADIANCE = ITEMS.register("radiance", () -> new Radiance());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
