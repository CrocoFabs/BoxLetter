package fr.crocofabs.BoxLetter;

import org.bukkit.plugin.java.JavaPlugin;

import fr.crocofabs.BoxLetter.Event;

public class BoxLetter extends JavaPlugin {
	protected Configuration config;
	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(new Event(this), this);
		config = new Configuration(this);
		config.charger();		
		System.out.println("[BoxLetter] BoxLetter est actif!");
	}
	@Override
	public void onDisable(){
		System.out.println("[BoxLetter] BoxLetter est inactif!");	
	}
}
