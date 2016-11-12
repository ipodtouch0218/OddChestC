package me.ipodtouch0218.oddchestc.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.ipodtouch0218.oddchestc.item.OddChest;

public class PlayerListener implements Listener {

	FileConfiguration messages;
	
	public PlayerListener(FileConfiguration messages) {
		this.messages = messages;
	}
	
	@EventHandler
	public void playerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (OddChest.isOddChest(e.getItem())) {
				e.setCancelled(true);
				OddChest chest = OddChest.fromItemStack(e.getItem());
				if (e.getPlayer().getInventory().firstEmpty() != -1) {
					e.getPlayer().getInventory().addItem(chest.getRandomLoot().getItemStack());
					if (e.getItem().getAmount() > 1) {
						e.getItem().setAmount(e.getItem().getAmount() - 1);
					} else {
						e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(), null);
					}
				}
			}
		}
	}
	
}
