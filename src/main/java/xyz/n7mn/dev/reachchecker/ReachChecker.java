package xyz.n7mn.dev.reachchecker;

import org.bukkit.plugin.java.JavaPlugin;

public final class ReachChecker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new EventListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
