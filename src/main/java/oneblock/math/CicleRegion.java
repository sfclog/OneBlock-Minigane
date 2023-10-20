package oneblock.math;


import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CicleRegion {


    public int radius;
    public double centerX,centerZ;
    public CicleRegion(Location center, int radius) {
        this.radius = radius;
        this.centerX = center.getX();
        this.centerZ = center.getZ();
    }

    public void setRadius(int size) {
        this.radius = size;
    }
    public boolean isInsideCircle(Location location) {
        double x = location.getX();
        double z = location.getZ();
        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(z - centerZ, 2));
        return distance <= radius;
    }

    public boolean isInsideCircle(Player p) {
        double x = p.getLocation().getX();
        double z = p.getLocation().getZ();
        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(z - centerZ, 2));
        return distance <= radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getCenterZ() {
        return centerZ;
    }

    public double getCenterX() {
        return centerX;
    }


}
