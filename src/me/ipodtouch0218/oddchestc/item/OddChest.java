package me.ipodtouch0218.oddchestc.item;

import java.util.ArrayList;
import java.util.List;

import me.ipodtouch0218.oddchestc.OddMain;
import me.ipodtouch0218.oddchestc.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OddChest {

	static FileConfiguration config = OddMain.main;
	Double chance;
	String name;
	ItemStack stack;
	List<Loot> lootTable = new ArrayList<Loot>();
	
	public OddChest(String name) {
		this.name = name;
		
		chance = config.getDouble(name + ".chance");
		Bukkit.broadcastMessage(chance + "");
		String[] splitStr = config.getString(name + ".itemstack").split(",");
		ItemStack stack = new ItemStack(Material.getMaterial(splitStr[0]), 1, Short.parseShort(splitStr[2]));
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(splitStr[1]);
		stack.setItemMeta(im);
		this.stack = stack;
		Bukkit.broadcastMessage(stack.toString());
		
		for (String str: config.getStringList(name + ".lootTable")) {
			Bukkit.broadcastMessage(str);
			if (str != null) {
				String[] splitString = str.split(",");
				ItemStack itemToAdd = new ItemStack(Material.getMaterial(splitString[0]), 1, Short.parseShort(splitString[2]));
				ItemMeta itemmeta = itemToAdd.getItemMeta();
				if (!(splitString[1].equals("null")))
					im.setDisplayName(ChatColor.translateAlternateColorCodes('&', splitString[1]));
				itemToAdd.setItemMeta(itemmeta);
				lootTable.add(new Loot(itemToAdd, Double.parseDouble(splitString[3])));
			}
		}
	}
	
	public Double getChance() {
		return chance;
	}
	
	public List<ItemStack> getLootTable() {
		List<ItemStack> lootTable = new ArrayList<ItemStack>();
		for (String str: config.getStringList(name + ".lootTable")) {
			if (str != null) {
				String[] splitStr = str.split(",");
				ItemStack itemToAdd = new ItemStack(Material.getMaterial(splitStr[0]), 1, Short.parseShort(splitStr[2]));
				ItemMeta im = itemToAdd.getItemMeta();
				im.setDisplayName(splitStr[1]);
				itemToAdd.setItemMeta(im);
				lootTable.add(itemToAdd);
			}
		}
		if (lootTable.isEmpty()) { 
			return null;
		} else { 
			return lootTable; 
		}
	}
	
	public Loot getRandomLoot() {
		int random = (int) (Math.random() * 100);
		for (Loot loot : lootTable) {
			if (loot.getChance() < random) {
				return loot;
			}
		}
		int random2 = (int) (Math.random() * lootTable.size());
		return lootTable.get(random2);
	}
	
	public ItemStack toItemStack() {
		List<String> lore = new ArrayList<String>();
		lore.add(Util.hideString(name));
		lore.add(ChatColor.GOLD + "Right Click " + ChatColor.WHITE + "to open!");
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(stack.getItemMeta().getDisplayName());
		meta.setLore(lore);
		stack.setItemMeta(meta);
		return stack;
	}
	
	public static OddChest fromItemStack(ItemStack itemStack) {
		if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
			List<String> lore = itemStack.getItemMeta().getLore();
			String name = lore.get(0).replaceAll(ChatColor.COLOR_CHAR + "", "");
			return new OddChest(name);
		}
		return null;
	}
	
	public static boolean isOddChest(ItemStack itemStack) {
		try {
			if (!(itemStack.hasItemMeta())) return false;
			if (!(itemStack.getItemMeta().hasLore())) return false;
			List<String> lore = itemStack.getItemMeta().getLore();
			if (lore != null) {
				String stringName = lore.get(0).replaceAll(ChatColor.COLOR_CHAR + "", "");
				for (String key : config.getKeys(false)) {
					if (stringName.equals(key)) {
						return true;
					}
				}
			}
		} catch (NullPointerException e) {}
		return false; 
	}
}
