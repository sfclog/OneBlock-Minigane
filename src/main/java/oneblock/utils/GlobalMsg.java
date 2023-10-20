package oneblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class GlobalMsg {


    public static void send(String msg, Sound sound) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(Color.tran(msg));
            p.playSound(p.getLocation(),sound,100,1);
        });
    }
    public static void send_title(String title,String sub) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendTitle(Color.tran(title),Color.tran(sub),0,100,0);
        });
    }
}
