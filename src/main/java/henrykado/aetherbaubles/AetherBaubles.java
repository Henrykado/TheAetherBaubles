package henrykado.aetherbaubles;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs.AetherTab;

import com.legacy.lostaether.items.ItemsLostAether;
import henrykado.aetherbaubles.baubles.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
	
	//public static AetherTab baublesCreativeTab = new AetherTab("aether_baubles");
	
	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
	}
	
	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event)
	{
		proxy.postInitialization();
	}
}
