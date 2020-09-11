package eu.yurama.buildffa.assets;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import eu.yurama.buildffa.assets.impl.InventorySort;
import eu.yurama.buildffa.builder.ItemBuilder;
import eu.yurama.buildffa.mysql.MySQL;

public class BuildFFAPlayer {

	private static Map<Player, BuildFFAPlayer> list = new HashMap<>();

	private Map<String, Object> stats;
	private Player player;

	public BuildFFAPlayer(Player player) {
		this.stats = new HashMap<String, Object>();
		this.player = player;
	}

	public void collectStats() {
		if (!MySQL.isValueInDatabase("buildffa_stats", "UUID", this.player.getUniqueId().toString())) {
			MySQL.update("INSERT INTO buildffa_stats (UUID, KILLS, DEATHS, INVENTORY) VALUES ('"
					+ this.player.getUniqueId().toString() + "','0','0','" + Source.DEFAULT_INVENTORY + "')");
		}

		stats.put("kills", MySQL.getInteger("KILLS", "buildffa_stats", "UUID", this.player.getUniqueId().toString()));
		stats.put("inv", MySQL.getString("INVENTORY", "buildffa_stats", "UUID", this.player.getUniqueId().toString()));
		stats.put("deaths", MySQL.getInteger("DEATHS", "buildffa_stats", "UUID", this.player.getUniqueId().toString()));
	}

	public void overwriteStats() {
		MySQL.update("UPDATE buildffa_stats SET KILLS='" + getKills() + "',DEATHS='" + getDeaths() + "',INVENTORY='"
				+ getInventorySort().toString() + "' WHERE UUID='" + this.player.toString() + "'");
	}

	public void addKill() {
		int data = getKills() + 1;
		stats.remove("kills");
		stats.put("kills", data);
	}

	public void addDeath() {
		int data = getKills() + 1;
		stats.remove("deaths");
		stats.put("deaths", data);
	}

	public void setInventory(String inventory) {
		stats.remove("inv");
		stats.put("inv", inventory);
	}

	public InventorySort getInventorySort() {
		return new InventorySort((String) stats.get("inv"));
	}

	public int getKills() {
		return (int) stats.get("kills");
	}

	public int getDeaths() {
		return (int) stats.get("deaths");
	}

	public Player getPlayer() {
		return this.player;
	}

	public void fillInventory() {
		this.player.getInventory().clear();

		this.player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
		this.player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
		this.player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
		this.player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
		
		this.player.getInventory().setItem(getInventorySort().getSlotSword(), new ItemBuilder(Material.IRON_SWORD)
				.addEnchantment(Enchantment.DAMAGE_ALL, 1).setDisplayName("§eSchwert").build());

		this.player.getInventory().setItem(getInventorySort().getSlotRod(),
				new ItemBuilder(Material.FISHING_ROD).setDisplayName("§eAngel").build());

		this.player.getInventory().setItem(getInventorySort().getSlotBow(),
				new ItemBuilder(Material.BOW).setDisplayName("§eBogen").build());

		this.player.getInventory().setItem(getInventorySort().getSlotCobweb(),
				new ItemBuilder(Material.WEB).setDisplayName("§eCobweb").setAmount(16).build());

		this.player.getInventory().setItem(getInventorySort().getSlotSandstone(),
				new ItemBuilder(Material.SANDSTONE).setDisplayName("§eSandstein").setAmount(64).build());

		this.player.getInventory().setItem(getInventorySort().getSlotArrow(),
				new ItemBuilder(Material.ARROW).setDisplayName("§ePfeile").setAmount(16).build());
	}

	public static BuildFFAPlayer getBuildFFAPlayer(Player player) {
		if (!list.containsKey(player)) {
			list.put(player, new BuildFFAPlayer(player));
		}
		return list.get(player);
	}

}
