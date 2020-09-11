package eu.yurama.buildffa.assets;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.impl.Map;
import eu.yurama.buildffa.mysql.MySQL;

@SuppressWarnings("deprecation")
public class MapLoader {

	private static List<Map> maps = new ArrayList<>();
	
	public static void getAllMaps() {
		try {
			ResultSet rs = MySQL.getResult("SELECT * FROM `buildffa_maps`");
			while (rs.next()) {
				maps.add(new Map(rs.getString("MAPNAME"), rs.getString("LOCATION")));
			}
		} catch (Exception e) {
		}
	}

	public static boolean mapchangeAvailable() {
		return maps.size() >= 2;
	}

	public static boolean hasMaps() {
		return maps.size() > 0;
	}

	public static Map getRandomMap() {
		if (hasMaps()) {
			return maps.get(new Random().nextInt(maps.size()));
		} else {
			return null;
		}
	}
	
	public static Map changeMap() {
		Map tmp = getRandomMap();
		
		while(!tmp.getName().equals(BuildFFA.getCurrentMap().getName())) {
			tmp = getRandomMap();
		}
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.teleport(tmp.getSpawnLocation());
			all.playSound(all.getLocation(), Sound.LEVEL_UP, 20, 1);
			all.sendTitle("§8« §6§lMAPCHANGE §8»", "§e" + tmp.getName());
		}
		
		return tmp;
	}

}
