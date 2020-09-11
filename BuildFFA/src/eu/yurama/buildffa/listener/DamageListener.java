package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import eu.yurama.buildffa.BuildFFA;

public class DamageListener implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (event.getEntity().getLocation().getBlockY() >= BuildFFA.getCurrentMap().getSpawnLocation().getBlockY()
					- 2
					&& event.getEntity().getLocation()
							.getBlockY() <= BuildFFA.getCurrentMap().getSpawnLocation().getBlockY() + 2) {
				event.setCancelled(true);
			}

			if (event.getCause().equals(DamageCause.FALL)) {
				event.setCancelled(true);
			}
		}
	}

}
