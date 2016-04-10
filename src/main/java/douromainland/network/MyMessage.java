package douromainland.network;

import douromainland.PlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MyMessage implements IMessage {
	
	private NBTTagCompound serverPlayerData;

	public MyMessage() {
	}
	
	public MyMessage(NBTTagCompound serverPlayerData) {
		this.serverPlayerData = serverPlayerData;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		serverPlayerData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, serverPlayerData);
	}

	public static class Handler implements IMessageHandler<MyMessage, IMessage> {

		@Override
		public IMessage onMessage(final MyMessage message, final MessageContext ctx) {
			
			// (WorldServer) ctx.getServerHandler().playerEntity.worldObj for server side
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					PlayerData clientPlayerData = PlayerData.get(Minecraft.getMinecraft().thePlayer);
					if(message.serverPlayerData.hasKey("updateMaxSoulPoints", 3)) {
						clientPlayerData.setMaxSoulPoints(message.serverPlayerData.getInteger("updateMaxSoulPoints"));
					}
					if(message.serverPlayerData.hasKey("updateCurrentSoulPoints", 3)) {
						clientPlayerData.setCurrentSoulPoints(message.serverPlayerData.getInteger("updateCurrentSoulPoints"));
					}
					if(message.serverPlayerData.hasKey("updateMaxHealthPoints", 3)) {
						clientPlayerData.setMaxHealthPoints(message.serverPlayerData.getInteger("updateMaxHealthPoints"));
					}
				}
			});
			return null;
		}

	}

}
