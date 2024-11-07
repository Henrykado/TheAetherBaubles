package henrykado.aetherbaubles.baubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.client.renders.items.util.AetherColor;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.inv.BaublesInventoryWrapper;
import henrykado.aetherbaubles.AetherBaubles;
import henrykado.aetherbaubles.ABConfig;
import henrykado.aetherbaubles.RegistryHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBaubles extends ItemAccessory implements IBauble {
	protected SoundEvent equipSound;
	protected int accessorySlot;

	public ItemBaubles(String name, AccessoryType type) {
		super(type);

		setMaxStackSize(1);
		setCreativeTab(AetherCreativeTabs.accessories);

		if (type == AccessoryType.PENDANT && ABConfig.renamePendants)
			setTranslationKey(name.replace("pendant", "amulet"));
		else
			setTranslationKey(name);

		setRegistryName(Aether.modid + ":" + name);

		switch (type) {
			case PENDANT:
				this.accessorySlot = 0;
				break;
			case CAPE:
				this.accessorySlot = 1;
				break;
			case SHIELD:
				this.accessorySlot = 2;
				break;
			case MISC:
				this.accessorySlot = 3;
				break;
			case RING:
				this.accessorySlot = 4;
				break;
			case GLOVE:
				this.accessorySlot = 6;
				break;
		}

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
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		BaubleType baubleType = this.getBaubleType(itemstack);
		AetherBaubles.LOGGER.info(baubleType);
		if (baubleType.equals(BaubleType.RING) || baubleType.equals(BaubleType.TRINKET)) {
			BaublesInventoryWrapper inv = new BaublesInventoryWrapper(player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null), (EntityPlayer) player);

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
		BaubleType btype = BaubleType.TRINKET;
		switch (accessoryType) {
			case GLOVE:
				btype = ABConfig.glovesBaubleType;
				break;
			case RING:
				btype = ABConfig.ringBaubleType;
				break;
			case PENDANT:
				btype = ABConfig.pendantBaubleType;
				break;
			case CAPE:
				btype = ABConfig.capeBaubleType;
				break;
			case MISC:
				btype = ABConfig.miscBaubleType;
				break;
			case SHIELD:
				btype = ABConfig.shieldBaubleType;
				break;
		}
		return btype;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		if (ABConfig.enableEquipSounds) {
			SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
			if (equipSound != null) soundEvent = equipSound;
			player.playSound(soundEvent, 1.0F, 1.0F);
		}

		IAccessoryInventory inv = AetherAPI.getInstance().get((EntityPlayer) player).getAccessoryInventory();

		if (accessoryType.equals(AccessoryType.RING) && getEquippedAccessoriesCount(inv) == 1) {
			accessorySlot = 5;
		}
		else if (accessoryType.equals(AccessoryType.MISC) && getEquippedAccessoriesCount(inv) == 1) {
			accessorySlot = 7;
		}

		inv.setInventorySlotContents(accessorySlot, new ItemStack(this));
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		IAccessoryInventory inv = AetherAPI.getInstance().get((EntityPlayer) player).getAccessoryInventory();
		inv.removeStackFromSlot(accessorySlot);
	}


	public int getEquippedAccessoriesCount(IAccessoryInventory inv) {
		int count = 0;

		if (this.accessoryType.equals(AccessoryType.RING) || this.accessoryType.equals(AccessoryType.MISC)) {
			for (int i : this.getBaubleType(new ItemStack(this)).getValidSlots()) {
				if (!inv.getStackInSlot(i).isEmpty() && !((ItemAccessory)inv.getStackInSlot(i).getItem()).getType().equals(this.accessoryType)) {
					count++;
				}
			}
		}

		return count;
	}
}