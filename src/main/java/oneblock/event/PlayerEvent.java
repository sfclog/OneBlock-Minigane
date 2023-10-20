package oneblock.event;


import oneblock.Main;
import oneblock.item.SettingKit;
import oneblock.math.CicleRegion;
import oneblock.random.RandomMap;
import oneblock.setting.WorldSetting;
import oneblock.topkill.TopKill;
import oneblock.utils.Color;
import oneblock.utils.GlobalMsg;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class PlayerEvent implements Listener {


    public static List<Material> BLACKLIST_BLOCK;

    static {
        BLACKLIST_BLOCK = new ArrayList<>();
        BLACKLIST_BLOCK.add(Material.CRAFTING_TABLE);
        BLACKLIST_BLOCK.add(Material.FURNACE);
        BLACKLIST_BLOCK.add(Material.ENCHANTING_TABLE);
    }


    @EventHandler
    public void onDead(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        p.spigot().respawn();
        WorldSetting setting = Main.getWorldSetting();
        Main.foliaLib.getImpl().runLater(()-> {
            if(setting.respawn) {
                p.teleportAsync(setting.getplayerrespawn());
            } else {
                if(p.getGameMode() != GameMode.SPECTATOR) {
                    p.setGameMode(GameMode.SPECTATOR);
                    GlobalMsg.send("&cNgười chơi " + p.getName() + " đã bị loại.", Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
                    p.teleportAsync(setting.getplayerrespawn());
                    p.sendTitle(Color.tran("&c&lBẠN ĐÃ CHẾT"),Color.tran("&fBạn không thể hồi sinh"),0,100,0);
                } else {
                    p.teleportAsync(setting.getplayerrespawn());
                }
            }
        },1,TimeUnit.SECONDS);
    }



    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        TopKill.add(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        TopKill.remove(e.getPlayer());
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getEntity().getKiller() instanceof Player) {
                Player killer = event.getEntity().getKiller();
               if(killer != null) {
                   TopKill.add_kill(killer);
               }
            }
        }
    }

    @EventHandler
    public void onBlockFall(EntityChangeBlockEvent event) {
         if ((event.getEntityType() == EntityType.FALLING_BLOCK)) {
             event.setCancelled(true);
        }
    }



    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        WorldSetting setting = Main.getWorldSetting();
        if(e.getCause() == null) {
            return;
        }
       if(e.getEntity() instanceof Player) {
           Player p = (Player) e.getEntity();

           if(e.getCause() == EntityDamageEvent.DamageCause.VOID) {
               if(setting.respawn) {
                   p.teleportAsync(setting.getplayerrespawn());
               } else {
                   if(p.getGameMode() != GameMode.SPECTATOR) {
                       p.setGameMode(GameMode.SPECTATOR);
                       GlobalMsg.send("&cNgười chơi " + p.getName() + " đã bị loại.", Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
                       p.teleportAsync(setting.getplayerrespawn());
                       p.sendTitle(Color.tran("&c&lBẠN ĐÃ CHẾT"),Color.tran("&fBạn không thể hồi sinh"),0,100,0);
                   } else {
                       p.teleportAsync(setting.getplayerrespawn());
                   }
               }
           }
           if(!setting.pvp) {
               e.setCancelled(true);
           }
       }
    }

    @EventHandler
    public void chickenLayBombs(EntityDropItemEvent event) {
            if (event.getEntityType() == EntityType.CHICKEN
                    && event.getItemDrop().getItemStack().getType() == Material.EGG) {
                java.util.Random random = new java.util.Random();
                random.setSeed(random.nextInt(999999));

                if (random.nextInt(10) >= 9) {

                    event.getItemDrop().getItemStack().setType(Material.AIR);
                    Location location = event.getEntity().getLocation();
                    World world = location.getWorld();
                    world.spawnEntity(location, EntityType.PRIMED_TNT);
                }

        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b  = e.getBlock();


        if(BLACKLIST_BLOCK.contains(b.getType())) {
            return;
        }

        if(b.getType().name().contains("CHEST")
                || b.getType().name().contains("SHULKER")
                || b.getType().name().contains("BED")
                || b.getType().name().contains("MINECAR")) {
            return;
        }
        e.setDropItems(false);
        if(p.getInventory() != null) {
            ItemStack item = p.getInventory().getItemInMainHand();

            if(!(e.getBlock().getDrops(item).size() <= 0)) {
                if(!RandomMap.getRandom(b.getLocation())) {
                    for(ItemStack i : e.getBlock().getDrops(item)) {
                        b.getWorld().dropItemNaturally(b.getLocation(),i);
                    }
                }
            }
        }

    }

    @EventHandler
    public void onDrop(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player)) {
                e.getDrops().clear();
                RandomMap.getRandom(e.getEntity().getLocation());

        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
            Location location = event.getEntity().getLocation();
            Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder()
                    .withColor(org.bukkit.Color.RED)
                    .withColor(org.bukkit.Color.ORANGE)
                    .withColor(org.bukkit.Color.YELLOW)
                    .withColor(org.bukkit.Color.GREEN)
                    .withColor(org.bukkit.Color.BLUE)
                    .withColor(org.bukkit.Color.PURPLE)
                    .withTrail()
                    .withFlicker()
                    .build());
            meta.setPower(0);
            firework.setFireworkMeta(meta);

    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        Location playerLocation = player.getLocation();
        double playerX = playerLocation.getX();
        double playerZ = playerLocation.getZ();

        CicleRegion region = Main.getWorldSetting().getRegion();
        double centerX = region.getCenterX();
        double centerZ = region.getCenterZ();
        double radius = region.getRadius();

        if (!region.isInsideCircle(player)) {

            double dx = playerX - centerX;
            double dz = playerZ - centerZ;
            double angle = Math.atan2(dz, dx);

            // Calculate the new position at the edge of the circle (one block away)
            double newX = centerX + (radius - 1) * Math.cos(angle);
            double newZ = centerZ + (radius - 1) * Math.sin(angle);

            // Set the player's location to the new position
            Location newPosition = new Location(player.getWorld(), newX, playerLocation.getY(), newZ, playerLocation.getYaw(), playerLocation.getPitch());
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,10,1);
            player.sendMessage(Color.tran("&cVui lòng không ra khỏi ranh giới"));
            player.teleportAsync(newPosition.clone().add(0.5,0.0,0.5));
            player.damage(1,null);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        WorldSetting setting = Main.getWorldSetting();
        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();

        if(b != null && b.getLocation() != null) {
            if(b.getLocation().getY() > 50 || b.getLocation().getY() < 0) {
                e.setCancelled(true);
            }
        }
        if(!Main.getWorldSetting().getRegion().isInsideCircle(b.getLocation())) {
            e.setCancelled(true);
        }
        if(p.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        if(!setting.place) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onIn(PlayerInteractEvent e) {
      Player p = e.getPlayer();
      ItemStack item = e.getItem();
       WorldSetting setting = Main.getWorldSetting();
      if(e.getAction() != null) {
          if(p != null && p.isOp() && item != null) {
              ItemMeta meta = item.getItemMeta();
              if(meta != null) {
                  if(meta.getDisplayName().equals(SettingKit.PVP)) {
                      setting.toggle_pvp();
                      e.setCancelled(true);
                  } else  if(meta.getDisplayName().equals(SettingKit.PLACE)) {
                      setting.toggle_place();
                      e.setCancelled(true);
                  } else  if(meta.getDisplayName().equals(SettingKit.BORDER_IN)) {
                      setting.in();
                      e.setCancelled(true);
                  } else  if(meta.getDisplayName().equals(SettingKit.BORDER_OUT)) {
                      setting.out();
                      e.setCancelled(true);
                  } else  if(meta.getDisplayName().equals(SettingKit.RESPAWN)) {
                      setting.toggle_respawn();
                      e.setCancelled(true);
                  }
              }
          }
      }
   }
}
