package douromainland.network;

import douromainland.client.gui.ContainerTileEntitySoulAwakeningTable;
import douromainland.client.gui.GuiTileEntitySoulAwakeningTable;
import douromainland.client.gui.GuiTutorial;
import douromainland.tileentity.TileEntitySoulAwakeningTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	public static final int TILE_ENTITY_GUI_SOUL_AWAKENING_TABLE = 0;
	public static final int TUTORIAL_GUI = 1;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == TILE_ENTITY_GUI_SOUL_AWAKENING_TABLE)
	        return new ContainerTileEntitySoulAwakeningTable(player.inventory, (TileEntitySoulAwakeningTable) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == TILE_ENTITY_GUI_SOUL_AWAKENING_TABLE)
	        return new GuiTileEntitySoulAwakeningTable(player.inventory, (TileEntitySoulAwakeningTable) world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == TUTORIAL_GUI)
	        return new GuiTutorial();
		return null;
	}
}
