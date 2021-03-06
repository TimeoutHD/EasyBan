package de.timeout.bukkit.ban.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackAPI {

	public static ItemStack createItemStack(Material mat, int amount, String name) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getItemStack(int id, short subid, int amount, String name) {
		ItemStack item = new ItemStack(id, amount, subid);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static void enchantItem(ItemStack item, Enchantment ench, int level, boolean hideEnchantments) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(ench, level, true);
		if(hideEnchantments)meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
	}
	
	public static void setLore(ItemStack item, String lore) {
		String[] split = lore.split(" ");
		List<String> list = new ArrayList<String>();
		String line = "";
		for(int i = 0; i < split.length; i++) {
			line = line + split[i];
			if((i % 4) == 0) {
				list.add(line);
				line = "";
			}
		}
		
		ItemMeta meta = item.getItemMeta();
		meta.setLore(list);
		item.setItemMeta(meta);
	}
	
	public static void setLore(ItemStack item, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public static void removeEnchantments(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.getEnchants().keySet().forEach(ench -> removeEnchantment(item, ench));
	}
	
	public static void removeEnchantment(ItemStack item, Enchantment ench) {
		ItemMeta meta = item.getItemMeta();
		meta.removeEnchant(ench);
		item.setItemMeta(meta);
	}
}
