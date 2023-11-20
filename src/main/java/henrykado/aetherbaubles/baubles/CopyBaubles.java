package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CopyBaubles extends ItemBaubles {
	
	protected int accessorySlot;
	
	public CopyBaubles(String name, AccessoryType type) {
		super(name, type);
		switch (type)
		{
			case CAPE: this.accessorySlot = 1; break;
			case SHIELD: this.accessorySlot = 2; break;
			case MISC: this.accessorySlot = 3; break;
			case GLOVE: this.accessorySlot = 6; break;
			default: break;
		}
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		super.onEquipped(itemstack, player);
		
		Item original = null;
		switch (name) {
			case "leather_gloves": original = ItemsAether.leather_gloves; break;
			case "iron_gloves": original = ItemsAether.iron_gloves; break;
			case "golden_gloves": original = ItemsAether.golden_gloves; break;
			case "chain_gloves": original = ItemsAether.chain_gloves; break;
			case "diamond_gloves": original = ItemsAether.diamond_gloves; break;
			case "zanite_gloves": original = ItemsAether.zanite_gloves; break;
			case "gravitite_gloves": original = ItemsAether.gravitite_gloves; break;
			case "neptune_gloves": original = ItemsAether.neptune_gloves; break;
			case "phoenix_gloves": original = ItemsAether.phoenix_gloves; break;
			case "obsidian_gloves": original = ItemsAether.obsidian_gloves; break;
			case "valkyrie_gloves": original = ItemsAether.valkyrie_gloves; break;
		
			case "red_cape": original = ItemsAether.red_cape; break;
			case "blue_cape": original = ItemsAether.blue_cape; break;
			case "yellow_cape": original = ItemsAether.yellow_cape; break;
			case "white_cape": original = ItemsAether.white_cape; break;
			case "swet_cape": original = ItemsAether.swet_cape; break;
			case "invisibility_cape": original = ItemsAether.invisibility_cape; break;
			case "agility_cape": original = ItemsAether.agility_cape; break;
			case "valkyrie_cape": original = ItemsAether.valkyrie_cape; break;
			
			//case "golden_feather": original = ItemsAether.golden_feather; break;
			case "regeneration_stone": original = ItemsAether.regeneration_stone; break;
			case "iron_bubble": original = ItemsAether.iron_bubble; break;
			
			case "repulsion_shield": original = ItemsAether.repulsion_shield; break;
		}
		
		IAccessoryInventory inv = AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory();
		if (original != null)
			inv.setInventorySlotContents(accessorySlot, new ItemStack(original));
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory().removeStackFromSlot(accessorySlot);
	}
}
