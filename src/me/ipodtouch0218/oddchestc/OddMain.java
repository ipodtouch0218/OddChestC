package me.ipodtouch0218.oddchestc;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import me.ipodtouch0218.oddchestc.events.EntityListener;
import me.ipodtouch0218.oddchestc.events.PlayerListener;

public class OddMain extends JavaPlugin {
	
	public static FileConfiguration main;
	
	File messageFile = new File(getDataFolder() + "/messages.yml");
	FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);
	
	File entityFile = new File(getDataFolder() + "/entities.yml");
	FileConfiguration entityConfig = YamlConfiguration.loadConfiguration(entityFile);
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		saveDefaultConfigs();
		main = getConfig();
		getCommand("oddchest").setExecutor(new CommandOddchest(messageConfig));
		getServer().getPluginManager().registerEvents(new EntityListener(entityConfig), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(messageConfig), this);
	}
	
	@Override
	public void onDisable() {
		saveDefaultConfigs();
	}
	
	public void saveDefaultConfigs() {
	    if (!entityFile.exists()) {
	    	saveResource("entities.yml", false);
	    }
	    if (!messageFile.exists()) {
	    	saveResource("messages.yml", false);
	    }
	}
}
