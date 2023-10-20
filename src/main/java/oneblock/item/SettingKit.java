package oneblock.item;

import oneblock.Main;
import oneblock.setting.WorldSetting;
import oneblock.utils.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SettingKit {

    public static String BORDER_OUT = Color.tran("&a&lTĂNG VÒNG BO");
    public static String BORDER_IN = Color.tran("&c&lGIẢM VÒNG BO");
    public static String PVP = Color.tran("&4&lBẬT/TẮT PVP");
    public static String PLACE = Color.tran("&b&lBẬT/TẮT ĐẶT KHỐI");

    public static String RESPAWN = Color.tran("&e&lBẬT/TẮT HỒI SINH");

    public static void give_border_out(Player p) {
        ItemStack item = new ItemStack(Material.LIME_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(BORDER_OUT);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" &fDùng để tăng bán kính");
        lore.add(" &fvòng bo của đấu trường");
        lore.add(" ");
        lore.add(" &eClick &ađể dùng");
        lore.add(" ");
        lore.replaceAll(a -> a.replace(a, Color.tran(a)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        p.getInventory().setItem(0,item);
        p.updateInventory();
    }

    public static void give_border_in(Player p) {
        ItemStack item = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(BORDER_IN);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" &fDùng để giảm bán kính");
        lore.add(" &fvòng bo của đấu trường");
        lore.add(" ");
        lore.add(" &eClick &ađể dùng");
        lore.add(" ");
        lore.replaceAll(a -> a.replace(a, Color.tran(a)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        p.getInventory().setItem(1,item);
        p.updateInventory();
    }


    public static void give_pvp(Player p) {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PVP);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" &fDùng để bật tắt PVP");
        lore.add(" &fcủa đấu trường");
        lore.add(" ");
        lore.add(" &eClick &ađể dùng");
        lore.add(" ");
        lore.replaceAll(a -> a.replace(a, Color.tran(a)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        p.getInventory().setItem(7,item);
        p.updateInventory();
    }

    public static void give_place(Player p) {
        ItemStack item = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PLACE);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" &fDùng để bật tắt đặt");
        lore.add(" &fkhối của đấu trường");
        lore.add(" ");
        lore.add(" &eClick &ađể dùng");
        lore.add(" ");
        lore.replaceAll(a -> a.replace(a, Color.tran(a)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        p.getInventory().setItem(8,item);
        p.updateInventory();
    }

    public static void give_respawn(Player p) {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(RESPAWN);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(" &fDùng để bật tắt hồi");
        lore.add(" &fsinh của đấu trường");
        lore.add(" ");
        lore.add(" &eClick &ađể dùng");
        lore.add(" ");
        lore.replaceAll(a -> a.replace(a, Color.tran(a)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        p.getInventory().setItem(6,item);
        p.updateInventory();
    }


    public static void give_setting_kit(Player p) {
        give_border_out(p);
        give_border_in(p);
        give_pvp(p);
        give_place(p);
        give_respawn(p);
    }
}
