package xyz.n7mn.dev.reachchecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

class EventListener implements Listener {
    private final Plugin plugin;

    public EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if (e.isCancelled()) {
            return;
        }

        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if (entity instanceof Player && damager instanceof Player) {
            new Thread(() -> {
                Player targetPlayer = (Player) entity;
                Player fromPlayer = (Player) damager;

                PlayerData data = ReachChecker.playerdataHashMap.get(fromPlayer.getUniqueId());

                if (fromPlayer.getGameMode() != GameMode.CREATIVE) {
                    if (fromPlayer.getLocation().getY() != targetPlayer.getLocation().getY()) {
                        double x = targetPlayer.getLocation().getX() - fromPlayer.getLocation().getX();
                        x = x * x;
                        double z = targetPlayer.getLocation().getZ() - fromPlayer.getLocation().getZ();
                        z = z * z;
                        double y = Math.abs(targetPlayer.getLocation().getY() - fromPlayer.getLocation().getY());
                        double distance = Math.sqrt(x + z) - (y / 7.5); //1.8: 2.5//1.12.2: 7.5
                        data.setLastreach(distance);
                        plugin.getLogger().info(fromPlayer.getName() + " ---> " + targetPlayer.getName() + " : " + distance + " (A)");

                        if (distance > data.getMaxreach()) {
                            data.setMaxreach(distance);
                        }
                        if (distance >= 4.0 && 12.0 >= distance) {
                            data.setVLA(data.getVLA() + 1);
                            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                if (ReachChecker.playerdataHashMap.get(player.getUniqueId()).isAlert()) {
                                    player.sendMessage("" + ChatColor.YELLOW + "[ReachChecker(A)] " + ChatColor.RESET + fromPlayer.getName() + " : " + distance + " §6§l(" + data.getVLA() + ")");
                                }
                            }
                        }
                    } else {
                        double x = targetPlayer.getLocation().getX() - fromPlayer.getLocation().getX();
                        double z = targetPlayer.getLocation().getZ() - fromPlayer.getLocation().getZ();
                        double distance = Math.sqrt(x * x + z * z);
                        data.setLastreach(distance);
                        plugin.getLogger().info(fromPlayer.getName() + " ---> " + targetPlayer.getName() + " : " + distance + " (B)");
                        if (distance > data.getMaxreach()) {
                            data.setMaxreach(distance);
                        }
                        if (distance >= 3.7 && 12.0 >= distance) { //1.8: 2.5//1.12.2: 7.5
                            if (targetPlayer.getVelocity().getX() >= 0.07 || targetPlayer.getVelocity().getZ() >= 0.07) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    if (ReachChecker.playerdataHashMap.get(player.getUniqueId()).isAlert()) {
                                        player.sendMessage("" + ChatColor.AQUA + "[ReachChecker(C-DEV)] " + ChatColor.RESET + fromPlayer.getName() + " : " + distance + " §b§l(" + data.getVLB() + ") Debug: X: "+targetPlayer.getVelocity().getX()+" Z:"+targetPlayer.getVelocity().getX());
                                    }
                                }
                            }else {
                                data.setVLB(data.getVLB() + 1);
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    if (ReachChecker.playerdataHashMap.get(player.getUniqueId()).isAlert()) {
                                        player.sendMessage("" + ChatColor.AQUA + "[ReachChecker(B)] " + ChatColor.GOLD + fromPlayer.getName() + " : " + distance + " §6§l(" + data.getVLB() + ") Debug: X: "+targetPlayer.getVelocity().getX()+" Z:"+targetPlayer.getVelocity().getX());
                                    }
                                }
                            }
                        }
                    }
                }
            }).start();
        }

    }

    @EventHandler
    public void ClickCPS(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            PlayerData data = ReachChecker.playerdataHashMap.get(e.getPlayer().getUniqueId());
            data.setCpsL(data.getCpsL() + 1);
            if (data.getCpsL() > data.getMaxcpsL()) {
                data.setMaxcpsL(data.getCpsL());
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    data.setCpsL(data.getCpsL() - 1);
                }
            }.runTaskLater(plugin,20);
        }else {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                PlayerData data = ReachChecker.playerdataHashMap.get(e.getPlayer().getUniqueId());
                data.setCpsR(data.getCpsR() + 1);
                if(data.getCpsR() > data.getMaxcpsR()){
                    data.setMaxcpsR(data.getCpsR());
                }
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        data.setCpsR(data.getCpsR() - 1);
                    }
                }.runTaskLater(plugin,20);
            }
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        if (!ReachChecker.playerdataHashMap.containsKey(e.getPlayer().getUniqueId())) {
            ReachChecker.playerdataHashMap.put(e.getPlayer().getUniqueId(), new PlayerData(e.getPlayer().getUniqueId()));
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("reachchecker.op")) {
                ReachChecker.playerdataHashMap.get(e.getPlayer().getUniqueId()).setIsalert(true);
                e.getPlayer().sendMessage("§e[ReachChecker] §rアラートが§aON§rになりました (/alerts で通知を切り替え可能です)");
            }
        }
    }
}