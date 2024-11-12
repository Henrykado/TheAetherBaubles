package henrykado.aetherbaubles.util;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import henrykado.aetherbaubles.ABConfig;
import henrykado.aetherbaubles.AetherBaubles;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class ASMMethods {
    public static ItemStack getAccessoryStack(int slotID, EntityPlayer player)
    {
        if (player == null) return ItemStack.EMPTY;

        IBaublesItemHandler inv = BaublesApi.getBaublesHandler(player);

        AccessoryType accType = switch (slotID) {
            case 0 -> AccessoryType.PENDANT;
            case 1 -> AccessoryType.CAPE;
            case 2 -> AccessoryType.SHIELD;
            case 4, 5 -> AccessoryType.RING;
            case 6 -> AccessoryType.GLOVE;
            default -> AccessoryType.MISC; // 3, 7
        };

        BaubleType baubleType = switch (accType) {
            case PENDANT -> ABConfig.pendantBaubleType;
            case CAPE -> ABConfig.capeBaubleType;
            case SHIELD -> ABConfig.shieldBaubleType;
            case RING -> ABConfig.ringBaubleType;
            case GLOVE -> ABConfig.glovesBaubleType;
            default -> ABConfig.miscBaubleType; // MISC
        };

        boolean skipNext = false;
        if (slotID == 5 || slotID == 7) skipNext = true;

        for (int i : baubleType.getValidSlots()) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof ItemAccessory && ((ItemAccessory)stack.getItem()).getType().equals(accType) && !skipNext) {
                return inv.getStackInSlot(i);
            }
            else skipNext = false;
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

        boolean skipNext = false;
        if (slotID == 5 || slotID == 7) skipNext = true;

        for (int i : baubleType.getValidSlots()) {
            if (inv.isItemValidForSlot(i, stack, player) && !skipNext) {
                inv.setStackInSlot(i, stack);
                return stack;
            }
            else skipNext = false;
        }

        return ItemStack.EMPTY;
    }

    public static NonNullList<ItemStack> getBaublesItemstackList(EntityPlayer player)
    {
        IBaublesItemHandler inv = BaublesApi.getBaublesHandler(player);

        NonNullList<ItemStack> list = NonNullList.withSize(inv.getSlots(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getSlots(); i++) {
            list.set(i, inv.getStackInSlot(i));
        }

        return list;
    }
}
