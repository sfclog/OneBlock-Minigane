package oneblock.math;


import oneblock.random.RandomBiome;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;


public class CicleBuild {



    public static void clear_block(Location center, int radius) {
        center.getBlock().setType(Material.BEDROCK);
        World world = center.getWorld();
        int centerX = center.getBlockX();
        int centerZ = center.getBlockZ();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                    int blockX = centerX + x;
                    int blockZ = centerZ + z;
                    Biome biome = RandomBiome.getRandom();
                    for(int y = -1 ; y < 60; y ++) {
                        Location blockLocation = new Location(world, blockX, y, blockZ);
                        Block block = blockLocation.getBlock();

                        if (x * x + z * z >= (radius - 1) * (radius - 1)) {
                            if(block.getType() != null ) {
                                if(block.getType() != Material.AIR &&  block.getType() != Material.BEDROCK) {
                                    FallingBlock fall = block.getWorld().spawnFallingBlock(block.getLocation().clone().add(0.5, 0, 0.5), block.getBlockData());
                                    fall.setGravity(true);
                                    fall.setDropItem(false);
                                }
                            }
                            block.setType(Material.AIR);
                            block.setBiome(biome);

                        }
                    }
                }
            }
        }
    }

    public static void build(Location center, int radius, Material type) {
            center.getBlock().setType(Material.BEDROCK);
            World world = center.getWorld();
            int centerX = center.getBlockX();
            int centerZ = center.getBlockZ();
            int centerY = center.getBlockY();

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z <= radius * radius) {
                        int blockX = centerX + x;
                        int blockZ = centerZ + z;
                        Biome biome = RandomBiome.getRandom();
                        Location blockLocation = new Location(world, blockX, centerY, blockZ);
                        Block block = blockLocation.getBlock();
                        if (x * x + z * z >= (radius - 1) * (radius - 1)) {
                            block.setType(type);
                            block.setBiome(biome);
                        }
                    }
                }
            }
        }

    public static void clear_all(Location center, int radius) {
        World world = center.getWorld();
        int centerX = center.getBlockX();
        int centerZ = center.getBlockZ();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                    int blockX = centerX + x;
                    int blockZ = centerZ + z;
                    Biome biome = RandomBiome.getRandom();
                    for(int y = -1 ; y < 60; y ++) {
                        Location blockLocation = new Location(world, blockX, y, blockZ);
                        Block block = blockLocation.getBlock();
                        block.setType(Material.AIR);
                        block.setBiome(biome);
                    }
                }
            }
        }
        center.getBlock().setType(Material.BEDROCK);
    }

}
