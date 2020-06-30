package danny8208.explosiverings;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeGroup extends ItemGroup {
    public CreativeGroup() {
        super(ExplosiveRings.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return ItemStack.EMPTY;
    }
}
