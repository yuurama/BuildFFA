package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.Source;

public class DeathListener implements Listener {

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();

		event.setKeepInventory(true);
		
		if (player.getKiller() != null) {
			Player killer = event.getEntity().getKiller();

			BuildFFAPlayer.getBuildFFAPlayer(player).addDeath();

			
			event.setDeathMessage(
					Source.PREFIX + "§e" + player.getName() + " §7wurde von §e" + killer.getName() + " §7getötet");
		} else {
			event.setDeathMessage(Source.PREFIX + "§e" + player.getName() + " §7ist gestorben");
		}
		
		player.spigot().respawn();
		BuildFFAPlayer.getBuildFFAPlayer(player).addDeath();
	}

}
