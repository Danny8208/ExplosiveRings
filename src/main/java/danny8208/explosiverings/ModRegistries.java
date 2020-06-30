package danny8208.explosiverings;

import danny8208.explosiverings.items.RingExplosive;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExplosiveRings.MODID);

    public static final RegistryObject<Item> RING_EXPLOSIVE = ITEMS.register("explosive_ring", RingExplosive::new);

    protected static void init(IEventBus bus) {
        ITEMS.register(bus);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                RING_EXPLOSIVE.get()
        );
    }
}
