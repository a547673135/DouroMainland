package douromainland.item;

import douromainland.item.test.ItemDecreaseSPCurrent;
import douromainland.item.test.ItemIncreaseSPCurrent;
import douromainland.item.test.ItemIncreaseSPMax;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static Item soulStone;
	public static Item increaseSPMax;
	public static Item increaseSPCurrent;
	public static Item decreaseSPCurrent;

	static {
		soulStone = new ItemSoulStone();
		increaseSPMax = new ItemIncreaseSPMax();
		increaseSPCurrent = new ItemIncreaseSPCurrent();
		decreaseSPCurrent = new ItemDecreaseSPCurrent();
	}

	public ItemLoader() {
		GameRegistry.registerItem(soulStone, "soul_stone");
		GameRegistry.registerItem(increaseSPMax, "increase_SP_max");
		GameRegistry.registerItem(increaseSPCurrent, "increase_SP_current");
		GameRegistry.registerItem(decreaseSPCurrent, "decrease_SP_current");
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerRender(soulStone);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(GameRegistry.findUniqueIdentifierFor(item).toString(), "inventory"));
	}
}
