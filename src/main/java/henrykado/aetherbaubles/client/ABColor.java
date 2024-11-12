package henrykado.aetherbaubles.client;

import henrykado.aetherbaubles.baubles.ItemBaubles;
import henrykado.aetherbaubles.baubles.LeatherGloves;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ABColor implements IItemColor {
    private final Item item;

    public ABColor(Item item)
    {
        this.item = item;
    }

    @Override
    public int colorMultiplier(@NotNull ItemStack stack, int tintIndex) {
        if (this.item.getClass() == LeatherGloves.class)
            return ((LeatherGloves) stack.getItem()).getColor(stack);

        return ((ItemBaubles) stack.getItem()).getColorFromItemStack(stack, 0);
    }
}
