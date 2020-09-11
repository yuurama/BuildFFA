package eu.yurama.buildffa.builder;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	ItemStack itemStack;
	ItemMeta itemMeta;
	
	public ItemBuilder(Material material) {
		itemStack = new ItemStack(material);
		itemMeta = itemStack.getItemMeta();
	}
	
	public ItemBuilder(Material material, int subId) {
		itemStack = new ItemStack(material, 0, (short) subId);
		itemMeta = itemStack.getItemMeta();
	}
	
	public ItemBuilder setDisplayName(String displayName) {
		itemMeta.setDisplayName(displayName);
		
		return this;
	}
	
	public ItemBuilder setAmount(int amount) {
		itemStack.setAmount(amount);
		
		return this;
	}
	
	public ItemBuilder addItemFlag(ItemFlag itemFlag) {
		itemMeta.addItemFlags(itemFlag);
		
		return this;
	}
	
	public ItemBuilder setLore(String[] lore) {
		ArrayList<String> loreLines = new ArrayList<>();
		
		for(String line : lore) {
			loreLines.add(line);
		}
		
		itemMeta.setLore(loreLines);
		
		return this;
	}
	
	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		itemMeta.addEnchant(enchantment, level, true);
		
		return this;
	}
	
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

}
