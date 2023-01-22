package de.wzrd.Commands;

import de.wzrd.PlayerDataBase.PlayerHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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
                sender.sendMessage(text("This Player doesn't exist"));
                return false;
            }
            sender.sendMessage(text(player.getName() + " currently has " + amount + " Dollars"));
            return true;
        }
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        try {
            sender.sendMessage(text("You currently have " + PlayerHandler.getBalanceFrom(player.getUniqueId()) + " Dollars"));
            return true;
        } catch (IOException e) {
            sender.sendMessage(text("This Player doesn't exist"));
            return false;
        }
    }

    public Component text(String string){
        return Component.text(string).color(TextColor.color(0, 255, 0));
    }
}
