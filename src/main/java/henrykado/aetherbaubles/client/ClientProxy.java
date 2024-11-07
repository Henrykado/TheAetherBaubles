package henrykado.aetherbaubles.client;

import com.gildedgames.the_aether.client.renders.items.ItemRendering;
import henrykado.aetherbaubles.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import com.gildedgames.the_aether.client.AetherKeybinds;

import henrykado.aetherbaubles.CommonProxy;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashSet;

public class ClientProxy extends CommonProxy {
	@Override
	public void postInitialization()
	{
		MinecraftForge.EVENT_BUS.register(new AButtonHandler());
		AetherKeybinds.keyBindingAccessories = new KeyBinding("key.aether.accessory_menu", Keyboard.KEY_NONE, "key.aether.category");

		RegistryHandler.registerColors();
	}
}
