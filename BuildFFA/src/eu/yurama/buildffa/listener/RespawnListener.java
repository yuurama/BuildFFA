package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.BuildFFAPlayer;

public class RespawnListener implements Listener {
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		Player player = (Player) event.getPlayer();
		
		event.setRespawnLocation(BuildFFA.getCurrentMap().getSpawnLocation());
		
		BuildFFAPlayer.getBuildFFAPlayer(player).fillInventory();
	}

}
