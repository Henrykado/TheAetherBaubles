package henrykado.aetherbaubles.baubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.items.accessories.ItemAccessoryDyable;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import henrykado.aetherbaubles.ABConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class LeatherGloves extends ItemAccessoryDyable implements IBauble
{
	private final SoundEvent equipSound;
	
	public LeatherGloves(AccessoryType type) {
		super(type);
		
		setMaxStackSize(1);
		setCreativeTab(AetherCreativeTabs.accessories);

		this.equipSound = this.getEquipSound();
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
	{
		BaubleType baubleType = this.getBaubleType(itemstack);
		if (baubleType.equals(BaubleType.RING) || baubleType.equals(BaubleType.TRINKET))
		{
			IBaublesItemHandler inv = BaublesApi.getBaublesHandler((EntityPlayer) player);

			for (int i : baubleType.getValidSlots())
			{
				ItemStack baubleStack = inv.getStackInSlot(i);
				if (baubleStack != ItemStack.EMPTY && baubleStack.getItem() instanceof ItemAccessory) {
					if (((ItemAccessory)baubleStack.getItem()).getType().equals(accessoryType)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return ABConfig.glovesBaubleType;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
	{
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
		for(int i : getBaubleType(new ItemStack(this)).getValidSlots()) {
			if(baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player) && canEquip(player.getHeldItem(hand), player)) {
				SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
				if (this.getEquipSound() != null) {
					soundEvent = this.getEquipSound();
				}
				player.playSound(soundEvent, 1.0F, 1.0F);

				baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
				if(!player.capabilities.isCreativeMode) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
				break;
			}
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
}
