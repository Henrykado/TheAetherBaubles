package henrykado.aetherbaubles;

import java.util.ArrayList;
import java.util.List;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;

import henrykado.aetherbaubles.baubles.*;

import henrykado.aetherbaubles.client.ABColor;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryHandler 
{
	public static List<Item> ITEMS = new ArrayList<Item>();

	public RegistryHandler()
	{
		ItemsAether.leather_gloves = new LeatherGloves("leather_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
		ItemsAether.iron_gloves = new ItemBaubles("iron_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
		ItemsAether.golden_gloves = new ItemBaubles("golden_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD).setColor(0xFBF424);
		ItemsAether.chain_gloves = new ItemBaubles("chain_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN).setTexture("chain");
		ItemsAether.diamond_gloves = new ItemBaubles("diamond_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setColor(0x33ebcb);

		ItemsAether.zanite_gloves = new ItemBaubles("zanite_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setTexture("zanite");
		ItemsAether.gravitite_gloves = new ItemBaubles("gravitite_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("gravitite");
		ItemsAether.neptune_gloves = new ItemBaubles("neptune_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot().setTexture("neptune");
		ItemsAether.phoenix_gloves = new ItemBaubles("phoenix_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot().setTexture("phoenix").setMaxDamage(152);
		ItemsAether.obsidian_gloves = new ItemBaubles("obsidian_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("obsidian").setDungeonLoot();
		ItemsAether.valkyrie_gloves = new ItemBaubles("valkyrie_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setTexture("valkyrie").setDungeonLoot();

		ItemsAether.iron_ring = ((ItemAccessory) new ItemBaubles("iron_ring", AccessoryType.RING).setColor(0xdddddd)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
		ItemsAether.golden_ring = ((ItemAccessory) new ItemBaubles("golden_ring", AccessoryType.RING).setColor(0xeaee57)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD);
		ItemsAether.zanite_ring = new ZaniteBaubles("zanite_ring", AccessoryType.RING).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(49);
		ItemsAether.ice_ring = new DamageBaubles("ice_ring", AccessoryType.RING).setColor(0x95e6e7).setMaxDamage(125);

		ItemsAether.iron_pendant = ((ItemAccessory) new ItemBaubles("iron_pendant", AccessoryType.PENDANT).setColor(0xdddddd)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
		ItemsAether.golden_pendant = ((ItemAccessory) new ItemBaubles("golden_pendant", AccessoryType.PENDANT).setColor(0xeaee57)).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD);
		ItemsAether.zanite_pendant = new ZaniteBaubles("zanite_pendant", AccessoryType.PENDANT).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(98);
		ItemsAether.ice_pendant = new DamageBaubles("ice_pendant", AccessoryType.PENDANT).setColor(0x95e6e7).setMaxDamage(250);

		ItemsAether.red_cape = new ItemBaubles("red_cape", AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0xe81111); //.setElytraTexture("base_elytra")
		ItemsAether.blue_cape = new ItemBaubles("blue_cape", AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0x137fb7); //.setElytraTexture("base_elytra")
		ItemsAether.yellow_cape = new ItemBaubles("yellow_cape", AccessoryType.CAPE).setElytraTexture("base_elytra").setColor(0xcdcb0e); //.setElytraTexture("base_elytra")
		ItemsAether.white_cape = new ItemBaubles("white_cape", AccessoryType.CAPE).setElytraTexture("base_elytra"); //.setElytraTexture("base_elytra")
		ItemsAether.swet_cape = new ItemBaubles("swet_cape", AccessoryType.CAPE).setTexture("swet_cape").setElytraTexture("swet_cape_elytra").setDungeonLoot();
		ItemsAether.invisibility_cape = new ItemBaubles("invisibility_cape", AccessoryType.CAPE).setDungeonLoot();
		ItemsAether.agility_cape = new ItemBaubles("agility_cape", AccessoryType.CAPE).setTexture("agility_cape").setElytraTexture("agility_cape_elytra").setDungeonLoot();
		ItemsAether.valkyrie_cape = new ItemBaubles("valkyrie_cape", AccessoryType.CAPE).setTexture("valkyrie_cape").setElytraTexture("valkyrie_cape_elytra").setDungeonLoot();

		ItemsAether.golden_feather = new ItemBaubles("golden_feather", AccessoryType.MISC).setDungeonLoot();
		ItemsAether.regeneration_stone = new ItemBaubles("regeneration_stone", AccessoryType.MISC).setDungeonLoot();
		ItemsAether.iron_bubble = new ItemBaubles("iron_bubble", AccessoryType.MISC).setDungeonLoot();

		ItemsAether.repulsion_shield = new DamageBaubles("repulsion_shield", AccessoryType.SHIELD).setTexture("repulsion").setInactiveTexture("repulsion_movement").setDungeonLoot().setMaxDamage(512);
	}

	@SubscribeEvent
	public void onRegisterItemEvent(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> itemRegistry = event.getRegistry();

		itemRegistry.registerAll(ITEMS.toArray(new Item[0]));
	}

	@SideOnly(Side.CLIENT)
	public static void registerColors()
	{
		for (int i = 0; i < ITEMS.size(); i++)
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ABColor(ITEMS.get(i)), ITEMS.get(i));
	}
}
