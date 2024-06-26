package net.redst.leonidsartifacts.load;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.redst.leonidsartifacts.LeonidsArtifacts;
public class InitTabs {
    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LeonidsArtifacts.MODID);
    public static final RegistryObject<CreativeModeTab> LA_TAB = TAB.register("la_tab",
            () -> CreativeModeTab.builder().title(Component.translatable("tabs.leonidsartifacts.la_tab")).icon(() -> new ItemStack(InitBlocks.SIGMIUM_ORE.get())).displayItems((parameters, tabData) -> {
                tabData.accept(InitItems.RADIANCE.get());
                tabData.accept(InitItems.BROOM.get());
            }).build());
    public static void register(IEventBus eventBus) {
        TAB.register(eventBus);
    }
}