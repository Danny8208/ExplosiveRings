package danny8208.explosiverings.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ExplosiveNBT {
    public static void addExplosiveTag(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
            stack.getTag().putInt("StoredExplosives", 0);
            stack.getTag().putInt("ExplosionRadius", 1);
        }
    }

    public static void addExplosives(ItemStack stack, int explosives) {
        stack.getTag().putInt("StoredExplosives", getExplosives(stack) + explosives);
    }

    public static void subtractExplosives(ItemStack stack, int explosives) {
        stack.getTag().putInt("StoredExplosives", getExplosives(stack) - explosives);
    }

    public static int getExplosives(ItemStack stack) {
        return stack.getTag().getInt("StoredExplosives");
    }

    public static void incrementRadius(ItemStack stack) {
        if (getRadius(stack) < 11) stack.getTag().putInt("ExplosionRadius", getRadius(stack) + 1);
        else if (getRadius(stack) == 11) stack.getTag().putInt("ExplosionRadius", 1);
    }

    public static int getRadius(ItemStack stack) {
        return stack.getTag().getInt("ExplosionRadius");
    }
}
