package xyz.n7mn.dev.reachchecker;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

class EventListener implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        ReachChecker.createPlayerData(e.getPlayer(), e.getPlayer().isOp());

        if(e.getPlayer().isOp()) {
            e.getPlayer().sendMessage(ChatColor.YELLOW + "[!] あなたのアラートはtrueです (切りたい場合は: /reachchecker:alerts)");
        }
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e) {
        ReachChecker.removePlayerData(e.getPlayer());
    }
}