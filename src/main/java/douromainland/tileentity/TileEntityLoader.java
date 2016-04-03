package douromainland.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	public TileEntityLoader() {
		GameRegistry.registerTileEntity(TileEntitySoulAwakeningTable.class, "tile_entity_soul_awakening_table");
	}
}
