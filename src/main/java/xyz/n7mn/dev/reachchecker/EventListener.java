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
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if (entity instanceof Player && damager instanceof Player) {
            new Thread(() -> {
                Player targetPlayer = (Player) entity;
                Player fromPlayer = (Player) damager;
                double distance = targetPlayer.getLocation().distance(fromPlayer.getLocation());

                plugin.getLogger().info(fromPlayer.getName() + " ---> " + targetPlayer.getName() + " : " + distance);
                if (distance >= 4){

                    for (Player player : Bukkit.getServer().getOnlinePlayers()){

                        if (player.isOp() || player.hasPermission("reachchecker.op")){

                            player.sendMessage("" +
                                    ChatColor.YELLOW + "[ReachChecker] "+ ChatColor.RESET + fromPlayer.getName()+" : " + distance
                            );
                        }
                    }
                }
            }).start();
        }
    }
}