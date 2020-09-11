package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.MapLoader;
import eu.yurama.buildffa.assets.Source;

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
	}

}
