package cc.moecraft.hykilpikonna.ult.fix.features.calculations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpCalculations
{
    public static Location getEssentialsSpawn()
    {
        //赋值默认坐标
        Location defaultSpawn = new Location(Bukkit.getWorld("World") != null ? Bukkit.getWorld("World") : Bukkit.getWorlds().get(0), 0, 0, 0);

        //判定ESS是否存在并设定默认坐标为ESS的坐标
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null)
        {
            YamlConfiguration essConfig = YamlConfiguration.loadConfiguration(new File(Bukkit.getPluginManager().getPlugin("Essentials").getDataFolder() + "/spawn.yml"));
            if (essConfig.contains("spawns.default.world"))
            {
                if (Bukkit.getWorld(essConfig.getString("spawns.default.world")) != null)
                {
                    defaultSpawn.setWorld(Bukkit.getWorld(essConfig.getString("spawns.default.world")));
                    defaultSpawn.setX(essConfig.getDouble("spawns.default.x"));
                    defaultSpawn.setY(essConfig.getDouble("spawns.default.y"));
                    defaultSpawn.setZ(essConfig.getDouble("spawns.default.z"));
                    defaultSpawn.setYaw((float) essConfig.getDouble("spawns.default.yaw"));
                    defaultSpawn.setPitch((float) essConfig.getDouble("spawns.default.pitch"));
                }
            }
        }

        return defaultSpawn;
    }
}
