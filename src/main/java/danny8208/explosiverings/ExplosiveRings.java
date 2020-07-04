package danny8208.explosiverings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("explosiverings")
public class ExplosiveRings {
    public static final String MODID = "explosiverings";
    public static final CreativeGroup GROUP = new CreativeGroup();
    public static final Logger logger = LogManager.getLogger();

    public ExplosiveRings() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        ModRegistries.init(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModKeybinding.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(ModRegistries.EXPLOSIVE_PROJECTILE_TYPE.get(), manager -> new SpriteRenderer<>(manager, itemRenderer, 1, true));
    }
}
