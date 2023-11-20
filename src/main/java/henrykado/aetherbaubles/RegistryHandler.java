package henrykado.aetherbaubles;

import java.util.ArrayList;
import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.accessories.AccessoryType;

import henrykado.aetherbaubles.baubles.*;
import henrykado.aetherbaubles.client.ABColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryHandler 
{
	public static List<ItemBaubles> ITEMS = new ArrayList<ItemBaubles>();
	
	public static Item leather_gloves = new LeatherGloves("leather_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
	public static Item iron_gloves = new CopyBaubles("iron_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	public static Item golden_gloves = new CopyBaubles("golden_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD).setColor(0xFBF424);
	public static Item chain_gloves = new CopyBaubles("chain_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
	public static Item diamond_gloves = new CopyBaubles("diamond_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setColor(0x33ebcb);
	
	public static Item zanite_gloves = new CopyBaubles("zanite_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	public static Item gravitite_gloves = new CopyBaubles("gravitite_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND);
	public static Item neptune_gloves = new CopyBaubles("neptune_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot();
	public static Item phoenix_gloves = new CopyBaubles("phoenix_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot().setMaxDamage(152);
	public static Item obsidian_gloves = new CopyBaubles("obsidian_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot();
	public static Item valkyrie_gloves = new CopyBaubles("valkyrie_gloves", AccessoryType.GLOVE).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).setDungeonLoot();
	
	public static Item iron_ring = new ItemBaubles("iron_ring", AccessoryType.RING).setColor(0xdddddd).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	public static Item golden_ring = new ItemBaubles("golden_ring", AccessoryType.RING).setColor(0xeaee57).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD);
	public static Item zanite_ring = new ZaniteBaubles("zanite_ring", AccessoryType.RING).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(49);
	public static Item ice_ring = new IceBaubles("ice_ring", AccessoryType.RING).setColor(0x95e6e7).setMaxDamage(125);
	
	public static Item iron_pendant = new ItemBaubles("iron_pendant", AccessoryType.PENDANT).setColor(0xdddddd).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	public static Item golden_pendant = new ItemBaubles("golden_pendant", AccessoryType.PENDANT).setColor(0xeaee57).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD);
	public static Item zanite_pendant = new ZaniteBaubles("zanite_pendant", AccessoryType.PENDANT).setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).setMaxDamage(98);
	public static Item ice_pendant = new IceBaubles("ice_pendant", AccessoryType.PENDANT).setColor(0x95e6e7).setMaxDamage(250);
	
	public static Item red_cape = new CopyBaubles("red_cape", AccessoryType.CAPE).setColor(0xe81111); //.setElytraTexture("base_elytra")
	public static Item blue_cape = new CopyBaubles("blue_cape", AccessoryType.CAPE).setColor(0x137fb7); //.setElytraTexture("base_elytra")
	public static Item yellow_cape = new CopyBaubles("yellow_cape", AccessoryType.CAPE).setColor(0xcdcb0e); //.setElytraTexture("base_elytra")
	public static Item white_cape = new CopyBaubles("white_cape", AccessoryType.CAPE); //.setElytraTexture("base_elytra")
	public static Item swet_cape = new CopyBaubles("swet_cape", AccessoryType.CAPE).setDungeonLoot();
	public static Item invisibility_cape = new CopyBaubles("invisibility_cape", AccessoryType.CAPE).setDungeonLoot();
	public static Item agility_cape = new CopyBaubles("agility_cape", AccessoryType.CAPE).setDungeonLoot();
	public static Item valkyrie_cape = new CopyBaubles("valkyrie_cape", AccessoryType.CAPE).setDungeonLoot();
	
	//public static Item golden_feather = new CopyBaubles("golden_feather", AccessoryType.MISC).setDungeonLoot();
	public static Item regeneration_stone = new CopyBaubles("regeneration_stone", AccessoryType.MISC).setDungeonLoot();
	public static Item iron_bubble = new CopyBaubles("iron_bubble", AccessoryType.MISC).setDungeonLoot();
	
	public static Item repulsion_shield = new RepulsionShield("repulsion_shield", AccessoryType.SHIELD).setDungeonLoot().setMaxDamage(512);
	
	
	@SubscribeEvent
	public void onRegisterItemEvent(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> itemRegistry = event.getRegistry();
		
		itemRegistry.registerAll(ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event)
	{
		for (int i = 0; i < ITEMS.size(); i++)
			ModelLoader.setCustomModelResourceLocation(ITEMS.get(i), 0, new ModelResourceLocation(Aether.modAddress() + ITEMS.get(i).name, "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerColors()
	{
		for (int i = 0; i < ITEMS.size(); i++)
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ABColor(ITEMS.get(i)), ITEMS.get(i));
	}
	
	@SubscribeEvent
	public void onRegisterCraftingEvent(RegistryEvent.Register<IRecipe> event)
	{
		event.getRegistry().register(new RecipeBaublesDyes().setRegistryName("aether_baubles_dyed_gloves"));
	}
}
