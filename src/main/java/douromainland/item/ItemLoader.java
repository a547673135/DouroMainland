package douromainland.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	public static Item soulStone;

	static {
		soulStone = new ItemSoulStone();
	}

	public ItemLoader() {
		GameRegistry.registerItem(soulStone, "soul_stone");
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
