package henrykado.aetherbaubles.client;

import org.lwjgl.input.Keyboard;

import com.gildedgames.the_aether.client.AetherKeybinds;

import henrykado.aetherbaubles.CommonProxy;
import henrykado.aetherbaubles.RegistryHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInitialization()
	{
		MinecraftForge.EVENT_BUS.register(new AButtonHandler());
		AetherKeybinds.keyBindingAccessories = new KeyBinding("key.aether.accessory_menu", Keyboard.KEY_NONE, "key.aether.category");
	}
	
	@Override
	public void initialization()
	{
		RegistryHandler.registerColors();
	}
}
