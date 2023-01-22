package de.wzrd.Commands;

import de.wzrd.Moon;
import de.wzrd.PlayerDataBase.PlayerHandler;
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
            sender.sendMessage(Moon.textWarning("You don't have the Permission to use this Command"));
            return false;
        }
        if (args.length != 2){
            sender.sendMessage(Moon.textMediumWarning("/setBalance <Player> <Amount>"));
            return false;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        long amount;
        try {
            amount = Long.parseLong(args[1]);
        }catch (NumberFormatException num){
            sender.sendMessage(Moon.textMediumWarning("/setBalance <Player> <Amount>"));
            return false;
        }
        try {
            PlayerHandler.setBalanceFrom(player.getUniqueId(),amount);
            sender.sendMessage(Moon.textInfo("Successfully set the Purse to "+amount));
        } catch (IOException e) {
            sender.sendMessage(Moon.textMediumWarning("This Player doesn't exist"));
            return false;
        }
        return true;
    }
}
