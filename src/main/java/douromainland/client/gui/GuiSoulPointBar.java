package douromainland.client.gui;

import douromainland.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiSoulPointBar extends Gui {
	private Minecraft mc;

	public GuiSoulPointBar(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent.Post event) {
		if(!event.isCancelable() && event.type == ElementType.EXPERIENCE) {
			if(!mc.thePlayer.capabilities.isCreativeMode) {
				int posX = event.resolution.getScaledWidth() /2 + 10;
				int posY = event.resolution.getScaledHeight() - 48;
				
				System.out.println("x: " + posX);
				System.out.println("y: " + posY);

				mc.renderEngine.bindTexture(new ResourceLocation(References.MODID + ":textures/gui/soul_point_bar.png"));
				mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, 91, 10);
			//	mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 5, 50, 4);
				
			}
		}
	}
}
