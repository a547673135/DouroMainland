package douromainland.item;

import douromainland.DouroMainlandMod;
import douromainland.network.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSoulStone extends Item {
	public ItemSoulStone() {
		super();
		this.setUnlocalizedName("soul_stone");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if (worldIn.isRemote) {
            playerIn.openGui(DouroMainlandMod.instance, GuiHandler.TUTORIAL_GUI, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        }
        return itemStackIn;
	}
}