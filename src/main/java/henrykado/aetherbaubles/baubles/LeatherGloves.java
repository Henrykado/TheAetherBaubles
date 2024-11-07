package henrykado.aetherbaubles.baubles;

import baubles.api.BaublesApi;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.inv.BaublesInventoryWrapper;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.items.accessories.ItemAccessoryDyable;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import henrykado.aetherbaubles.ABConfig;
import henrykado.aetherbaubles.AetherBaubles;
import henrykado.aetherbaubles.RegistryHandler;

import com.gildedgames.the_aether.items.ItemsAether;

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
	public String name;
	
	public LeatherGloves(String name, AccessoryType type) {
		super(type);
		
		setMaxStackSize(1);
		setCreativeTab(AetherCreativeTabs.accessories);
		setTranslationKey(name);
		setRegistryName(Aether.modid + ":" + name);
		
		this.name = name;
		this.equipSound = this.getEquipSound();

		RegistryHandler.ITEMS.add(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
	{
		if(!worldIn.isRemote) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for(int i : getBaubleType(new ItemStack(this)).getValidSlots()) {
				if(baubles.isItemValidForSlot(i, player.getHeldItem(hand), player) && canEquip(baubles.getStackInSlot(i), player)) {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
			}
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
	{
		BaubleType baubleType = this.getBaubleType(itemstack);
		if (baubleType.equals(BaubleType.RING) || baubleType.equals(BaubleType.TRINKET))
		{
			BaublesInventoryWrapper inv = new BaublesInventoryWrapper(player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null), (EntityPlayer)player);

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
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) 
	{
		if (ABConfig.enableEquipSounds) {
			SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
			if (equipSound != null) soundEvent = equipSound;
			player.playSound(soundEvent, 1.0F, 1.0F);
		}
		
		IAccessoryInventory inv = AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory();
		inv.setInventorySlotContents(6, new ItemStack(this));
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory().removeStackFromSlot(6);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return ABConfig.glovesBaubleType;
	}
}
