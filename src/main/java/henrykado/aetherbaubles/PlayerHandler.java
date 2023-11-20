package henrykado.aetherbaubles;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import henrykado.aetherbaubles.baubles.LeatherGloves;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.block.BlockCauldron.LEVEL;

public class PlayerHandler {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerStrVsBlock(BreakSpeed event)
	{
		float original = event.getNewSpeed();
		float f = original;

		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(Minecraft.getMinecraft().player);
		ItemStack zanite_ring;
		zanite_ring = baubles.getStackInSlot(1);
		zanite_ring = baubles.getStackInSlot(2) != ItemStack.EMPTY ? baubles.getStackInSlot(2) : zanite_ring;
		//ItemStack zanite_pendant;

		if (zanite_ring.getItem() == RegistryHandler.zanite_ring)
		{
			f *= (1F + (float)zanite_ring.getItemDamage() / (float)zanite_ring.getMaxDamage() * 3F);
		}
		
		if (f != original) event.setNewSpeed(original + f);
	}
	
	@SubscribeEvent
	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		if (event.getHand() == EnumHand.MAIN_HAND)
		{
			IBlockState block = event.getWorld().getBlockState(event.getPos());

			if (block.getBlock() == Blocks.CAULDRON)
			{
				BlockCauldron cauldron = (BlockCauldron) block.getBlock();

				int waterLevel = block.getValue(LEVEL);

				if (event.getItemStack().getItem() == RegistryHandler.leather_gloves)
				{
					LeatherGloves gloves = (LeatherGloves) event.getItemStack().getItem();

					if (waterLevel > 0)
					{
						if (gloves.hasColor(event.getItemStack()) && !event.getWorld().isRemote)
						{
							gloves.removeColor(event.getItemStack());
							cauldron.setWaterLevel(event.getWorld(), event.getPos(), block, waterLevel - 1);
							event.getEntityPlayer().addStat(StatList.ARMOR_CLEANED);
						}
					}
				}
			}
		}
	}
}
