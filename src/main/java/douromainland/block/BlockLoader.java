package douromainland.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader {
	public static Block soulStoneOre;
	public static Block soulAwakeningTable; 
	
	static {
		soulStoneOre = new BlockSoulStoneOre();
		soulAwakeningTable = new BlockTileEntitySoulAwakeningTable();
	}
	
	public BlockLoader() {
		registerBlock(soulStoneOre, "soul_stone_ore");		
		registerBlock(soulAwakeningTable, "soul_awakening_table");
	}
	
	public void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, name);
	}

	@SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(soulStoneOre);
        registerRender(soulAwakeningTable);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(GameRegistry.findUniqueIdentifierFor(block).toString(), "inventory"));
    }
}
