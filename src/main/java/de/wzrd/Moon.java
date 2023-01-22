package de.wzrd;

import de.wzrd.Commands.Balance;
import de.wzrd.Commands.SetBalance;
import de.wzrd.EventHandler.OnJoinSlashLeave;
import de.wzrd.PlayerDataBase.PlayerHandler;
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
    }

    public void initHandler(){
        getServer().getPluginManager().registerEvents(new OnJoinSlashLeave(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
