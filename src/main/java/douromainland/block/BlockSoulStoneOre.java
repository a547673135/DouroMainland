package douromainland.block;

import java.util.Random;

import douromainland.item.ItemLoader;
import douromainland.item.ItemSoulStone;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulStoneOre extends Block {

	public BlockSoulStoneOre() {
		super(Material.rock);
		this.setUnlocalizedName("soul_stone_ore");
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 3);
		this.setStepSound(soundTypeStone);
	}
		
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) {
			this.spawnAsEntity(worldIn, pos, new ItemStack(ItemLoader.soulStone, (int)(2 + 3 * Math.random())));
		}
		
	}
	
}
