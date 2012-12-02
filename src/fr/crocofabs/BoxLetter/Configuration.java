package fr.crocofabs.BoxLetter;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Configuration{	
	BoxLetter plugin;
	HashMap<Vector, String> mapConfig = new HashMap<Vector, String>();
	FileConfiguration config;
	public Configuration(BoxLetter plugin){
		this.plugin = plugin;
		config = plugin.getConfig();
	}
	public void charger(){
		if(config.get("Configuration") == null){
			config.createSection("coffres", mapConfig);
			plugin.saveConfig();
		}
	}
	public void ajouterCoffre(Location lieu, Player joueur){
		mapConfig.put(lieu.toVector(), joueur.getName());
		config.createSection("coffres", mapConfig);
		plugin.saveConfig();
	}
}
