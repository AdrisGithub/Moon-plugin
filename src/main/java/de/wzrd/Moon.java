package de.wzrd;

import de.wzrd.Commands.Balance;
import de.wzrd.Commands.BalanceTop;
import de.wzrd.Commands.Pay;
import de.wzrd.Commands.SetBalance;
import de.wzrd.EventHandler.OnJoinSlashLeave;
import de.wzrd.PlayerDataBase.PlayerHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Moon extends JavaPlugin {


    static Logger log;
    @Override
    public void onEnable() {
        // Plugin startup logic
        log = getLogger();
        PlayerHandler.init();
        initHandler();
        initCommands();
    }

    private void initCommands() {
        Objects.requireNonNull(getCommand("balance")).setExecutor(new Balance());
        Objects.requireNonNull(getCommand("setBalance")).setExecutor(new SetBalance());
        Objects.requireNonNull(getCommand("balancetop")).setExecutor(new BalanceTop());
        Objects.requireNonNull(getCommand("pay")).setExecutor(new Pay());
    }

    public void initHandler(){
        getServer().getPluginManager().registerEvents(new OnJoinSlashLeave(),this);
    }

    public static Component textInfo(String string){
        return text(" "+string,0,255,0);
    }
    public static Component textMediumWarning(String string){
        return text(" "+string,255,255,0);
    }
    public static Component textWarning(String string){
        return text(" "+string,255,0,0);
    }

    public static Component text(String string,int r,int g,int b){
        return Component.text("[ MOON-Plugin ]").color(TextColor.color(93, 162, 255)).append(Component.text(string).color(TextColor.color(r, g, b)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
