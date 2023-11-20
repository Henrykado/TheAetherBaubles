package henrykado.aetherbaubles;

import henrykado.aetherbaubles.baubles.ItemBaubles;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootHandler {
	@SubscribeEvent
	public void onLootTableLoadEvent(LootTableLoadEvent event)
	{
		switch (event.getName().toString()) {
			case "aether_legacy:chests/bronze_dungeon_chest":
			case "aether_legacy:chests/bronze_dungeon_chest_sub0":
			case "aether_legacy:chests/bronze_dungeon_chest_sub1":
			case "aether_legacy:chests/bronze_dungeon_chest_sub2":
			case "aether_legacy:chests/bronze_dungeon_chest_sub3":
				
			case "aether_legacy:chests/silver_dungeon_chest":
			case "aether_legacy:chests/silver_dungeon_chest_sub0":
			case "aether_legacy:chests/silver_dungeon_chest_sub1":
			case "aether_legacy:chests/silver_dungeon_chest_sub2":
			case "aether_legacy:chests/silver_dungeon_chest_sub3":
			case "aether_legacy:chests/silver_dungeon_chest_sub4":
			case "aether_legacy:chests/silver_dungeon_chest_sub5":
			case "aether_legacy:chests/silver_dungeon_chest_sub6":
				
			case "aether_legacy:chests/bronze_dungeon_reward":
				
			case "aether_legacy:chests/silver_dungeon_reward":
			case "aether_legacy:chests/silver_dungeon_reward_sub0":
				
			case "aether_legacy:chests/gold_dungeon_reward":
			case "aether_legacy:chests/gold_dungeon_reward_sub0":
			case "aether_legacy:chests/gold_dungeon_reward_sub1":
			case "aether_legacy:chests/gold_dungeon_reward_sub2":
			case "aether_legacy:chests/gold_dungeon_reward_sub3":
			case "aether_legacy:chests/gold_dungeon_reward_sub4":
				LootTable table = event.getTable();
				LootPool pool = table.getPool("main");
				
				for (ItemBaubles i : RegistryHandler.ITEMS)
					pool = replaceEntry(pool, i);
				
				table.removePool("main");
				table.addPool(pool);
				
				event.setTable(table);
				break;
		}
	}
	
	public LootPool replaceEntry(LootPool pool, ItemBaubles replacement)
	{
		String replaced = "aether_legacy:" + replacement.name;
		if (pool.getEntry(replaced) != null) 
		{
			pool.removeEntry(replaced);
			pool.addEntry(new LootEntryItem(replacement, 1, 0, new LootFunction[0], new LootCondition[0], replaced));
		}
		
		return pool;
	}
}
