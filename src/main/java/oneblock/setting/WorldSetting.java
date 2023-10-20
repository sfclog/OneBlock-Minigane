package oneblock.setting;

import oneblock.Main;
import oneblock.math.CicleBuild;
import oneblock.math.CicleRegion;
import oneblock.topkill.TopKill;
import oneblock.utils.GlobalMsg;
import org.bukkit.*;
import org.bukkit.block.Block;

public class WorldSetting {

    public World world;
    public boolean place,pvp,respawn;

    public int border_size;

    public CicleRegion region;

    public int BASIC_RANGE = 100;


    public WorldSetting(World w) {
       this.world = w;
       this.place = false;
       this.pvp = false;
       this.respawn = true;
       this.border_size = BASIC_RANGE;
       this.region = new CicleRegion(getbuild_center(),border_size);
       this.world.setAutoSave(false);


        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.setGameMode(GameMode.SURVIVAL);
            pl.teleportAsync(getplayerrespawn());
        });

    }


    public Location getbuild_center() {
        return world.getSpawnLocation();
    }
    public Location getplayerrespawn() {
        Location loc = world.getSpawnLocation();
        Block b = loc.getWorld().getHighestBlockAt(loc.getBlockX(),loc.getBlockZ());
        return b.getLocation().clone().add(0.5,2,0.5);
    }

    public void out() {
        int size = border_size + 1;
            CicleBuild.build(getbuild_center(),border_size, Material.AIR);
            this.border_size = size;
            this.region.setRadius(size);
            CicleBuild.build(getbuild_center(),border_size, Material.BEDROCK);


    }
    public void in() {
        int size = border_size - 1;
        if(!(size < 0)) {
            CicleBuild.clear_block(world.getSpawnLocation(),this.border_size);
            this.border_size = size;
            this.region.setRadius(size);
            CicleBuild.build(world.getSpawnLocation(),border_size, Material.BEDROCK);
        }
    }

    public void toggle_respawn() {
        boolean value = respawn == true ? false : respawn == false ? true : false;

        if(value) {
            GlobalMsg.send("&aHồi Sinh &fĐã bật cho phép hồi sinh khi chết!", Sound.ENTITY_VILLAGER_DEATH);
            GlobalMsg.send_title("&a&lĐÃ BẬT HỒI SINH","&fBạn có thể hồi sinh khi chết!");
        } else {
            GlobalMsg.send("&cHồi Sinh &fĐã tắt cho phép hòi sinh khi chết!", Sound.ENTITY_VILLAGER_DEATH);
            GlobalMsg.send_title("&c&lĐÃ TẮT HỒI SINH","&fBạn không thể hồi sinh khi chết!");
        }
        this.respawn = value;
    }
    public void toggle_place() {
        boolean value = place == true ? false : place == false ? true : false;

        if(value) {
            GlobalMsg.send("&aBảo Vệ &fĐã bật cho phép đặt khối trong đấu trường, bạn có thể đặt khối!", Sound.BLOCK_STONE_BREAK);
            GlobalMsg.send_title("&a&lBẬT ĐẶT KHỐI","&fBạn có để đặt khối");
        } else {
            GlobalMsg.send("&cBảo Vệ &fĐã tắt cho phép đặt khối trong đấu trường, bạn không thể đặt khối!", Sound.BLOCK_STONE_BREAK);
            GlobalMsg.send_title("&c&lTẮT ĐẶT KHỐI","&fBạn không thể đặt khối");
        }
        this.place = value;
    }
    public void toggle_pvp() {
        boolean value = pvp == true ? false : pvp == false ? true : false;

        if(value) {
            GlobalMsg.send("&cCảnh Báo &fPVP đã được bật trên đấu trường này, bạn có thể đánh người khác!", Sound.ENTITY_ENDER_DRAGON_GROWL);
            GlobalMsg.send_title("&c&lPVP ĐÃ BẬT","&fPVP đã được bật");
        } else {
            GlobalMsg.send("&aAn Toàn &fPVP đã được tắt trên đầu trường này, người khác không thể đánh bạn!", Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
            GlobalMsg.send_title("&a&lPVP ĐÃ TẮT","&fPVP đã được tắt");
        }
        this.pvp = value;
    }

    public CicleRegion getRegion() {
        return region;
    }

    public void reset() {

        //clear map
        CicleBuild.clear_all(getbuild_center(),BASIC_RANGE);

        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.setGameMode(GameMode.SURVIVAL);
            pl.getInventory().clear();
            pl.teleportAsync(getplayerrespawn());
        });
        this.border_size = BASIC_RANGE;
        this.region.setRadius(BASIC_RANGE);
        this.place = false;
        this.pvp = false;
        this.respawn = true;
        TopKill.reset();

        CicleBuild.build(getbuild_center(),border_size, Material.BEDROCK);

    }


}
