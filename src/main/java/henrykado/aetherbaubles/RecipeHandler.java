package henrykado.aetherbaubles;

import com.gildedgames.the_aether.Aether;

import henrykado.aetherbaubles.baubles.ItemBaubles;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class RecipeHandler {
	@SubscribeEvent
	public void onRecipeRegistryEvent(RegistryEvent.Register<IRecipe> event)
	{
		IForgeRegistryModifiable<IRecipe> reg = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
		for (ItemBaubles i : RegistryHandler.ITEMS)
			reg.remove(Aether.locate(i.name));
	}	
}
