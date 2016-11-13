package me.ipodtouch0218.oddchestc.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Loot extends ItemStack {

	private ItemStack lootStack;
	private String lootStackName;
	private double chance;
	
	public Loot(ItemStack stack, double chance) {
		this.lootStack = stack;
		this.lootStackName = lootStack.getItemMeta().getDisplayName();
		ItemMeta lootMeta = lootStack.getItemMeta();
		lootMeta.setDisplayName(lootStackName);
		lootStack.setItemMeta(lootMeta);
		this.chance = chance;
	}
	
	public ItemStack getItemStack() {
		return lootStack;
	}
	
	public double getChance() {
		return chance;
	}
}
