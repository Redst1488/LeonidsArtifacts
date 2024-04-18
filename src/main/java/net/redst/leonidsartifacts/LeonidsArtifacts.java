package net.redst.leonidsartifacts;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LeonidsArtifacts.MODID)
public class LeonidsArtifacts {
    public static final String MODID = "leonidsartifacts";
    private static final Logger LOGGER = LogUtils.getLogger();
    public LeonidsArtifacts() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
