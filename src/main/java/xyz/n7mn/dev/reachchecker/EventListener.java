package xyz.n7mn.dev.reachchecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

class EventListener implements Listener {

    private final Plugin plugin;

    public EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void d(EntityDamageByEntityEvent e) {

        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if (entity instanceof Player && damager instanceof Player) {
            new Thread(() -> {
                Player targetPlayer = (Player) entity;
                Player fromPlayer = (Player) damager;
                if (fromPlayer.getGameMode() != GameMode.CREATIVE) {
                    double x = targetPlayer.getLocation().getX() - fromPlayer.getLocation().getX();
                    x = x * x;
                    double z = targetPlayer.getLocation().getZ() - fromPlayer.getLocation().getZ();
                    z = z * z;
                    double y = Math.abs(targetPlayer.getLocation().getY() - fromPlayer.getLocation().getY());
                    double distance = Math.sqrt(x + z) - (y / 2.5); //1.12.2の場合は下げてもいいかも
                    plugin.getLogger().info(fromPlayer.getName() + " ---> " + targetPlayer.getName() + " : " + distance);
                    if (distance >= 3.5) {

                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

                            if (player.isOp() || player.hasPermission("reachchecker.op")) {

                                player.sendMessage("" +
                                        ChatColor.YELLOW + "[ReachChecker] " + ChatColor.RESET + fromPlayer.getName() + " : " + distance
                                );
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
