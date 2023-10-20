package oneblock.command;

import oneblock.Main;
import oneblock.item.SettingKit;
import oneblock.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OneBlockCommand implements CommandExecutor , TabCompleter {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(!p.isOp()) {
                send(commandSender," ");
                send(commandSender,"&#FFF800&lO&#FFF500&lN&#FFF200&lE&#FFEF00&lB&#FFEC00&lL&#FFE900&lO&#FFE600&lC&#FFE300&lK &7| &fMinigame");
                send(commandSender,"&fAuthor: &eSFC_Log");
                send(commandSender," ");
                send(commandSender," &cBạn không có quyền");
                send(commandSender," ");
            } else {

                if(strings.length < 1) {
                    send(commandSender," ");
                    send(commandSender,"&#FFF800&lO&#FFF500&lN&#FFF200&lE&#FFEF00&lB&#FFEC00&lL&#FFE900&lO&#FFE600&lC&#FFE300&lK &7| &fMinigame");
                    send(commandSender,"&fAuthor: &eSFC_Log");
                    send(commandSender," ");
                    send(commandSender," &f/oneblock kit &aĐể lấy bộ kit cho Admin");
                    send(commandSender," &f/oneblock reset &aĐể reset đấu trường");
                    send(commandSender," ");
                } else if(strings[0].equalsIgnoreCase("kit")) {
                    SettingKit.give_setting_kit(p);
                    send(commandSender,"&aĐã nhận bộ kit điều khiển trận đấu");

                } else if(strings[0].equalsIgnoreCase("reset")) {
                    Main.getWorldSetting().reset();
                    send(commandSender,"&aĐã đặt lại đấu trường");
                }

            }

        }
        return false;
    }

    private void send(CommandSender commandSender, String space) {
        commandSender.sendMessage(Color.tran(space));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        list.add("kit");
        list.add("reset");
        return list;
    }
}
