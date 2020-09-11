package eu.yurama.buildffa.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.Source;
import eu.yurama.buildffa.assets.impl.InventorySort;

@SuppressWarnings("incomplete-switch")
public class CloseListener implements Listener {

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		if (event.getInventory().getName().equalsIgnoreCase("§6Inventarsortierung")) {
			if (getFilledSlots(event.getInventory()) == 6) {
				int sword = 0;
				int arrow = 0;
				int bow = 0;
				int rod = 0;
				int sandstone = 0;
				int cobweb = 0;

				int x = 0;
				while (x < 9) {

					if (event.getInventory().getItem(x) != null) {
						switch (event.getInventory().getItem(x).getType()) {
						case IRON_SWORD:
							sword = x;
							break;
						case ARROW:
							arrow = x;
							break;
						case BOW:
							bow = x;
							break;
						case FISHING_ROD:
							rod = x;
							break;
						case WEB:
							cobweb = x;
							break;
						case SANDSTONE:
							sandstone = x;
							break;
						}
					}

					x++;
				}

				BuildFFAPlayer.getBuildFFAPlayer(((Player) event.getPlayer()))
						.setInventory(InventorySort.parseInventoryToString(sword, rod, bow, arrow, cobweb, sandstone));
				event.getPlayer().sendMessage(Source.PREFIX + "§aInventar wurde gespeichert");
				((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.NOTE_PLING, 10, 2);
			} else {
				event.getPlayer().sendMessage(Source.PREFIX + "§cSpeichern fehlgeschlagen");
				((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.ANVIL_LAND, 10, 1);
			}

			BuildFFAPlayer.getBuildFFAPlayer(((Player) event.getPlayer())).fillInventory();
		}

	}

	public static int getFilledSlots(Inventory inventory) {
		int count = 0;
		for (ItemStack stack : inventory.getContents()) {
			if (stack != null && !stack.getType().equals(Material.AIR)) {
				count++;
			}
		}

		return count;
	}

}
