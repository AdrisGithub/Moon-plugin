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

public class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(args.length != 2) {
            sender.sendMessage(Moon.textMediumWarning("/setBalance <Player> <Amount>"));
            return false;
        }
        Player playerSender = ((Player) sender).getPlayer();
        OfflinePlayer playerReceiver = Bukkit.getOfflinePlayer(args[0]);
        //if(playerReceiver == null) return false;
        long amount;
        try {
            amount = Long.parseLong(args[1]);
        }catch (NumberFormatException num){
            sender.sendMessage(Moon.textMediumWarning("/setBalance <Player> <Amount>"));
            return false;
        }
        if(amount<= 0){
            sender.sendMessage(amount == 0
                    ? Moon.textMediumWarning("You can't pay zero Dollars")
                    : Moon.textMediumWarning("You can't pay negative Dollars"));
            return false;
        }
        if (!PlayerHandler.hasEnoughMoney(playerSender.getUniqueId(), amount)) {
            sender.sendMessage(Moon.textMediumWarning("You don't have enough money to do that"));
            return false;
        }
        try {
            PlayerHandler.addBalanceFrom(playerReceiver.getUniqueId(),amount);
            PlayerHandler.subBalanceFrom(playerSender.getUniqueId(),amount);
        } catch (IOException e) {
            sender.sendMessage(Moon.textMediumWarning("This Player doesn't exist"));
            return false;
        }
        sender.sendMessage(Moon.textInfo("Successfully transferred "+amount+" to the account of "+playerReceiver.getName()));
        return true;
    }
}
