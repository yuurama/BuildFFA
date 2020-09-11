package eu.yurama.buildffa.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import eu.yurama.buildffa.assets.BuildFFAPlayer;
import eu.yurama.buildffa.assets.impl.InventorySort;
import eu.yurama.buildffa.builder.ItemBuilder;

public class InvCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;
			InventorySort sort = BuildFFAPlayer.getBuildFFAPlayer(player).getInventorySort();

			Inventory inventory = Bukkit.createInventory(null, 9, "§6Inventarsortierung");

			inventory.setItem(sort.getSlotSword(), new ItemBuilder(Material.IRON_SWORD).setDisplayName("§6Schwert")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
			inventory.setItem(sort.getSlotArrow(), new ItemBuilder(Material.ARROW).setDisplayName("§6Pfeile").build());
			inventory.setItem(sort.getSlotBow(), new ItemBuilder(Material.BOW).setDisplayName("§6Bogen").build());
			inventory.setItem(sort.getSlotRod(),
					new ItemBuilder(Material.FISHING_ROD).setDisplayName("§6Angel").build());
			inventory.setItem(sort.getSlotSandstone(),
					new ItemBuilder(Material.SANDSTONE).setDisplayName("§6Sandstein").build());
			inventory.setItem(sort.getSlotCobweb(), new ItemBuilder(Material.WEB).setDisplayName("§7Cobweb").build());

			player.openInventory(inventory);
			player.getInventory().clear();
			player.playSound(player.getLocation(), Sound.CHEST_OPEN, 10, 1);
			
			int x = 0;
			while (x <= 36) {
				player.getInventory().setItem(x,
						new ItemBuilder(Material.STAINED_GLASS_PANE, 14).setDisplayName("§a").setAmount(0).build());
				x++;
			}
		}

		return false;
	}

}
