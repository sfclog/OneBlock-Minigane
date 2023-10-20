package oneblock.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import oneblock.topkill.TopKill;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class PAPI extends PlaceholderExpansion {


    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier != null) {
            if (identifier.contains("top")) {
                int top = Integer.valueOf(identifier.split("_")[1]);
                return TopKill.getTop(top);
            } else  if (identifier.equals("live")) {
               return String.valueOf(getLive());
            } else  if (identifier.equals("die")) {
                return String.valueOf(getDie());
            }

        }
        return "";
    }
    public int getDie() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() != GameMode.SURVIVAL).collect(Collectors.toList()).size();
    }
    public int getLive() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).collect(Collectors.toList()).size();
    }


    public String getIdentifier() {
        return "oneblock";
    }

    public String getAuthor() {
        return "SFC_Log";
    }

    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }
}