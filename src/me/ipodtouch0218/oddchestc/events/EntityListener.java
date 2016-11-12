package me.ipodtouch0218.oddchestc.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.ipodtouch0218.oddchestc.item.OddChest;
import me.ipodtouch0218.oddchestc.util.Util;

public class EntityListener implements Listener {

	FileConfiguration config;
	
	public EntityListener(FileConfiguration fg) {
		config = fg;
	}
	
	@EventHandler
	public void entityDeathEvent(EntityDeathEvent e) {
		Bukkit.broadcastMessage("Broadcasted");
		if (e.getEntity().getKiller() != null && e.getEntity() instanceof LivingEntity) {
			Bukkit.broadcastMessage("PlayerKilled");
			if (config.getBoolean("Entities." + e.getEntityType().toString())) {
				Bukkit.broadcastMessage("Type: " + e.getEntityType().toString());
				OddChest chest = Util.getRandomOddChest();
				Double chance = chest.getChance();
				Double random = (Math.random()*100)+1;
				if (chance <= random) {
					List<ItemStack> drops = e.getDrops();
					drops.add(chest.toItemStack());
				}
			}
		}
	}
}
