package douromainland.common;

import org.lwjgl.opengl.GL11;

import douromainland.References;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventLoader {
	public EventLoader() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent event) {
		if (event.type == ElementType.HEALTH) {
			event.setCanceled(true);
		}
		if (!event.isCancelable() && event.type == ElementType.EXPERIENCE) {
			Minecraft mc = Minecraft.getMinecraft();
			if (!mc.thePlayer.capabilities.isCreativeMode) {
				int posX = 10;
				int posY = 10;

				mc.renderEngine
						.bindTexture(new ResourceLocation(References.MODID + ":textures/gui/SP_HP_bar.png"));
				mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, 91, 5);
				mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 5, 50, 5);
				mc.ingameGUI.drawTexturedModalRect(posX, posY + 20, 0, 10, 91, 5);
				mc.ingameGUI.drawTexturedModalRect(posX, posY + 20, 0, 15, (int)(mc.thePlayer.getHealth()*91/20), 5);
			}
		}
	}
}
