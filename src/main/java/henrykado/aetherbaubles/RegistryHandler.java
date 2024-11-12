package henrykado.aetherbaubles;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;

import com.legacy.lostaether.items.ItemsLostAether;
import henrykado.aetherbaubles.baubles.*;

import henrykado.aetherbaubles.util.RecipeDyedGloves;
import henrykado.aetherbaubles.client.ABColor;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryHandler 
{
	public static IForgeRegistry<Item> itemRegistry;

	public void initialization()
	{
		ItemsAether.leather_gloves = register("leather_gloves", new LeatherGloves(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER));
		ItemsAether.iron_gloves = register("iron_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON));
		ItemsAether.golden_gloves = register("golden_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD).setColor(0xFBF424));
		ItemsAether.chain_gloves = register("chain_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN).setTexture("chain"));
		ItemsAether.diamond_gloves = register("diamond_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setColor(0x33ebcb));

		ItemsAether.zanite_gloves = register("zanite_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setTexture("zanite"));
		ItemsAether.gravitite_gloves = register("gravitite_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("gravitite"));
		ItemsAether.neptune_gloves = register("neptune_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot().setTexture("neptune"));
		ItemsAether.phoenix_gloves = register("phoenix_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot().setTexture("phoenix").setMaxDamage(152));
		ItemsAether.obsidian_gloves = register("obsidian_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("obsidian").setDungeonLoot());
		ItemsAether.valkyrie_gloves = register("valkyrie_gloves", new ItemBaubles(AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("valkyrie").setDungeonLoot());

		ItemsAether.iron_ring = register("iron_ring", ((ItemAccessory) new ItemBaubles(AccessoryType.RING).setColor(0xdddddd)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON));
		ItemsAether.golden_ring = register("golden_ring", ((ItemAccessory) new ItemBaubles(AccessoryType.RING).setColor(0xeaee57)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
		ItemsAether.zanite_ring = register("zanite_ring", new ItemBaubles(AccessoryType.RING).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(49));
		ItemsAether.ice_ring = register("ice_ring", new ItemBaubles(AccessoryType.RING).setColor(0x95e6e7).setMaxDamage(125));

		ItemsAether.iron_pendant = register("iron_pendant", ((ItemAccessory) new ItemBaubles(AccessoryType.PENDANT).setColor(0xdddddd)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON));
		ItemsAether.golden_pendant = register("golden_pendant", ((ItemAccessory) new ItemBaubles(AccessoryType.PENDANT).setColor(0xeaee57)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD));
		ItemsAether.zanite_pendant = register("zanite_pendant", new ItemBaubles(AccessoryType.PENDANT).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(98));
		ItemsAether.ice_pendant = register("ice_pendant", new ItemBaubles(AccessoryType.PENDANT).setColor(0x95e6e7).setMaxDamage(250));

		ItemsAether.red_cape = register("red_cape", new ItemBaubles(AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0xe81111));
		ItemsAether.blue_cape = register("blue_cape", new ItemBaubles(AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0x137fb7));
		ItemsAether.yellow_cape = register("yellow_cape", new ItemBaubles(AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0xcdcb0e));
		ItemsAether.white_cape = register("white_cape", new ItemBaubles(AccessoryType.CAPE).setElytraTexture("base_elytra"));
		ItemsAether.swet_cape = register("swet_cape", new ItemBaubles( AccessoryType.CAPE).setTexture("swet_cape").setElytraTexture("swet_cape_elytra").setDungeonLoot());
		ItemsAether.invisibility_cape = register("invisibility_cape", new ItemBaubles(AccessoryType.CAPE).setDungeonLoot());
		ItemsAether.agility_cape = register("agility_cape", new ItemBaubles(AccessoryType.CAPE).setTexture("agility_cape").setElytraTexture("agility_cape_elytra").setDungeonLoot());
		ItemsAether.valkyrie_cape = register("valkyrie_cape", new ItemBaubles(AccessoryType.CAPE).setTexture("valkyrie_cape").setElytraTexture("valkyrie_cape_elytra").setDungeonLoot());

		ItemsAether.golden_feather = register("golden_feather", new ItemBaubles(AccessoryType.MISC).setDungeonLoot());
		ItemsAether.regeneration_stone = register("regeneration_stone", new ItemBaubles(AccessoryType.MISC).setDungeonLoot());
		ItemsAether.iron_bubble = register("iron_bubble", new ItemBaubles(AccessoryType.MISC).setDungeonLoot());

		ItemsAether.repulsion_shield = register("repulsion_shield", new ItemBaubles(AccessoryType.SHIELD).setTexture("repulsion").setInactiveTexture("repulsion_movement").setDungeonLoot().setMaxDamage(512));

		if (Loader.isModLoaded("lost_aether")) {
			ItemsLostAether.phoenix_cape = register("lost_aether", "phoenix_cape", new ItemBaubles(AccessoryType.CAPE).setTexture("phoenix_cape").setElytraTexture("phoenix_cape_elytra").setDungeonLoot().setMaxDamage(50));
			ItemsLostAether.sentry_shield = register("lost_aether", "sentry_shield", new ItemBaubles(AccessoryType.SHIELD).setTexture("sentry").setInactiveTexture("sentry").setDungeonLoot().setMaxDamage(30));
			ItemsLostAether.invisibility_gem = register("lost_aether", "invisibility_gem", new ItemBaubles(AccessoryType.MISC).setDungeonLoot());
			ItemsLostAether.power_gloves = register("lost_aether", "power_gloves", new ItemBaubles(AccessoryType.GLOVE).setTexture("power_gloves").setDungeonLoot().setMaxDamage(300));
		}
	}

	public Item register(String modId, String name, Item item)
	{
		if (((ItemAccessory)item).getType() == AccessoryType.PENDANT && ABConfig.renamePendants)
			item.setTranslationKey(name.replace("pendant", "amulet"));
		else
			item.setTranslationKey(name);

		item.setRegistryName(modId + ":" + name);

		itemRegistry.register(item);

		return item;
	}

	public Item register(String name, Item item)
	{
		return register(Aether.modid, name, item);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onRegisterItemEvent(RegistryEvent.Register<Item> event)
	{
		itemRegistry = event.getRegistry();
		initialization();
	}

	@SubscribeEvent
	public void onRegisterCraftingEvent(RegistryEvent.Register<IRecipe> event)
	{
		event.getRegistry().register(new RecipeDyedGloves().setRegistryName("aether_dyed_gloves"));
	}


	@SideOnly(Side.CLIENT)
	public static void registerColor(Item item)
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ABColor(item), item);
	}

	@SideOnly(Side.CLIENT)
	public static void registerColors()
	{
		registerColor(ItemsAether.leather_gloves);
		registerColor(ItemsAether.iron_gloves);
		registerColor(ItemsAether.golden_gloves);
		registerColor(ItemsAether.chain_gloves);
		registerColor(ItemsAether.diamond_gloves);
		registerColor(ItemsAether.zanite_gloves);
		registerColor(ItemsAether.gravitite_gloves);
		registerColor(ItemsAether.neptune_gloves);
		registerColor(ItemsAether.phoenix_gloves);
		registerColor(ItemsAether.obsidian_gloves);
		registerColor(ItemsAether.valkyrie_gloves);
		registerColor(ItemsAether.iron_ring);
		registerColor(ItemsAether.golden_ring);
		registerColor(ItemsAether.zanite_ring);
		registerColor(ItemsAether.ice_ring);
		registerColor(ItemsAether.iron_pendant);
		registerColor(ItemsAether.golden_pendant);
		registerColor(ItemsAether.zanite_pendant);
		registerColor(ItemsAether.ice_pendant);
		registerColor(ItemsAether.red_cape);
		registerColor(ItemsAether.blue_cape);
		registerColor(ItemsAether.yellow_cape);
		registerColor(ItemsAether.white_cape);
	}
}
