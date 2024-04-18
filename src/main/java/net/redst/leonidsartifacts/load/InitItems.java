package net.redst.leonidsartifacts.load;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;

public class InitItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeonidsArtifacts.MODID);
    public static final RegistryObject<Item> SIGMIUM_DUST = ITEMS.register("sigmium_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIGMIUM_INGOT = ITEMS.register("sigmium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SIGMIUM = ITEMS.register("raw_sigmium", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
