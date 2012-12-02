package fr.crocofabs.BoxLetter;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Event implements Listener {
	protected Location lieu;
	BoxLetter plugin;
	private HashMap<String,String> joueurMap = new HashMap<String, String>();
	protected String pseudo;
	public Event(BoxLetter plugin){
		this.plugin = plugin;
	}
	
	/*
	 * Verifie si la commande /boxletter à un argument,
	 * si elle a un agument il est pris pour proprietaire du coffre
	 * sinon c'est le joueur qui fait la commande qui est proprietaire
	 * /boxletter stop arrete la commande
	 */
	@EventHandler
	public void Commande(PlayerCommandPreprocessEvent event){
		String[] args = event.getMessage().split(" ");
		if(event.getMessage().split(" ")[0].equalsIgnoreCase("/boxletter")){
			if(args.length == 2){
				joueurMap.put(event.getPlayer().getName(), args[1]);
				event.setCancelled(true);
			}else{
				pseudo = event.getPlayer().getName();
				event.setCancelled(true);
			}
		}else if(event.getMessage().split(" ")[0].equalsIgnoreCase("/boxletter")){
			if(args.length == 2 && args[1].equalsIgnoreCase("stop")){
				joueurMap.put(event.getPlayer().getName(), "-1");
				event.setCancelled(true);
			}
		}
	}
	
	
	/*
	 * Si la commande /boxletter est active,
	 * Au clique sur un coffre la position de
	 * celui-ci est ajouté au fichier de config,
	 * La commande est desactive apres le clique.
	 */
	@EventHandler
	public void ouvrirCoffre(BlockDamageEvent event){
		if(joueurMap.containsKey(event.getPlayer().getName())){
			if(!joueurMap.get(event.getPlayer().getName()).equals("-1")  && event.getBlock().getType() == Material.CHEST ){
				plugin.config.ajouterCoffre(event.getPlayer().getLocation(), joueurMap.get(event.getPlayer().getName()));
				joueurMap.put(event.getPlayer().getName(), "-1");
			}
			event.setCancelled(true);
		}
	}
	
	
}
