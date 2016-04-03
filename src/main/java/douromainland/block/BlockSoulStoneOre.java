package douromainland.block;

import java.util.Random;

import douromainland.item.ItemSoulStone;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulStoneOre extends Block {

	public BlockSoulStoneOre() {
		super(Material.rock);
		this.setUnlocalizedName("soul_stone_ore");
		this.setHardness(0.5F);
		this.setStepSound(soundTypeStone);
	}
}
