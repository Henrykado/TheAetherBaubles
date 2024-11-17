package henrykado.aetherbaubles;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RegistryHandler 
{
	@SubscribeEvent
	public void attachBaublesCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if (event.getObject().getItem() instanceof ItemAccessory) {
			event.addCapability(event.getObject().getItem().getRegistryName(), new ICapabilityProvider() {
				@Override
				public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
					return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
				}

				@Nullable
				@Override
				public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
					if (hasCapability(capability, facing)) {
						return BaublesCapabilities.CAPABILITY_ITEM_BAUBLE.cast(new IBauble() {
							@Override
							public BaubleType getBaubleType(ItemStack itemstack) {
								return switch (((ItemAccessory)itemstack.getItem()).getType()) {
									case GLOVE -> ABConfig.glovesBaubleType;
									case RING -> ABConfig.ringBaubleType;
									case PENDANT -> ABConfig.pendantBaubleType;
									case CAPE -> ABConfig.capeBaubleType;
									case MISC -> ABConfig.miscBaubleType;
									case SHIELD -> ABConfig.shieldBaubleType;
								};
							}

							@Override
							public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
                                BaubleType baubleType = itemstack.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null).getBaubleType(itemstack);

								if (baubleType.equals(BaubleType.RING) || baubleType.equals(BaubleType.TRINKET)) {
									AccessoryType accessoryType = ((ItemAccessory)itemstack.getItem()).getType();
									if (baubleType.equals(BaubleType.RING) && (accessoryType.equals(AccessoryType.RING) || accessoryType.equals(AccessoryType.MISC))) {
										return true;
									}

									boolean skipNext = accessoryType.equals(AccessoryType.RING) || accessoryType.equals(AccessoryType.MISC);

									IBaublesItemHandler inv = BaublesApi.getBaublesHandler((EntityPlayer) player);

									for (int i : baubleType.getValidSlots()) {
										ItemStack baubleStack = inv.getStackInSlot(i);
										if (baubleStack != ItemStack.EMPTY && baubleStack.getItem() instanceof ItemAccessory && ((ItemAccessory) baubleStack.getItem()).getType().equals(accessoryType)) {
											if (skipNext) {
												skipNext = false;
												continue;
											}
											return false;
										}
									}
								}
								return true;
							}
						});
					}

					return null;
				}
			});
		}
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void remapOutdatedItemMappings(RegistryEvent.MissingMappings<Item> event)
	{
        for (Mapping<Item> mapping : event.getMappings()) {
			try
			{
                mapping.remap(ItemsAether.itemRegistry.getValue(new ResourceLocation(Aether.modid, mapping.key.getPath())));
            }
			catch (Exception e)
			{
                AetherBaubles.LOGGER.warn("Failed to remap legacy bauble item: {}", mapping.key.getPath());
            }
        }
	}
}
