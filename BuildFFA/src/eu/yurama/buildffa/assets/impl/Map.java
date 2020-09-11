package eu.yurama.buildffa.assets.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Map {
	
	private String name;
	private Location spawn;
	
	public Map(String name, String spawn) {
		this.name = name;
		this.spawn = getLocation(spawn);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Location getSpawnLocation() {
		return this.spawn;
	}
	
	public static String getLocation(Location location) {
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		String world = location.getWorld().getName();
		
		return x + "," + y + "," + z + "," + yaw + "," + pitch + "," + world;
	}
	
	public static Location getLocation(String location) {
		String[] loc = location.split(",");
		double x = Double.parseDouble(loc[0]);
		double y = Double.parseDouble(loc[1]);
		double z = Double.parseDouble(loc[2]);
		float yaw = Float.parseFloat(loc[3]);
		float pitch = Float.parseFloat(loc[4]);
		String world = loc[5];
		
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}

}
