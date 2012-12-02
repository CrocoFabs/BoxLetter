package fr.crocofabs.BoxLetter;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Event implements Listener {
	protected Location lieu;
	BoxLetter plugin;
	private HashMap<String,Boolean> joueurMap = new HashMap<String, Boolean>();
	protected String pseudo;
	public Event(BoxLetter plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void Commande(PlayerCommandPreprocessEvent event){
		if(event.getMessage().equalsIgnoreCase("/boxletter")){
			joueurMap.put(event.getPlayer().getName(), true);
			String[] args = event.getMessage().split(" ");
			if(args.length == 2){
				pseudo = args[1];
			}else{
				event.getPlayer().sendMessage("[Erreur] usage: /boxletter <player>");
				System.out.println("erreur");
			}
		}
	}
	
	@EventHandler
	public void ouvrirCoffre(PlayerInteractEvent event){
		System.out.println("click");
		if(/*joueurMap.get(event.getPlayer().getName())  &&*/ event.getMaterial() == Material.CHEST ){
			System.out.println("set lieu");
			lieu = event.getClickedBlock().getLocation();
			plugin.config.ajouterCoffre(event.getPlayer().getLocation(), event.getPlayer());
			joueurMap.put(event.getPlayer().getName(), false);
		}event.setCancelled(false);
	}
}
