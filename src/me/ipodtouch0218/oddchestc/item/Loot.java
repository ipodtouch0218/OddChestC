package me.ipodtouch0218.oddchestc.item;

import org.bukkit.inventory.ItemStack;

public class Loot extends ItemStack {

	ItemStack lootStack;
	double chance;
	
	public Loot(ItemStack stack, double chance) {
		this.lootStack = stack;
		this.chance = chance;
	}
	
	public ItemStack getItemStack() {
		return lootStack;
	}
	
	public double getChance() {
		return chance;
	}
}
