package danny8208.explosiverings.items;

import danny8208.explosiverings.ModKeybinding;
import danny8208.explosiverings.ModRegistries;
import danny8208.explosiverings.entity.ExplosiveProjectile;
import danny8208.explosiverings.util.EnabledUtil;
import danny8208.explosiverings.util.ExplosiveNBT;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static danny8208.explosiverings.ExplosiveRings.GROUP;

public class RingExplosive extends Item {
    public RingExplosive() {
        super(new Properties().maxStackSize(1).group(GROUP));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ExplosiveNBT.addExplosiveTag(stack);
        TranslationTextComponent shiftMessage = new TranslationTextComponent("item.explosiverings.shift_message");

        if (!Screen.func_231173_s_()) tooltip.add(shiftMessage.func_240699_a_(TextFormatting.RED));
        if (Screen.func_231173_s_()) {
            tooltip.add(new TranslationTextComponent("item.explosiverings.directions"));
            tooltip.remove(shiftMessage);
        }
        tooltip.add(new TranslationTextComponent("item.explosiverings.active_state", EnabledUtil.isEnabled(stack)));
        tooltip.add(new TranslationTextComponent("item.explosiverings.stored_explosives", ExplosiveNBT.getExplosives(stack)));
        tooltip.add(new TranslationTextComponent("item.explosiverings.explosion_radius", ExplosiveNBT.getRadius(stack)));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote && entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityIn;

            if (ModKeybinding.EJECT_BALL.isPressed() && EnabledUtil.isEnabled(stack)) {
                if (player.inventory.getStackInSlot(9).getItem() == ModRegistries.CREATIVE_EXPLOSIVES.get())
                    worldIn.addEntity(new ExplosiveProjectile(player, ExplosiveNBT.getRadius(stack)));
                else if (ExplosiveNBT.getExplosives(stack) > ExplosiveNBT.getRadius(stack)) {
                    worldIn.addEntity(new ExplosiveProjectile(player, ExplosiveNBT.getRadius(stack)));
                    ExplosiveNBT.subtractExplosives(stack, ExplosiveNBT.getRadius(stack));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        PlayerInventory playerInv = playerIn.inventory;
        if (!worldIn.isRemote) {
            if (!playerIn.isSneaking()) {
                EnabledUtil.changeEnabled(playerIn.getHeldItem(handIn));
                playerIn.sendStatusMessage(new TranslationTextComponent("item.explosiverings.active_state", EnabledUtil.isEnabled(playerIn.getHeldItem(handIn))), true);
            }
            if (playerIn.isSneaking()) {
                if (playerInv.getStackInSlot(9).getItem() == Items.TNT) {
                    playerInv.getStackInSlot(9).shrink(1);
                    ExplosiveNBT.addExplosives(playerIn.getHeldItem(handIn), 10);
                }
                if (playerInv.getStackInSlot(9).getItem() == Items.GUNPOWDER) {
                    playerInv.getStackInSlot(9).shrink(1);
                    ExplosiveNBT.addExplosives(playerIn.getHeldItem(handIn), 2);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (!entity.world.isRemote && entity instanceof PlayerEntity) ExplosiveNBT.incrementRadius(stack);
        return false;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return EnabledUtil.isEnabled(stack);
    }
}
