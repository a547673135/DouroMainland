package douromainland.client.gui;

import douromainland.tileentity.TileEntitySoulAwakeningTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerTileEntitySoulAwakeningTable extends Container {
	
	private TileEntitySoulAwakeningTable tileEntitySoulAwakeningTable;

	public ContainerTileEntitySoulAwakeningTable(IInventory playerInv, TileEntitySoulAwakeningTable tileEntitySoulAwakeningTable) {
		this.tileEntitySoulAwakeningTable = tileEntitySoulAwakeningTable;

		// Tile Entity, Slot 0, Slot IDs 0
		this.addSlotToContainer(new Slot(tileEntitySoulAwakeningTable, 0, 30, 35));

		// Player Inventory, Slot 9-35, Slot IDs 1-27
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		// Player Inventory, Slot 0-8, Slot IDs 28-36
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntitySoulAwakeningTable.isUseableByPlayer(playerIn);
	}

}
