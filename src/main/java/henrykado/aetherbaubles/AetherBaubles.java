package henrykado.aetherbaubles;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs.AetherTab;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = AetherBaubles.MODID,
	name = AetherBaubles.NAME,
	version = AetherBaubles.VERSION,
	dependencies = "required-after:baubles;required-after:aether_legacy",
	acceptedMinecraftVersions = "[1.12.2]",
	useMetadata = true
)
public class AetherBaubles {
	public static final String MODID = "aether_baubles";
	public static final String NAME = "The Aether's Baubles";
	public static final String VERSION = "1.0";
	
	@Instance(MODID)
	public static AetherBaubles instance;
	
	@SidedProxy(modId = MODID, clientSide = "henrykado.aetherbaubles.client.ClientProxy", serverSide = "henrykado.aetherbaubles.CommonProxy")
	public static CommonProxy proxy;
	
	public static AetherTab baublesCreativeTab = new AetherTab("aether_baubles");
	
	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		proxy.preInitialization();
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
		MinecraftForge.EVENT_BUS.register(new LootHandler());
		//AetherKeybinds.keyBindings[0].setKeyCode(Keyboard.KEY_NONE);
		
	}
	
	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		proxy.initialization();
		baublesCreativeTab.setIcon(new ItemStack(ItemsAether.golden_ring));
		MinecraftForge.EVENT_BUS.register(new PlayerHandler());
		//MinecraftForge.EVENT_BUS.register(new RecipeHandler());
	}
}
