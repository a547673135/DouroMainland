package douromainland.client;

import douromainland.block.BlockLoader;
import douromainland.item.ItemLoader;

public class ItemRenderLoader {
	public ItemRenderLoader() {
		ItemLoader.registerRenders();
		BlockLoader.registerRenders();
	}
}
