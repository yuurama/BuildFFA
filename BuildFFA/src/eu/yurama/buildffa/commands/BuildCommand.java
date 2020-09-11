package eu.yurama.buildffa.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.yurama.buildffa.BuildFFA;
import eu.yurama.buildffa.assets.Source;

public class BuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (player.hasPermission("buildffa.build")) {
				if(BuildFFA.buildMode.contains(player)) {
					BuildFFA.buildMode.remove(player);
					
					player.getInventory().clear();
					player.setGameMode(GameMode.SURVIVAL);
					player.teleport(BuildFFA.getCurrentMap().getSpawnLocation());
					player.sendMessage(Source.PREFIX + "Du hast den Baumodus §cverlassen");
				} else {
					BuildFFA.buildMode.add(player);
					
					player.getInventory().clear();
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(Source.PREFIX + "Du hast den Baumodus §abetreten");
				}
			} else {
				player.sendMessage(Source.PREFIX + "§cDu hast keine Berechtigung hierfür");
			}

		} else {
			sender.sendMessage(Source.PREFIX + "§cDu bist kein Spieler");
		}

		return false;
	}

}
