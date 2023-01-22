package de.wzrd.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class BalanceTop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File allData = new File("plugins/Moon-Configs/");
        for (String s: Objects.requireNonNull(allData.list())) {
            sender.sendMessage(s);
        }
        return true;
    }
}
