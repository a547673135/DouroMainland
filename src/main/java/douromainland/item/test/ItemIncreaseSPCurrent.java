package douromainland.item.test;

import douromainland.PlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIncreaseSPCurrent extends Item {
	public ItemIncreaseSPCurrent() {
		super();
        this.setUnlocalizedName("increase_SP_current");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if(!worldIn.isRemote) {
			PlayerData playerData = PlayerData.get(playerIn);
			playerData.increaseCurrentSoulPoints(1);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
