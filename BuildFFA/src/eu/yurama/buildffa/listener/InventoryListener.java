package eu.yurama.buildffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if(!event.getClickedInventory().getName().equalsIgnoreCase("§6Inventarsortierung")) {
			((Player)event.getWhoClicked()).updateInventory();
			event.setCancelled(true);
			((Player)event.getWhoClicked()).updateInventory();
		} else {
			if(event.getSlot() >= 9) {
				((Player)event.getWhoClicked()).updateInventory();
				event.setCancelled(true);
				((Player)event.getWhoClicked()).updateInventory();
			}
		}
	}

}
