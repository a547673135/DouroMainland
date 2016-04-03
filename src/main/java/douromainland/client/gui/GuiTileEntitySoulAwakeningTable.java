package douromainland.client.gui;

import douromainland.tileentity.TileEntitySoulAwakeningTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTileEntitySoulAwakeningTable extends GuiContainer {

	public GuiTileEntitySoulAwakeningTable(IInventory playerInv, TileEntitySoulAwakeningTable tileEntitySoulAwakeningTable) {
		super(new ContainerTileEntitySoulAwakeningTable(playerInv, tileEntitySoulAwakeningTable));
		this.xSize = 176;
        this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	    this.mc.getTextureManager().bindTexture(new ResourceLocation("douromainlandmod:textures/gui/container/tile_entity_soul_awakening_table_gui.png"));
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

}
