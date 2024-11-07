package henrykado.aetherbaubles.baubles;

import baubles.api.BaubleType;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;

import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class DamageBaubles extends ItemBaubles {
	public DamageBaubles(String name, AccessoryType type) {
		super(name, type);
	}
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		if (player.hasCapability(AetherAPI.AETHER_PLAYER, null)) {
			IAccessoryInventory inv = player.getCapability(AetherAPI.AETHER_PLAYER, null).getAccessoryInventory();
			int damage = inv.getStackInSlot(accessorySlot).getItemDamage();
			if (damage != 0) itemstack.setItemDamage(damage);
		}
	}
}
