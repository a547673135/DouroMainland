package douromainland.item.test;

import douromainland.PlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemIncreaseSPMax extends ItemFood {

	public ItemIncreaseSPMax() {
		super(0, 0.0F, false);
		this.setAlwaysEdible();
        this.setUnlocalizedName("increase_SP_max");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote) {
			PlayerData playerData = PlayerData.get(player);
			playerData.increaseMaxSoulPoints(5);
			super.onFoodEaten(stack, worldIn, player);
		}		
	}
}
