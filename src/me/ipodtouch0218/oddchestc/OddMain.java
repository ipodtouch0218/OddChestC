package me.ipodtouch0218.oddchestc;

import org.bukkit.plugin.java.JavaPlugin;
import me.ipodtouch0218.oddchestc.events.EntityDeath;


public class OddMain extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("oddchest").setExecutor(new OddChestCMD(this));
		getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
	}
	

}
