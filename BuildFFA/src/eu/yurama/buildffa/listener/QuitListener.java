package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.Source;

public class QuitListener implements Listener {

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		BuildFFAPlayer.getBuildFFAPlayer(player).overwriteStats();
		
		event.setQuitMessage(Source.PREFIX + "§e" + player.getName() + " §7hat das Spiel verlassen");
	}

}
