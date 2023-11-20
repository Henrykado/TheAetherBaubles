package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class RepulsionShield extends CopyBaubles {
	public RepulsionShield(String name, AccessoryType type) {
		super(name, type);
	}
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		int damage = player.getCapability(AetherAPI.AETHER_PLAYER, null).getAccessoryInventory().getStackInSlot(2).getItemDamage();
		if (damage != 0) itemstack.setItemDamage(damage);;
	}
}
