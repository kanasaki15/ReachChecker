package xyz.n7mn.dev.reachchecker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public final class ReachChecker extends JavaPlugin {
    public static HashMap<UUID,PlayerData> playerdataHashMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("alerts").setExecutor(new AlertsCommand());
        getCommand("look-up").setExecutor(new LookUpCommand());
        getCommand("cps").setExecutor(new GetCPSCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
