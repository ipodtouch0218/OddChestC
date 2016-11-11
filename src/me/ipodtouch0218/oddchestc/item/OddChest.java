package me.ipodtouch0218.oddchestc.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OddChest extends ItemStack {

	FileConfiguration config;
	String name;
	ItemStack stack;
	List<Loot> lootTable;
	
	public OddChest(FileConfiguration cfg, String name) {
		this.config = cfg;
		this.name = name;
		
		String[] splitStr = name.split(",");
		ItemStack stack = new ItemStack(Material.getMaterial(splitStr[0]), 1, Short.parseShort(splitStr[2]));
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(splitStr[1]);
		stack.setItemMeta(im);
		this.stack = stack;
		
		for (String str: config.getStringList(name + ".lootTable")) {
			if (str != null) {
				String[] splitString = str.split(",");
				ItemStack itemToAdd = new ItemStack(Material.getMaterial(splitString[0]), 1, Short.parseShort(splitString[2]));
				ItemMeta itemmeta = itemToAdd.getItemMeta();
				im.setDisplayName(splitString[1]);
				itemToAdd.setItemMeta(itemmeta);
				lootTable.add(new Loot(itemToAdd, Double.parseDouble(splitString[3])));
			}
		}
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
	
	public boolean isOddChest() {
		for (String key : config.getKeys(false)) {
			if (name.equals(key)) {
				return true;
			}
		}
		return false;
	}
}
