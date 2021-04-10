package xyz.n7mn.dev.reachchecker;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ReachChecker.map.containsKey(player.getUniqueId())) {
                String alerts = ReachChecker.map.get(player.getUniqueId());

                switch (alerts) {
                    case "alert.true":
                        ReachChecker.map.put(player.getUniqueId(), "alert.false");
                        player.sendMessage("§e[ReachChecker] §rアラートを§cOFF§rにしました");
                        break;
                    case "alert.false":
                        ReachChecker.map.put(player.getUniqueId(), "alert.true");
                        player.sendMessage("§e[ReachChecker] §rアラートを§aON§rにしました");
                        break;
                    default:
                        player.sendMessage("§c[ReachChecker] エラー...");
                }
            }else {
                ReachChecker.map.put(player.getUniqueId(), "alert.true");
                player.sendMessage("§e[ReachChecker] §rアラートを§aON§rにしました");
            }
        }
        return true;
    }
}