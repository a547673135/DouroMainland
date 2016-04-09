package douromainland.common;

import douromainland.DouroMainlandMod;
import douromainland.block.BlockLoader;
import douromainland.client.gui.GuiSoulPointBar;
import douromainland.item.ItemLoader;
import douromainland.network.GuiHandler;
import douromainland.tileentity.TileEntityLoader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new ItemLoader();
		new BlockLoader();
		new TileEntityLoader();
	}

	public void init(FMLInitializationEvent event) {
		new EventLoader();
		NetworkRegistry.INSTANCE.registerGuiHandler(DouroMainlandMod.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event) {
//		MinecraftForge.EVENT_BUS.register(new GuiSoulPointBar(Minecraft.getMinecraft()));		
	}
}
