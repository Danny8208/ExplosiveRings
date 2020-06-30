package danny8208.explosiverings.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ExplosiveNBT {
    public static void addExplosives(ItemStack stack, int explosives) {
        stack.getTag().putInt("StoredExplosives", getExplosives(stack) + explosives);
    }

    public static void subtractExplosives(ItemStack stack, int explosives) {
        stack.getTag().putInt("StoredExplosives", getExplosives(stack) - explosives);
    }

    public static void addExplosivesTag(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
    }

    public static int getExplosives(ItemStack stack) {
        return stack.getTag().getInt("StoredExplosives");
    }
}
