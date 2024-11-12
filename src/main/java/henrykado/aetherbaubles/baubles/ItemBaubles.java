package henrykado.aetherbaubles.baubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import henrykado.aetherbaubles.ABConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemBaubles extends ItemAccessory implements IBauble {
	public ItemBaubles(AccessoryType type) {
		super(type);

		setMaxStackSize(1);
		setCreativeTab(AetherCreativeTabs.accessories);
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		BaubleType baubleType = this.getBaubleType(itemstack);

		if (baubleType.equals(BaubleType.RING) || baubleType.equals(BaubleType.TRINKET)) {
			IBaublesItemHandler inv = BaublesApi.getBaublesHandler((EntityPlayer) player);

			boolean first = true;
			for (int i : baubleType.getValidSlots()) {
				ItemStack baubleStack = inv.getStackInSlot(i);
				if (baubleStack != ItemStack.EMPTY && baubleStack.getItem() instanceof ItemAccessory) {
					if (((ItemAccessory) baubleStack.getItem()).getType().equals(accessoryType)) {
						if (first && (accessoryType.equals(AccessoryType.RING) || accessoryType.equals(AccessoryType.MISC))) {
							first = false;
							continue;
						}
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return switch (accessoryType) {
            case GLOVE -> ABConfig.glovesBaubleType;
            case RING -> ABConfig.ringBaubleType;
            case PENDANT -> ABConfig.pendantBaubleType;
            case CAPE -> ABConfig.capeBaubleType;
            case MISC -> ABConfig.miscBaubleType;
            case SHIELD -> ABConfig.shieldBaubleType;
        };
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
	{
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
		ItemStack heldItem = player.getHeldItem(hand);
		for(int i : getBaubleType(new ItemStack(this)).getValidSlots()) {
			if(baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, heldItem, player) && canEquip(heldItem, player)) {
				SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
				if (this.getEquipSound() != null) {
					soundEvent = this.getEquipSound();
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