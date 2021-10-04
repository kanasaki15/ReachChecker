package xyz.n7mn.dev.reachchecker.checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.n7mn.dev.reachchecker.ReachChecker;

import java.util.Collection;

public class Check {

    protected void alert(Player player, String type, String reason) {
        Collection<? extends Player> online = Bukkit.getOnlinePlayers();

        if (online.size() == 0) return;

        for (Player alert : Bukkit.getOnlinePlayers()) {
            if (ReachChecker.getPlayerData(alert).isAlert()) {
                Bukkit.getScheduler().runTask(ReachChecker.getPlugin(), () -> {
                    alert.sendMessage(ChatColor.YELLOW + player.getName() + "は (" + ChatColor.RED + type + ChatColor.YELLOW + ") を失敗しました" + "\n理由: " + reason);
                });
            }
        }
    }
}