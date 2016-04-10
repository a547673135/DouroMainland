package douromainland;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import douromainland.network.MyMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerData implements IExtendedEntityProperties, Serializable {

	private EntityPlayer player;

	public static final String identifier = References.MODID + "PlayerData";

	private static Map<UUID, EntityPlayer> UUIDmap = new HashMap<UUID, EntityPlayer>();

	private int currentSoulPoints;

	private int maxSoulPoints;

	private int maxHealthPoints;

	public PlayerData(EntityPlayer player) {
		this.player = player;
		this.currentSoulPoints = 0;
		this.maxSoulPoints = 0;
		this.maxHealthPoints = 20;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public static PlayerData get(EntityPlayer player) {
		return (PlayerData) player.getExtendedProperties(identifier);
	}

	public static void register(EntityPlayer player) {
		player.registerExtendedProperties(identifier, new PlayerData(player));
	}

	public boolean isServerSide() {
		return this.player instanceof EntityPlayerMP;
	}

	public int getCurrentSoulPoints() {
		return currentSoulPoints;
	}

	public void setCurrentSoulPoints(int currentSoulPoints) {
		this.currentSoulPoints = currentSoulPoints;
	}

	public int getMaxSoulPoints() {
		return maxSoulPoints;
	}

	public void setMaxSoulPoints(int maxSoulPoints) {
		this.maxSoulPoints = maxSoulPoints;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
		sync("updateMaxHealthPoints");
	}

	public void increaseCurrentSoulPoints(int amount) {
		if (currentSoulPoints + amount >= maxSoulPoints) {
			currentSoulPoints = maxSoulPoints;
		} else {
			currentSoulPoints += amount;
		}
		sync("updateCurrentSoulPoints");
	}

	public boolean decreaseCurrentSoulPoints(int amount) {
		if (currentSoulPoints - amount < 0) {
			return false;
		} else {
			currentSoulPoints -= amount;
			sync("updateCurrentSoulPoints");
			return true;
		}
	}

	public void increaseMaxSoulPoints(int amount) {
		maxSoulPoints += amount;
		sync("updateMaxSoulPoints");
		setMaxHealthPoints(20 + getMaxSoulPoints()/10);
	}

	public void sync(String effect) {
		if (this.player instanceof EntityPlayerMP) {
			NBTTagCompound data = new NBTTagCompound();
			if(effect.equals("updateMaxSoulPoints")){
				data.setInteger(effect, maxSoulPoints);
			}
			if(effect.equals("updateCurrentSoulPoints")){
				data.setInteger(effect, currentSoulPoints);
			}
			if(effect.equals("updateMaxHealthPoints")){
				data.setInteger(effect, maxHealthPoints);
			}
			References.network.sendTo(new MyMessage(data), (EntityPlayerMP) this.player);
		}
	}
	
	public void syncAll() {
		sync("updateMaxSoulPoints");
		sync("updateCurrentSoulPoints");
		sync("updateMaxHealthPoints");
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setInteger("currentSoulPoints", this.currentSoulPoints);
		compound.setInteger("maxSoulPoints", this.maxSoulPoints);
		compound.setInteger("maxHealthPoints", this.maxHealthPoints);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		if (compound.hasKey("currentSoulPoints", 3)) {
			this.setCurrentSoulPoints(compound.getInteger("currentSoulPoints"));
		}
		if (compound.hasKey("maxSoulPoints", 3)) {
			this.setMaxSoulPoints(compound.getInteger("maxSoulPoints"));
		}
		if (compound.hasKey("maxHealthPoints", 3)) {
			this.setMaxHealthPoints(compound.getInteger("maxHealthPoints"));
		}
	}

	@Override
	public void init(Entity entity, World world) {
		
	}

}
