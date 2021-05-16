package xyz.n7mn.dev.reachchecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

class EventListener implements Listener {
    private final Plugin plugin;

    public EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {


        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if (entity instanceof Player && damager instanceof Player) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (e.isCancelled()) {
                        cancel();
                    }

                    Player targetPlayer = (Player) entity;
                    Player fromPlayer = (Player) damager;

                    PlayerData data = ReachChecker.playerdataHashMap.get(fromPlayer.getUniqueId());

                    if (fromPlayer.getGameMode() != GameMode.CREATIVE) {
                        double x = targetPlayer.getLocation().getX() - fromPlayer.getLocation().getX();
                        double z = targetPlayer.getLocation().getZ() - fromPlayer.getLocation().getZ();
                        double y = Math.abs(targetPlayer.getLocation().getY() - fromPlayer.getLocation().getY());
                        double distance = Math.sqrt(x * x + z * z) - (y / 7.5);
                        data.setLastreach(distance);
                        plugin.getLogger().info(fromPlayer.getName() + " ---> " + targetPlayer.getName() + " : " + distance + " (A)");

                        if (distance > data.getMaxreach()) {
                            data.setMaxreach(distance);
                        }
                        if (distance >= 4.2 && 12.0 >= distance) {
                            data.setVLA(data.getVLA() + 1);
                            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                if (ReachChecker.playerdataHashMap.get(player.getUniqueId()).isAlert()) {
                                    player.sendMessage("" + ChatColor.YELLOW + "[ReachChecker(A)] " + ChatColor.RESET + fromPlayer.getName() + " : " + distance + " §6§l(" + data.getVLA() + ")§r "+ "ping: " + ((CraftPlayer) fromPlayer).getHandle().ping + "\n§bdebug:" + targetPlayer.getVelocity().getY() + " | " + targetPlayer.getVelocity());
                                }
                            }
                        }
                    }
                }
            }.runTaskAsynchronously(plugin);
        }

    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        if (!ReachChecker.playerdataHashMap.containsKey(e.getPlayer().getUniqueId())) {
            ReachChecker.playerdataHashMap.put(e.getPlayer().getUniqueId(), new PlayerData());
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("reachchecker.op")) {
                ReachChecker.playerdataHashMap.get(e.getPlayer().getUniqueId()).setIsalert(true);
                e.getPlayer().sendMessage("§e[ReachChecker] §rアラートが§aON§rになりました (/alerts で通知を切り替え可能です)");
            }
        }
    }
}
