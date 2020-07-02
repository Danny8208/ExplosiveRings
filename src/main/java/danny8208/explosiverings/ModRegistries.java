package danny8208.explosiverings;

import danny8208.explosiverings.entity.ExplosiveProjectile;
import danny8208.explosiverings.items.RingExplosive;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
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

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExplosiveRings.MODID);
    public static final RegistryObject<EntityType<ExplosiveProjectile>> EXPLOSIVE_PROJECTILE_TYPE = ENTITY_TYPES.register("explosive_projectile", () -> EntityType.Builder.<ExplosiveProjectile>create(ExplosiveProjectile::new, EntityClassification.MISC).size(1.0f, 1.0f).build("explosive_projectile"));

    protected static void init(IEventBus bus) {
        ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                RING_EXPLOSIVE.get()
        );
    }
}
