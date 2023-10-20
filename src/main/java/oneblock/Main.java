package oneblock;


import com.tcoded.folialib.FoliaLib;
import oneblock.command.OneBlockCommand;
import oneblock.event.PlayerEvent;
import oneblock.papi.PAPI;
import oneblock.random.RandomBiome;
import oneblock.random.RandomMap;
import oneblock.setting.WorldSetting;
import oneblock.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static WorldSetting worldSetting;
    public static Plugin pl;


    public static WorldSetting getWorldSetting() {
        return worldSetting;
    }


    public static FoliaLib foliaLib;

    @Override
    public void onEnable() {
        pl = this;
        sendlog(" ");
        sendlog("&#FFF800&lO&#FFF500&lN&#FFF200&lE&#FFEF00&lB&#FFEC00&lL&#FFE900&lO&#FFE600&lC&#FFE300&lK &7| &fMinigame");
        sendlog("&fAuthor: &eSFC_Log");
        sendlog(" ");
        worldSetting = new WorldSetting(Bukkit.getWorld("world"));
        getServer().getPluginManager().registerEvents(new PlayerEvent(),this);
        getServer().getPluginCommand("oneblock").setExecutor(new OneBlockCommand());
        foliaLib = new FoliaLib(this);
        new RandomMap();
        new RandomBiome();
        new PAPI().register();



    }

    @Override
    public void onDisable() {
        new PAPI().unregister();
        sendlog(" ");
        sendlog("&#FFF800&lO&#FFF500&lN&#FFF200&lE&#FFEF00&lB&#FFEC00&lL&#FFE900&lO&#FFE600&lC&#FFE300&lK &7| &fMinigame");
        sendlog("&fAuthor: &eSFC-Log");
        sendlog(" ");
        pl = null;
    }

    public static void sendlog(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color.tran(msg));
    }
}
