package danny8208.explosiverings;

import danny8208.explosiverings.entity.ExplosiveProjectile;
import danny8208.explosiverings.items.RingExplosive;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static danny8208.explosiverings.ExplosiveRings.GROUP;

public class ModRegistries {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExplosiveRings.MODID);
    public static final RegistryObject<Item> RING_EXPLOSIVE = ITEMS.register("explosive_ring", RingExplosive::new);
    public static final RegistryObject<Item> CREATIVE_EXPLOSIVES = ITEMS.register("creative_explosives", () -> new Item(new Item.Properties().group(GROUP).maxStackSize(1)));

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExplosiveRings.MODID);
    public static final RegistryObject<EntityType<ExplosiveProjectile>> EXPLOSIVE_PROJECTILE_TYPE = ENTITY_TYPES.register("explosive_projectile", () -> EntityType.Builder.<ExplosiveProjectile>create(ExplosiveProjectile::new, EntityClassification.MISC).size(1.0f, 1.0f).setShouldReceiveVelocityUpdates(false).build("explosive_projectile"));

    protected static void init(IEventBus bus) {
        ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
    }
}
