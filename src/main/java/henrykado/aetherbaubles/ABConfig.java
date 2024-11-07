package henrykado.aetherbaubles;

import baubles.api.BaubleType;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Tags.MODID, name = Tags.MODNAME)
public class ABConfig {
	@RequiresMcRestart
	@Name("Rename Pendants to Amulets")
	@Config.Comment("Changes Pendants' display names so they are referred to as Amulets")
	public static boolean renamePendants = true;

	@Name("Enable Equip Sounds")
	@Config.Comment("Enables equip sounds when equipping an aether bauble")
	public static boolean enableEquipSounds = true;

	@Name("Gloves Accessories' Baubles Type")
	public static BaubleType glovesBaubleType = BaubleType.RING;
	@Name("Pendant Accessories' Baubles Type")
	public static BaubleType pendantBaubleType = BaubleType.AMULET;
	@Name("Ring Accessories' Baubles Type")
	public static BaubleType ringBaubleType = BaubleType.RING;
	@Name("Miscellaneous Accessories' Baubles Type")
	public static BaubleType miscBaubleType = BaubleType.TRINKET;
	@Name("Cape Accessories' Baubles Type")
	public static BaubleType capeBaubleType = BaubleType.BODY;
	@Name("Repulsion Shield Baubles Type")
	public static BaubleType shieldBaubleType = BaubleType.BELT;
	
	@Mod.EventBusSubscriber(modid = Tags.MODID)
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
			if (event.getModID().equals(Tags.MODID))
			{
				ConfigManager.sync(Tags.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
