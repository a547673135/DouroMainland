package douromainland.common;

import douromainland.DouroMainlandMod;
import douromainland.block.BlockLoader;
import douromainland.item.ItemLoader;
import douromainland.network.GuiHandler;
import douromainland.tileentity.TileEntityLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new ItemLoader();
		new BlockLoader();
		new TileEntityLoader();
	}

	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(DouroMainlandMod.instance, new GuiHandler());
	}
}
