package douromainland.common;

import douromainland.PlayerData;
import douromainland.References;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventLoader {
	public EventLoader() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && PlayerData.get((EntityPlayer) event.entity) == null) {
			PlayerData.register((EntityPlayer) event.entity);			
		}	
	}
	
	@SubscribeEvent
	void onClone(PlayerEvent.Clone event) {
	    NBTTagCompound temp = new NBTTagCompound();
	    event.original.getExtendedProperties(PlayerData.identifier).saveNBTData(temp);
	    event.entityPlayer.getExtendedProperties(PlayerData.identifier).loadNBTData(temp);
	}

	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityLivingBase entity = (EntityLivingBase) event.entity;
			PlayerData playerData = PlayerData.get((EntityPlayer) event.entity);
			if (!event.world.isRemote) {
				entity.getEntityAttribute(SharedMonsterAttributes.maxHealth)
						.setBaseValue(playerData.getMaxHealthPoints());
			}
			playerData.syncAll();
		}
	}

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent event) {
		if (event.type == ElementType.HEALTH) {
			event.setCanceled(true);
		}
		if (!event.isCancelable() && event.type == ElementType.EXPERIENCE) {
			
			Minecraft mc = Minecraft.getMinecraft();
			PlayerData playerData = PlayerData.get(mc.thePlayer);

			if (!mc.thePlayer.capabilities.isCreativeMode) {
				int posX = 10;
				int posY = 10;
				int width = 91;
				int height = 5;

				mc.renderEngine.bindTexture(new ResourceLocation(References.MODID + ":textures/gui/SP_HP_bar.png"));
				if (playerData.getMaxSoulPoints() > 0) {
					mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, width, height);
					mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 5,
							playerData.getCurrentSoulPoints() * width / playerData.getMaxSoulPoints(), height);
				}
				mc.ingameGUI.drawTexturedModalRect(posX, posY + 20, 0, 10, width, height);
				mc.ingameGUI.drawTexturedModalRect(posX, posY + 20, 0, 15,
						(int) (mc.thePlayer.getHealth() * width / playerData.getMaxHealthPoints()), height);

				if (playerData.getMaxSoulPoints() > 0) {
					mc.ingameGUI.drawString(mc.fontRendererObj,
							playerData.getCurrentSoulPoints() + "/" + playerData.getMaxSoulPoints(), posX + width, posY,
							Integer.parseInt("FFAA00", 16));
				}
				mc.ingameGUI.drawString(mc.fontRendererObj,
						(int) (mc.thePlayer.getHealth()) + "/" + playerData.getMaxHealthPoints(), posX + width,
						posY + 20, Integer.parseInt("FFAA00", 16));
			}
		}
	}
}
