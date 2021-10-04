package xyz.n7mn.dev.reachchecker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.n7mn.dev.reachchecker.checks.type.Reach;
import xyz.n7mn.dev.reachchecker.command.AlertsCommand;

import java.util.HashMap;
import java.util.UUID;


public final class ReachChecker extends JavaPlugin {
    private static final HashMap<UUID, PlayerData> playerdataHashMap = new HashMap<>();
    private static Plugin plugin;


    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new Reach(), this);
        getCommand("alerts").setExecutor(new AlertsCommand());

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            createPlayerData(player, player.isOp());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void createPlayerData(Player player, boolean alert) {
        if (getPlayerData(player) == null) playerdataHashMap.put(player.getUniqueId(), new PlayerData(alert));
    }

    public static void removePlayerData(Player player) {
        if (getPlayerData(player) != null) playerdataHashMap.remove(player.getUniqueId());
    }

    public static PlayerData getPlayerData(Player player) {
        return playerdataHashMap.get(player.getUniqueId());
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}