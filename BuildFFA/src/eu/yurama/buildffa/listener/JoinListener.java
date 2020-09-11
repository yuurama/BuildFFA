package eu.yurama.buildffa.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.MapLoader;
import eu.yurama.buildffa.assets.Source;
import eu.yurama.buildffa.builder.ScoreboardBuilder;

public class JoinListener implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		BuildFFAPlayer.getBuildFFAPlayer(player).collectStats();
		BuildFFAPlayer.getBuildFFAPlayer(player).fillInventory();
		
		player.setHealth(20);
		player.setFoodLevel(20);
		
		if(MapLoader.hasMaps())
			player.teleport(BuildFFA.getCurrentMap().getSpawnLocation());
		
		event.setJoinMessage(Source.PREFIX + "§e" + player.getName() + " §7hat das Spiel betreten");
		
		ArrayList<String> scoreBoard = new ArrayList<>();
		scoreBoard.add("§a");
		scoreBoard.add("§7Kills§8;");
		scoreBoard.add("§1§8➟ §e" + BuildFFAPlayer.getBuildFFAPlayer(player).getKills());
		scoreBoard.add("§b");
		scoreBoard.add("§7Tode§8;");
		scoreBoard.add("§8➟ §e" + BuildFFAPlayer.getBuildFFAPlayer(player).getDeaths());
		scoreBoard.add("§d");
		scoreBoard.add("§7TeamSpeak§8;");
		scoreBoard.add("§8➟ §etest.teamspeak.de");
		scoreBoard.add("§c");
		
		new ScoreboardBuilder("§6§lTESTSERVER", scoreBoard).showBoard(player);
	}

}
