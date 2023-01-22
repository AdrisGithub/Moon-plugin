package de.wzrd.Commands;

import de.wzrd.PlayerDataBase.PlayerHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SetBalance implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(Component.text("You don#t have the Permission to use this Command").color(TextColor.color(255,0,0)));
            return false;
        }
        if (args.length != 2){
            sender.sendMessage(Component.text("/setBalance <Player> <Amount>").color(TextColor.color(255,0,0)));
            return false;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        long amount;
        try {
            amount = Long.parseLong(args[1]);
        }catch (NumberFormatException num){
            sender.sendMessage(Component.text("/setBalance <Player> <Amount>").color(TextColor.color(255,0,0)));
            return false;
        }
        try {
            PlayerHandler.setBalanceFrom(player.getUniqueId(),amount);
            sender.sendMessage(text("Successfully set the Purse to "+amount));
        } catch (IOException e) {
            sender.sendMessage(text("This Player doesn't exist"));
            return false;
        }
        return true;
    }
    public Component text(String string){
        return Component.text(string).color(TextColor.color(0, 255, 0));
    }
}
