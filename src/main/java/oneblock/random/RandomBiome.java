package oneblock.random;

import oneblock.utils.Random;
import org.bukkit.block.Biome;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomBiome {

    public static List<Biome> list;

    public RandomBiome() {
        list = new ArrayList<>();
        for(Biome biome : Biome.values()) {
            list.add(biome);
        }
    }
    public static Biome getRandom() {
        return list.get(Random.getRandomNumber(0,list.size() -1));
    }

}
