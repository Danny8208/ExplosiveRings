package danny8208.explosiverings.entity;

import danny8208.explosiverings.ModRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosiveProjectile extends DamagingProjectileEntity implements IRendersAsItem {
    int explosionRadius = 1;

    public ExplosiveProjectile(EntityType<? extends ExplosiveProjectile> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    public ExplosiveProjectile(PlayerEntity player, int radius) {
        this(ModRegistries.EXPLOSIVE_PROJECTILE_TYPE.get(), player.world);
        setPosition(player.getPosX(), player.getPosYEye(), player.getPosZ());
        setMotion(player.getLookVec());
        explosionRadius = radius;
    }

    @Override
    protected void onImpact(RayTraceResult raytraceResult) {
        super.onImpact(raytraceResult);
        if (raytraceResult.getType() == RayTraceResult.Type.BLOCK) {
            world.createExplosion(this, raytraceResult.getHitVec().x, raytraceResult.getHitVec().y, raytraceResult.getHitVec().z, explosionRadius, Explosion.Mode.DESTROY);
            remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
