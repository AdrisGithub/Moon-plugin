package de.wzrd.EventHandler;

import de.wzrd.Moon;
import de.wzrd.PlayerDataBase.PlayerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoinSlashLeave implements Listener {

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent event){
        PlayerHandler.addPlayer(event.getPlayer().getUniqueId());
        event.joinMessage(Moon.textInfo("Hello "+event.getPlayer().getName()));
    }
    @EventHandler
    public void OnPlayerLeaveEvent(PlayerQuitEvent event){
        event.quitMessage(Moon.textInfo(event.getPlayer()+" has other Things to do"));
        PlayerHandler.leavePlayer(event.getPlayer().getUniqueId());
    }
}
