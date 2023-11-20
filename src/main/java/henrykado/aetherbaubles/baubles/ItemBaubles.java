package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;

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
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBaubles extends Item implements IBauble {
	public String name;
	public AccessoryType type;
	private boolean isDungeonLoot;
	private int colorHex = 0xffffff;
	private SoundEvent equipSound;
	
	public ItemBaubles(String name, AccessoryType type)
	{
		setMaxStackSize(1);
		setCreativeTab(AetherBaubles.baublesCreativeTab);
		
		if (type == AccessoryType.PENDANT && ABConfig.rename_pendants)
			setTranslationKey(name.replace("pendant", "amulet"));
		else 
			setTranslationKey(name);
		
		setRegistryName(AetherBaubles.MODID + ":" + name);
		this.name = name;
		this.type = type;
		
		if (!ABConfig.enable_rings && type == AccessoryType.RING) return;
		else if (!ABConfig.enable_pendants && type == AccessoryType.PENDANT) return;
		else if (!ABConfig.enable_capes && type == AccessoryType.CAPE) return;
		else if (!ABConfig.enable_miscellaneous && type == AccessoryType.MISC) return;
		else if (!ABConfig.enable_repulsion_shield && type == AccessoryType.SHIELD) return;
		
		if (type == AccessoryType.GLOVE) 
			switch (name)
			{
				case "leather_gloves": case "iron_gloves": case "golden_gloves": 
				case "chain_gloves": case "diamond_gloves":
					if (!ABConfig.enable_vgloves) return;
					break;
				default:
					if (!ABConfig.enable_agloves) return;
					break;
			}
		
		RegistryHandler.ITEMS.add(this);
	}
	
	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) 
	{
		ItemStack is1, is2 = null;
		if (type == AccessoryType.GLOVE && ABConfig.gloves_baubletype == BaubleType.RING) 
		{
			BaublesInventoryWrapper inv = new BaublesInventoryWrapper(player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null), (EntityPlayer)player);
			
			is1 = inv.getStackInSlot(1);
			if (is1 != ItemStack.EMPTY && is1.getItem() instanceof ItemBaubles) {
				if ( ((ItemBaubles)is1.getItem()).name.contains("gloves")) return false;
			}
			is2 = inv.getStackInSlot(2);
			if (is2 != ItemStack.EMPTY && is2.getItem() instanceof ItemBaubles) {
				if ( ((ItemBaubles)is2.getItem()).name.contains("gloves")) return false;
			}
		}
		return true;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) 
	{
		BaubleType btype = BaubleType.AMULET;
		switch (type)
		{
			case GLOVE: btype = ABConfig.gloves_baubletype; break;
			case RING: btype = BaubleType.RING; break;
			case PENDANT: btype = BaubleType.AMULET; break;
			case CAPE: btype = BaubleType.BODY; break;
			case MISC: case SHIELD: btype = BaubleType.CHARM; break;
			default: break;
		}
		return btype;
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) 
	{
		if (ABConfig.enable_equip_sounds) {
			SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
			if (equipSound != null) soundEvent = equipSound;
			player.playSound(soundEvent, 1.0F, 1.0F);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public EnumRarity getRarity(ItemStack stack)
    {
    	return this.isDungeonLoot ? ItemsAether.aether_loot : super.getRarity(stack);
    }
	
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int meta)
    {
    	return this.colorHex;
    }
	
	public ItemBaubles setEquipSound(SoundEvent sound)
    {
        this.equipSound = sound;
        return this;
    }
	
	public ItemBaubles setDungeonLoot()
    {
    	this.isDungeonLoot = true;
    	return this;
    }
	
	public ItemBaubles setColor(int color)
    {
    	this.colorHex = color;
    	return this;
    }
}
