package eu.yurama.buildffa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.yurama.buildffa.assets.Source;
import eu.yurama.buildffa.assets.impl.Map;
import eu.yurama.buildffa.mysql.MySQL;

public class SetspawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (player.hasPermission("buildffa.setspawn")) {
				if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
					if (MySQL.isValueInDatabase("buildffa_maps", "MAPNAME", args[0])) {
						MySQL.update("DELETE FROM buildffa_maps WHERE MAPNAME='" + args[0] + "'");
						player.sendMessage(Source.PREFIX + "Du hast die Map §a" + args[0] + " §7erfolgreich gelöscht");
					} else {
						player.sendMessage(Source.PREFIX + "§cDie Map " + args[0] + " existiert nicht");
					}
				} else if (args.length == 1) {
					if (!MySQL.isValueInDatabase("buildffa_maps", "MAPNAME", args[0])) {
						MySQL.update("INSERT INTO buildffa_maps (MAPNAME, LOCATION) VALUES ('" + args[0] + "','"
								+ Map.getLocation(player.getLocation()) + "')");
						player.sendMessage(
								Source.PREFIX + "Du hast die Map §a" + args[0] + " §7erfolgreich registriert");
					} else {
						player.sendMessage(Source.PREFIX + "§cDu hast die Map " + args[0] + " bereits registriert");
					}
				} else {
					player.sendMessage(Source.PREFIX + "§cBitte nutze /setspawn <Mapname>");
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
