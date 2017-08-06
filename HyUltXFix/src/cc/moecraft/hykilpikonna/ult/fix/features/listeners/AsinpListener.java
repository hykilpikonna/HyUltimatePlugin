package cc.moecraft.hykilpikonna.ult.fix.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import cc.moecraft.hykilpikonna.ult.fix.features.calculations.AsinpCalculations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

import static org.bukkit.Material.PORTAL;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpListener extends Listener
{
    public static ArrayList<BlockFace> blockFaces = new ArrayList<>();

    public AsinpListener()
    {
        super(Fix.getInstance());
    }

    @EventHandler
    public void onEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if (Features.getASINP().getPermissionsConfig().hasPermission(player, "hyult.asinp.tp", false))
        {
            //一个开关, 是True的话就是有地狱门
            boolean aSwitch = false;
            for (BlockFace one : blockFaces)
            {
                if (player.getLocation().getBlock().getRelative(one).getType().equals(PORTAL))
                {
                    aSwitch = true;
                    break;
                }
            }

            if (aSwitch)
            {
                teleportPlayer(player);
                Features.getASINP().getMessenger().sendMessage(player, "teleport");
            }
        }
    }

    @EventHandler
    public void onPortalEvent(PlayerPortalEvent event)
    {
        if (Features.getASINP().getConfig().getBoolean("UseTimer"))
        {
            Player player = event.getPlayer();
            if (event.getPlayer().getLocation().getBlock().getType().equals(Material.PORTAL))
            {
                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        if (player.isOnline())
                        {
                            if (player.getLocation().getBlock().getType().equals(Material.PORTAL))
                            {
                                teleportPlayer(player);
                                Features.getASINP().getMessenger().sendMessage(player, "teleport");
                            }
                        }
                    }
                }.runTaskLater(Fix.getInstance(), Features.getASINP().getConfig().getInt("TimerTimeOutInSeconds") * 20);
            }
        }
    }

    private void teleportPlayer(Player player)
    {
        if (Features.getASINP().getConfig().getBoolean("TpToASpecificPointInsteadOfOffset"))
        {
            player.teleport(getNewLocation(player.getLocation()));
        }
        else
        {
            player.teleport(getNewOffsetLocation(player.getLocation()));
        }
    }

    private Location getNewLocation(Location playersl)
    {
        if (Features.getASINP().getConfig().getBoolean("SpecificPoint.UseEssentialsSpawn"))
        {
            return AsinpCalculations.getEssentialsSpawn();
        }
        else
        {
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangeWorld")) playersl.setWorld(Bukkit.getWorld(Features.getASINP().getConfig().getString("SpecificPoint.World")));
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangeX")) playersl.setX(Features.getASINP().getConfig().getDouble("SpecificPoint.X"));
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangeY")) playersl.setY(Features.getASINP().getConfig().getDouble("SpecificPoint.Y"));
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangeZ")) playersl.setZ(Features.getASINP().getConfig().getDouble("SpecificPoint.Z"));
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangeYaw")) playersl.setYaw((float) Features.getASINP().getConfig().getDouble("SpecificPoint.Yaw"));
            if (Features.getASINP().getConfig().getBoolean("SpecificPoint.ChangePitch")) playersl.setPitch((float) Features.getASINP().getConfig().getDouble("SpecificPoint.Pitch"));
            return playersl;
        }
    }

    /**
     * 获取偏移后玩家的坐标
     * @param playersl 玩家
     * @return 偏移后的坐标
     */
    private Location getNewOffsetLocation(Location playersl)
    {
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangeWorld")) playersl.setWorld(Bukkit.getWorld(Features.getASINP().getConfig().getString("Offset.World")));
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangeX")) playersl.setX(playersl.getX() + Features.getASINP().getConfig().getDouble("Offset.X"));
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangeY")) playersl.setY(playersl.getY() + Features.getASINP().getConfig().getDouble("Offset.Y"));
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangeZ")) playersl.setZ(playersl.getZ() + Features.getASINP().getConfig().getDouble("Offset.Z"));
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangeYaw")) playersl.setYaw(playersl.getYaw() + (float) Features.getASINP().getConfig().getDouble("Offset.Yaw"));
        if (Features.getASINP().getConfig().getBoolean("Offset.ChangePitch")) playersl.setPitch(playersl.getPitch() + (float) Features.getASINP().getConfig().getDouble("Offset.Pitch"));
        return playersl;
    }
}
