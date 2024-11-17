package henrykado.aetherbaubles;

import baubles.api.cap.BaublesCapabilities;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
	modid = Tags.MODID,
	name = Tags.MODNAME,
	version = Tags.VERSION,
	dependencies = "required-after:baubles;required-after:aether_legacy;after:lost_aether;",
	acceptedMinecraftVersions = "[1.12.2]",
	useMetadata = true
)
public class AetherBaubles {	
	@Instance(Tags.MODID)
	public static AetherBaubles instance;
	
	@SidedProxy(modId = Tags.MODID, clientSide = "henrykado.aetherbaubles.client.ClientProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);
	
	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
	}

	//@EventHandler
	//public void initialization(FMLInitializationEvent event) {}
	
	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event)
	{
		if (ABConfig.renamePendants) {
			ItemsAether.iron_pendant.setTranslationKey("iron_amulet");
			ItemsAether.golden_pendant.setTranslationKey("golden_amulet");
			ItemsAether.zanite_pendant.setTranslationKey("zanite_amulet");
			ItemsAether.ice_pendant.setTranslationKey("ice_amulet");
		}

		proxy.postInitialization();
	}
}
