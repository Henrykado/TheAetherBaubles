package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.accessories.AccessoryType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ZaniteBaubles extends ItemBaubles {
	public ZaniteBaubles(String name, AccessoryType type)
	{
		super(name, type);
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) 
	{
		if (player.ticksExisted % 400 == 0) itemstack.damageItem(1, player);
	}
}
