package eu.yurama.buildffa;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.MapLoader;
import eu.yurama.buildffa.assets.impl.Map;
import eu.yurama.buildffa.builder.ScoreboardBuilder;
import eu.yurama.buildffa.commands.BuildCommand;
import eu.yurama.buildffa.commands.InvCommand;
import eu.yurama.buildffa.commands.SetspawnCommand;
import eu.yurama.buildffa.listener.CloseListener;
import eu.yurama.buildffa.listener.DamageListener;
import eu.yurama.buildffa.listener.DeathListener;
import eu.yurama.buildffa.listener.DropListener;
import eu.yurama.buildffa.listener.FoodListener;
import eu.yurama.buildffa.listener.InteractListener;
import eu.yurama.buildffa.listener.InventoryListener;
import eu.yurama.buildffa.listener.JoinListener;
import eu.yurama.buildffa.listener.PlaceListener;
import eu.yurama.buildffa.listener.QuitListener;
import eu.yurama.buildffa.listener.RespawnListener;
import eu.yurama.buildffa.mysql.MySQL;
import eu.yurama.buildffa.mysql.MySQLFile;

public class BuildFFA extends JavaPlugin {

	public static List<Player> buildMode = new ArrayList<>();
	
	private static BuildFFA instance;
	private static Map currentMap;

	public void onEnable() {
		instance = this;

		MySQLFile mysqlFile = new MySQLFile();
		mysqlFile.setStandart();
		mysqlFile.readData();

		MySQL.connect();
		MySQL.startKeepAlive();
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new DropListener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
		Bukkit.getPluginManager().registerEvents(new CloseListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlaceListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new RespawnListener(), this);
		Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);

		Bukkit.getPluginCommand("inv").setExecutor(new InvCommand());
		Bukkit.getPluginCommand("build").setExecutor(new BuildCommand());
		Bukkit.getPluginCommand("setspawn").setExecutor(new SetspawnCommand());
		
		MapLoader.getAllMaps();
		
		if(MapLoader.hasMaps()) {
			currentMap = MapLoader.getRandomMap();
		}
		
		if(MapLoader.mapchangeAvailable()) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					currentMap = MapLoader.changeMap();
				}
			}, 0, 20*60*5);
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					ArrayList<String> scoreBoard = new ArrayList<>();
					scoreBoard.add("§a");
					scoreBoard.add("§7Kills§8;");
					scoreBoard.add("§1§8➟ §e" + BuildFFAPlayer.getBuildFFAPlayer(all).getKills());
					scoreBoard.add("§b");
					scoreBoard.add("§7Tode§8;");
					scoreBoard.add("§8➟ §e" + BuildFFAPlayer.getBuildFFAPlayer(all).getDeaths());
					scoreBoard.add("§d");
					scoreBoard.add("§7TeamSpeak§8;");
					scoreBoard.add("§8➟ §etest.teamspeak.de");
					scoreBoard.add("§c");
					
					new ScoreboardBuilder("§6§lTESTSERVER", scoreBoard).showBoard(all);
				}
			}
		}, 0, 40);
		
	}

	public void onDisable() {
		MySQL.disconnect();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer("§6Reload");
		}
	}
	
	public static Map getCurrentMap() {
		return currentMap;
	}

	public static BuildFFA getInstance() {
		return instance;
	}

}
