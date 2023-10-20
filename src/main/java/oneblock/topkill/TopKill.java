package oneblock.topkill;

import oneblock.Main;
import oneblock.utils.Color;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TopKill {

    public static HashMap<String,Integer> map = new HashMap<String, Integer>();


    public static void reset() {
        map.clear();;
    }
    public static String getTop(int i) {
        if(!(i <= 0)) {
            int select = i - 1;
            List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() > 0)
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .collect(Collectors.toList());
            if (!sortedEntries.isEmpty()) {
                Map.Entry<String, Integer> entry = sortedEntries.get(select);
                return Color.tran("&f" + entry.getKey() + " &b" + entry.getValue());
            }
        }
        return "";
    }

    public static void add_kill(Player p) {
        if(map.containsKey(p.getName())) {
            int v = map.get(p.getName()) + 1;
            map.replace(p.getName(),v);
        } else {
            map.put(p.getName(),1);
        }
    }
    public static void add(Player p) {
        if(!map.containsKey(p.getName())) {
            map.put(p.getName(),0);
        }
    }
    public static void remove(Player p) {
        if(map.containsKey(p.getName())) {
            map.remove(p.getName());
        }
    }
}


