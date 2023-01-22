package de.wzrd.Commands;

import de.wzrd.Moon;
import de.wzrd.PlayerDataBase.PlayerHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BalanceTop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File allData = new File("plugins/Moon-Configs/");
        StringBuilder build = new StringBuilder();
        build.append("--------TOP BALANCE--------\n");
        Map<TempPlayer,Long> unsortedPlayers = new HashMap<>();
        for (String s:Objects.requireNonNull(allData.list())) {
            try {
                Long currency = PlayerHandler.getBalanceFrom(UUID.fromString(s));
                unsortedPlayers.put(new TempPlayer(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), currency),currency);
            }catch (IOException e) {
                sender.sendMessage(Moon.textWarning("Error occurred"));
                return false;
            }
        }
        Object[] sorted = unsortedPlayers.values().stream().sorted().toArray();
        for (int i = sorted.length - 1; i >= 0; i--) {
            for (TempPlayer player : unsortedPlayers.keySet()) {
                if (player.getCurrency().equals(sorted[i])&& !player.isUsed()) {
                    build.append(sorted.length-i).append(": ").append(player);
                }
            }
        }
        build.append("-------");
        sender.sendMessage(Component.text(build.toString()).color(TextColor.color(0,255,0)).append(Moon.textInfo("-------")));
        return true;
    }
    protected class TempPlayer {
        private final String name;
        private final Long currency;
        private boolean used;
        public TempPlayer(String name,Long currency){
            this.name = name;
            this.currency = currency;
            this.used = false;
        }


        public String getName() {
            return name;
        }

        public Long getCurrency() {
            return currency;
        }

        @Override
        public String toString() {
            used = true;
            return (name+" "+currency+"$\n");
        }

        public boolean isUsed() {
            return used;
        }
    }
}
