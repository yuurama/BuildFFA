package eu.yurama.buildffa.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.yurama.buildffa.BuildFFA;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if(!BuildFFA.buildMode.contains(event.getPlayer())) {
			if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			event.setCancelled(true);
		}
	}

}
