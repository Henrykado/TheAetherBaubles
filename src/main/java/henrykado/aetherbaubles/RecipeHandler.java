package henrykado.aetherbaubles;

import com.gildedgames.the_aether.Aether;

import henrykado.aetherbaubles.baubles.ItemBaubles;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@EventBusSubscriber
public class RecipeHandler { 
	@SubscribeEvent
	public static void onRecipeRegistryEvent(RegistryEvent.Register<IRecipe> event)
	{
		IForgeRegistryModifiable<IRecipe> reg = (IForgeRegistryModifiable<IRecipe>)event.getRegistry();
		reg.remove(new ResourceLocation("aether_legacy:blue_cape"));
		reg.remove(new ResourceLocation("aether_legacy:diamond_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:gold_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:gold_pendant"));
		reg.remove(new ResourceLocation("aether_legacy:gold_ring"));
		reg.remove(new ResourceLocation("aether_legacy:gravitite_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:iron_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:iron_pendant"));
		reg.remove(new ResourceLocation("aether_legacy:iron_ring"));
		reg.remove(new ResourceLocation("aether_legacy:leather_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:red_cape"));
		reg.remove(new ResourceLocation("aether_legacy:white_cape"));
		reg.remove(new ResourceLocation("aether_legacy:yellow_cape"));
		reg.remove(new ResourceLocation("aether_legacy:zanite_gloves"));
		reg.remove(new ResourceLocation("aether_legacy:zanite_pendant"));
		reg.remove(new ResourceLocation("aether_legacy:zanite_ring"));
	}	
}
