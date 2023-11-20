package henrykado.aetherbaubles.baubles;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.accessories.ItemAccessoryDyable;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LeatherGloves extends ItemBaubles
{
	//public ResourceLocation texture = Aether.locate("textures/armor/accessory_base.png");
	//public ResourceLocation texture_slim = Aether.locate("textures/armor/accessory_base_slim.png");
	
	public LeatherGloves(String name, AccessoryType type) {
		super(name, type);
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) 
	{
		super.onEquipped(itemstack, player);
		IAccessoryInventory inv = AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory();
		inv.setInventorySlotContents(6, new ItemStack(ItemsAether.leather_gloves));
		ItemStack gloves = inv.getStackInSlot(6);
		((ItemAccessoryDyable)gloves.getItem()).setColor(inv.getStackInSlot(6), this.getColor(itemstack));
		inv.setInventorySlotContents(6, gloves);
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		AetherAPI.getInstance().get((EntityPlayer)player).getAccessoryInventory().removeStackFromSlot(6);
	}
	
	
	public boolean hasColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return (nbttagcompound != null && nbttagcompound.hasKey("display", 10)) && nbttagcompound.getCompoundTag("display").hasKey("color", 3);
    }

    public int getColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color", 3))
            {
                return nbttagcompound1.getInteger("color");
            }
        }

        return 0xa06540;
    }

    public void removeColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color"))
            {
                nbttagcompound1.removeTag("color");
            }
        }
    }

    public void setColor(ItemStack stack, int color)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display", 10))
        {
            nbttagcompound.setTag("display", nbttagcompound1);
        }

        nbttagcompound1.setInteger("color", color);
    }

    public boolean hasOverlay(ItemStack stack)
    {
        return getColor(stack) != 0x00FFFFFF;
    }
}
