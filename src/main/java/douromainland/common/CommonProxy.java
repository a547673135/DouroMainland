package douromainland.common;

import douromainland.DouroMainlandMod;
import douromainland.References;
import douromainland.block.BlockLoader;
import douromainland.item.ItemLoader;
import douromainland.network.GuiHandler;
import douromainland.network.MyMessage;
import douromainland.tileentity.TileEntityLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {	
	
	public void preInit(FMLPreInitializationEvent event) {
		new ItemLoader();
		new BlockLoader();
		new TileEntityLoader();
		References.network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
		References.network.registerMessage(MyMessage.Handler.class, MyMessage.class, 0, Side.CLIENT);
	}

	public void init(FMLInitializationEvent event) {
		new EventLoader();
		NetworkRegistry.INSTANCE.registerGuiHandler(DouroMainlandMod.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
