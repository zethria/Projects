package com.zethria.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener{
	
private Main plugin;
	
	public Listeners(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent q) {
		Player player = q.getPlayer();
		PoisonCommand pc = PoisonCommand.pcmd();
		System.out.println(pc.poison);
		for(String quit : pc.poison) {
			System.out.println(quit);
		}
		if(SpeedCommand.speed.contains(player.getName()) || JellyLegs.Jellylegs.contains(player.getName()) 
				|| Nightvision.nightvision.contains(player.getName()) || Waterbreathing.wb.contains(player.getName()) || pc.poison.contains(player.getName())) {
			if(JellyLegs.Jellylegs.contains(player.getName())) {
				JellyLegs.Jellylegs.remove(player.getName());
			} if(SpeedCommand.speed.contains(player.getName())) {
			SpeedCommand.speed.remove(player.getName());
			player.removePotionEffect(PotionEffectType.SPEED);
			} if(Nightvision.nightvision.contains(player.getName())) {
				Nightvision.nightvision.remove(player.getName());
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			} if(Waterbreathing.wb.contains(player.getName())) {
				Waterbreathing.wb.remove(player.getName());
				player.removePotionEffect(PotionEffectType.WATER_BREATHING);
			} if (pc.poison.contains(player.getName())) {
				pc.poison.remove(player.getName());
				player.removePotionEffect(PotionEffectType.POISON);
			}
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent d) {
		if(d.getEntity() instanceof Player) {
			Player player = (Player) d.getEntity();
			if(d.getCause() == DamageCause.FALL && JellyLegs.Jellylegs.contains(player.getName())) {
				d.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onCreate(PortalCreateEvent c) {
		if(plugin.getConfig().getBoolean("Portal.enabled") == true) {
			c.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onEnter(PlayerTeleportEvent t) {
		if(plugin.getConfig().getBoolean("Portal.enabled") == true) {
			if(t.getCause() == TeleportCause.NETHER_PORTAL || t.getCause() == TeleportCause.END_PORTAL) {
				t.setCancelled(true);
			}
		}
	}
	
}
