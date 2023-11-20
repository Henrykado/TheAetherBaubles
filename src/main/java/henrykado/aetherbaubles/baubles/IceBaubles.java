package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.accessories.AccessoryType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class IceBaubles extends ItemBaubles {
	public IceBaubles(String name, AccessoryType type)
	{
		super(name, type);
	}
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) 
	{
		int i = MathHelper.floor(player.posX);
		int j = MathHelper.floor(player.getEntityBoundingBox().minY);
		int k = MathHelper.floor(player.posZ);

		for (int l = i - 1; l <= i + 1; l++)
		{
			for (int i1 = j - 1; i1 <= j + 1; i1++)
			{
				for (int j1 = k - 1; j1 <= k + 1; j1++)
				{
					IBlockState state = player.world.getBlockState(new BlockPos.MutableBlockPos().setPos(l, i1, j1));
					Block block = state.getBlock();
					BlockPos pos = new BlockPos(l, i1, j1);

					if (block == Blocks.WATER || block == Blocks.FLOWING_WATER)
					{
						if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0)
						{ player.world.setBlockState(pos, Blocks.ICE.getDefaultState()); }
						else { player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); }
					}
					else if (block == Blocks.LAVA || block == Blocks.FLOWING_LAVA)
					{
						if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0)
						{ player.world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState()); }
						else { player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); }
					}
					else
					{
						continue;
					}

					itemstack.damageItem(1, player);
				}
			}
		}
	}
	
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) 
	{		
		// Play Aether Equip Sound
	}
}
