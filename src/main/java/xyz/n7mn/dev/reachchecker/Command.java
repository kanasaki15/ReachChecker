package xyz.n7mn.dev.reachchecker;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ReachChecker.map.containsKey(player.getUniqueId())) {
                if (ReachChecker.map.get(player.getUniqueId()).equals("alert.true")) {
                    ReachChecker.map.put(player.getUniqueId(), "alert.false");
                    player.sendMessage("§e[ReachChecker] §rアラートを§cOFF§rにしました");
                } else {
                    ReachChecker.map.put(player.getUniqueId(), "alert.true");
                    player.sendMessage("§e[ReachChecker] §rアラートを§aON§rにしました");
                }
            } else {
                ReachChecker.map.put(player.getUniqueId(), "alert.true");
                player.sendMessage("§e[ReachChecker] §rアラートを§aON§rにしました");
            }
        }
        return true;
    }
}
