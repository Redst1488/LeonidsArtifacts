package net.redst.leonidsartifacts;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.redst.leonidsartifacts.load.InitBlocks;
import net.redst.leonidsartifacts.load.InitEffects;
import net.redst.leonidsartifacts.load.InitItems;
import net.redst.leonidsartifacts.load.InitTabs;
import org.slf4j.Logger;

@Mod(LeonidsArtifacts.MODID)
public class LeonidsArtifacts {
    public static final String MODID = "leonidsartifacts";
    public static final Logger LOGGER = LogUtils.getLogger();
    public LeonidsArtifacts() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        InitTabs.register(modEventBus);
        InitItems.register(modEventBus);
        InitBlocks.register(modEventBus);
        InitEffects.register(modEventBus);
    }
}