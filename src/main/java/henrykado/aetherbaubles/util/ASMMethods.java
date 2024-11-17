package henrykado.aetherbaubles.util;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import henrykado.aetherbaubles.ABConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ASMMethods {
    public static final AccessoryType[] ACC_TYPE_LOOKUP = {AccessoryType.PENDANT, AccessoryType.CAPE, AccessoryType.SHIELD, AccessoryType.MISC, AccessoryType.RING, AccessoryType.RING, AccessoryType.GLOVE, AccessoryType.MISC};

    public static ItemStack getAccessoryStack(int slotID, EntityPlayer player)
    {
        if (player == null) return ItemStack.EMPTY;

        IBaublesItemHandler inv = BaublesApi.getBaublesHandler(player);

        AccessoryType accType = ACC_TYPE_LOOKUP[slotID];

        BaubleType baubleType = switch (accType) {
            case PENDANT -> ABConfig.pendantBaubleType;
            case CAPE -> ABConfig.capeBaubleType;
            case SHIELD -> ABConfig.shieldBaubleType;
            case RING -> ABConfig.ringBaubleType;
            case GLOVE -> ABConfig.glovesBaubleType;
            default -> ABConfig.miscBaubleType; // MISC
        };

        boolean skipNext = slotID == 5 || slotID == 7;

        for (int i : baubleType.getValidSlots()) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof ItemAccessory && ((ItemAccessory)stack.getItem()).getType().equals(accType)) {
                if (skipNext) {
                    skipNext = false;
                    continue;
                }
                return inv.getStackInSlot(i);
            }
        }

        return ItemStack.EMPTY;
    }

    public static ItemStack setAccessoryStack(int slotID, ItemStack stack, EntityPlayer player)
    {
        if (player == null) return ItemStack.EMPTY;

        IBaublesItemHandler inv = BaublesApi.getBaublesHandler(player);

        BaubleType baubleType = switch (slotID) {
            case 0 -> ABConfig.pendantBaubleType;
            case 1 -> ABConfig.capeBaubleType;
            case 2 -> ABConfig.shieldBaubleType;
            case 4, 5 -> ABConfig.ringBaubleType;
            case 6 -> ABConfig.glovesBaubleType;
            default -> ABConfig.miscBaubleType; // 3, 7
        };

        for (int i : baubleType.getValidSlots()) {
            if (inv.isItemValidForSlot(i, stack, player) && inv.getStackInSlot(i).isEmpty()) {
                inv.setStackInSlot(i, stack);
                return stack;
            }
        }

        return ItemStack.EMPTY;
    }

    public static NonNullList<ItemStack> getBaublesItemstackList(EntityPlayer player)
    {
        IBaublesItemHandler inv = BaublesApi.getBaublesHandler(player);

        NonNullList<ItemStack> list = NonNullList.withSize(inv.getSlots(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getSlots(); i++) {
            list.set(i, inv.getStackInSlot(i).getItem() instanceof ItemAccessory ? inv.getStackInSlot(i) : ItemStack.EMPTY);
        }

        return list;
    }

    public static ActionResult<ItemStack> onItemRightClick(ItemAccessory item, EntityPlayer player, EnumHand hand)
    {
        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
        ItemStack heldItem = player.getHeldItem(hand);

        IBauble cap = heldItem.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null);
        for(int i : cap.getBaubleType(new ItemStack(item)).getValidSlots()) {
            if(baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, heldItem, player) && cap.canEquip(heldItem, player)) {
                SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
                if (item.getEquipSound() != null) {
                    soundEvent = item.getEquipSound();
                }
                player.playSound(soundEvent, 1.0F, 1.0F);

                baubles.setStackInSlot(i, heldItem.copy());
                if(!player.capabilities.isCreativeMode) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                }

                return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
            }
        }

        return new ActionResult<>(EnumActionResult.FAIL, heldItem);
    }
}
