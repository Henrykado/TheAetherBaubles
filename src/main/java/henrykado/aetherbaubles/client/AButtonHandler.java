package henrykado.aetherbaubles.client;

import java.util.List;

import com.gildedgames.the_aether.client.gui.button.GuiAccessoryButton;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AButtonHandler {
	@SubscribeEvent
	public void removeAccessoryButton(GuiScreenEvent.InitGuiEvent.Post event) {
		List<GuiButton> list = event.getButtonList();
		for (GuiButton button : list)
			if (button instanceof GuiAccessoryButton) { 
				button.enabled = false; 
				button.visible = false; 
			}
	}
}
