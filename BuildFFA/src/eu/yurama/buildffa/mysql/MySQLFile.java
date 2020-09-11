package eu.yurama.buildffa.mysql;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLFile {

	public void setStandart() {
		FileConfiguration cfg = getFileConfiguration();

		cfg.options().copyDefaults(true);

		cfg.addDefault("host", "127.0.0.1");
		cfg.addDefault("port", "3306");
		cfg.addDefault("database", "minecraft");
		cfg.addDefault("username", "minecraft");
		cfg.addDefault("password", "<password>");

		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§cConfig mit Daten konnte nicht erstellt werden!");
		}
	}

	private File getFile() {
		return new File("plugins/BuildFFA", "mysql.yml");

	}

	private FileConfiguration getFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getFile());
	}

	public void readData() {
		FileConfiguration cfg = getFileConfiguration();
		MySQL.host = cfg.getString("host");
		MySQL.port = cfg.getString("port");
		MySQL.database = cfg.getString("database");
		MySQL.username = cfg.getString("username");
		MySQL.password = cfg.getString("password");
	}

}
