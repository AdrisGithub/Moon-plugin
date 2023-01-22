package de.wzrd.EventHandler;

import de.wzrd.PlayerDataBase.PlayerHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoinSlashLeave implements Listener {

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent event){
        PlayerHandler.addPlayer(event.getPlayer().getUniqueId());
        event.joinMessage(Component.text("Hello "+event.getPlayer().getName()).color(TextColor.color(0,255,0)));
    }
    @EventHandler
    public void OnPlayerLeaveEvent(PlayerQuitEvent event){
        event.quitMessage(Component.text(event.getPlayer().getName()+" has other things to do").color(TextColor.color(0,255,0)));
        PlayerHandler.leavePlayer(event.getPlayer().getUniqueId());
    }
}
