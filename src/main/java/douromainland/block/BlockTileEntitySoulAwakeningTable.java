package douromainland.block;

import douromainland.DouroMainlandMod;
import douromainland.network.GuiHandler;
import douromainland.tileentity.TileEntitySoulAwakeningTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockTileEntitySoulAwakeningTable extends BlockContainer {
	protected BlockTileEntitySoulAwakeningTable() {
		super(Material.iron);
        this.setUnlocalizedName("soul_awakening_table");
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 3;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySoulAwakeningTable();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntitySoulAwakeningTable te = (TileEntitySoulAwakeningTable) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((TileEntitySoulAwakeningTable) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
	    if (!world.isRemote) {
	        player.openGui(DouroMainlandMod.instance, GuiHandler.TILE_ENTITY_GUI_SOUL_AWAKENING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}
}
