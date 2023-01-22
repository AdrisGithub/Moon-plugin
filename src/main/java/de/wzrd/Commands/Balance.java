package de.wzrd.Commands;

import de.wzrd.Moon;
import de.wzrd.PlayerDataBase.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Balance implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 0) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            Long amount;
            try {
                amount = PlayerHandler.getBalanceFrom(player.getUniqueId());
            } catch (IOException e) {
                sender.sendMessage(Moon.textWarning("This Player doesn't exist"));
                return false;
            }
            sender.sendMessage(Moon.textInfo(player.getName() + " currently has " + amount + " Dollars"));
            return true;
        }
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        try {
            sender.sendMessage(Moon.textInfo("You currently have " + PlayerHandler.getBalanceFrom(player.getUniqueId()) + " Dollars"));
            return true;
        } catch (IOException e) {
            sender.sendMessage(Moon.textInfo("This Player doesn't exist"));
            return false;
        }
    }

}
