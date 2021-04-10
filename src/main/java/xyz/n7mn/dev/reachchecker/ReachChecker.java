package xyz.n7mn.dev.reachchecker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class ReachChecker extends JavaPlugin {

    public static HashMap<UUID,String> map = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getCommand("alerts").setExecutor(new AlertsCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
