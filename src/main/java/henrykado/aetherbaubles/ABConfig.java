package henrykado.aetherbaubles;

import baubles.api.BaubleType;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AetherBaubles.MODID, name = "The Aether's Baubles")
public class ABConfig {
	@RequiresMcRestart
	@Name("Enable the Vanilla Gloves Accessories")
	@Config.Comment("Enables the Gloves Accessories that are made from Vanilla materials (e.g. Iron, Gold, Diamond, etc).")
	public static boolean enable_vgloves = true;
	
	@RequiresMcRestart
	@Name("Enable the Aether Gloves Accessories")
	public static boolean enable_agloves = true;
	
	@RequiresMcRestart
	@Name("Enable the Ring Accessories")
	public static boolean enable_rings = true;
	
	@RequiresMcRestart
	@Name("Enable the Pendant Accessories")
	public static boolean enable_pendants = true;
	
	@RequiresMcRestart
	@Name("Enable the Cape Accessories")
	public static boolean enable_capes = true;
	
	@RequiresMcRestart
	@Name("Enable the Miscellaneous Accessories")
	@Config.Comment("Enables Golden Feather, Regeneration Stone, and Iron Bubble.")
	public static boolean enable_miscellaneous = true;
	
	@RequiresMcRestart
	@Name("Enable the Repulsion Shield")
	public static boolean enable_repulsion_shield = true;
	
	@RequiresMcRestart
	@Name("Rename Pendants to Amulets")
	@Config.Comment("Changes Pendants' display names so they are referred to as Amulets")
	public static boolean rename_pendants = true;
	
	@Name("Gloves Accessories' Baubles Type")
	public static BaubleType gloves_baubletype = BaubleType.RING;
	
	@Name("Enable Equip Sounds")
	@Config.Comment("Enables equip sounds when equipping an aether bauble")
	public static boolean enable_equip_sounds = true;
	
	@Mod.EventBusSubscriber(modid = AetherBaubles.MODID)
	private static class EventHandler
	{
		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals(AetherBaubles.MODID))
			{
				ConfigManager.sync(AetherBaubles.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
