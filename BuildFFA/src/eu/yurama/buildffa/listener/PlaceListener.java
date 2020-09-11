package eu.yurama.buildffa.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import eu.yurama.buildffa.BuildFFA;

public class PlaceListener implements Listener {

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		if (!BuildFFA.buildMode.contains(event.getPlayer())) {
			if (event.getBlock().getLocation().getBlockY() >= BuildFFA.getCurrentMap().getSpawnLocation().getBlockY()
					- 2
					&& event.getBlock().getLocation()
							.getBlockY() <= BuildFFA.getCurrentMap().getSpawnLocation().getBlockY() + 2) {
				event.setCancelled(true);
			} else {
				if (event.getBlock().getType().equals(Material.WEB)) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
						@Override
						public void run() {
							event.getBlock().setType(Material.AIR);
							event.getBlock().getWorld().playEffect(event.getBlock().getLocation(),
									Effect.MOBSPAWNER_FLAMES, 2);
						}
					}, 20 * 3);
				}
				if (event.getBlock().getType().equals(Material.SANDSTONE)) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
						@Override
						public void run() {
							event.getBlock().setType(Material.REDSTONE_BLOCK);
							event.getBlock().getWorld().playEffect(event.getBlock().getLocation(),
									Effect.MOBSPAWNER_FLAMES, 2);
						}
					}, 20 * 3);
					Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
						@Override
						public void run() {
							event.getBlock().setType(Material.AIR);
							event.getBlock().getWorld().playEffect(event.getBlock().getLocation(),
									Effect.MOBSPAWNER_FLAMES, 2);
						}
					}, 20 * 5);
				}
			}
		}
	}

}
