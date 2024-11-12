package henrykado.aetherbaubles.client;

import henrykado.aetherbaubles.RegistryHandler;
import org.lwjgl.input.Keyboard;

import com.gildedgames.the_aether.client.AetherKeybinds;

import henrykado.aetherbaubles.CommonProxy;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	@Override
	public void postInitialization()
	{
		MinecraftForge.EVENT_BUS.register(new AccessoryButtonHandler());
		AetherKeybinds.keyBindingAccessories = new KeyBinding("key.aether.accessory_menu", Keyboard.KEY_NONE, "key.aether.category");

		RegistryHandler.registerColors();
	}
}
