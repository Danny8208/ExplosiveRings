package danny8208.explosiverings.entity;

import danny8208.explosiverings.ModRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosiveProjectile extends AbstractFireballEntity {
    public ExplosiveProjectile(EntityType<? extends ExplosiveProjectile> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    public ExplosiveProjectile(PlayerEntity player) {
        this(ModRegistries.EXPLOSIVE_PROJECTILE_TYPE.get(), player.world);
        setStack(new ItemStack(Items.TORCH));
        setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
        setMotion(player.getLookVec());
    }

    @Override
    protected void onImpact(RayTraceResult raytraceResult) {
        super.onImpact(raytraceResult);
        world.createExplosion(this, raytraceResult.getHitVec().x, raytraceResult.getHitVec().y, raytraceResult.getHitVec().z, 5, Explosion.Mode.BREAK);
        remove();
    }
}
