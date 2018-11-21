package me.ipodtouch0218.oddchestc.item;

import java.util.ArrayList;
import java.util.List;

import me.ipodtouch0218.oddchestc.OddMain;
import me.ipodtouch0218.oddchestc.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OddChest {

	private static FileConfiguration config = OddMain.main;
	private Double chance;
	private String name;
	private boolean commandMode = false;
	private ItemStack stack;
	private List<Loot> lootTable = new ArrayList<Loot>();
	private List<Cmd> commandTable = new ArrayList<Cmd>();
	
	public OddChest(String name) {
		this.name = name;
		
		if (config.getString(name + "mode") != null) {
			if (config.getString(name + "mode").equalsIgnoreCase("command")) {
				commandMode = true;
			} else {
				commandMode = false;
			}
		}
		chance = config.getDouble(name + ".chance");
		String[] splitStr = config.getString(name + ".itemstack").split(",");
		ItemStack stack = new ItemStack(Material.getMaterial(splitStr[0]), 1, Short.parseShort(splitStr[2]));
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', splitStr[1]));
		stack.setItemMeta(im);
		this.stack = stack;
		
		if (commandMode) {
			for (String str: config.getStringList(name + ".commands")) {
				if (str != null) {
					String[] splitString = str.split(",");
					String cmd = splitString[0];
					Double chance = Double.parseDouble(splitString[1]);
					commandTable.add(new Cmd(cmd, chance));
				}
			}
		} else {
			for (String str: config.getStringList(name + ".lootTable")) {
				if (str != null) {
					String[] splitString = str.split(",");
					ItemStack itemToAdd = new ItemStack(Material.getMaterial(splitString[0]), 1, Short.parseShort(splitString[2]));
					ItemMeta itemmeta = itemToAdd.getItemMeta();
					if (!(splitString[1].equals("")))
						itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', splitString[1]));
					itemToAdd.setItemMeta(itemmeta);
					lootTable.add(new Loot(itemToAdd, Double.parseDouble(splitString[3])));
				}
			}
		}
	}
	
	public Double getChance() {
		return chance;
	}
	
	public List<Cmd> getCommandTable() {
		if (commandTable.isEmpty()) {
			return null;
		} else {
			return commandTable;
		}
	}
	
	public List<ItemStack> getLootTable() {
		if (lootTable.isEmpty()) { 
			return null;
		} else { 
			List<ItemStack> table = new ArrayList<ItemStack>();
			for (Loot loot : lootTable) {
				table.add(loot.getItemStack());
			}
			return table;
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
	
	public boolean commandMode() {
		return commandMode;
	}
	
	public Cmd getRandomCommand() {
		int random = (int) (Math.random() * 100);
		for (Cmd cmd : commandTable) {
			if (cmd.getChance() < random) {
				return cmd;
			}
		}
		int random2 = (int) (Math.random() * lootTable.size());
		return commandTable.get(random2);
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
