package cc.moecraft.hykilpikonna.ult.fix.features.listeners;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AbtnConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AbtnListener extends Listener
{
    public AbtnListener()
    {
        super(Fix.getInstance());
    }

    @EventHandler (ignoreCancelled = true)
    public void onMoveEvent(PlayerMoveEvent event)
    {
        if (Features.getABTN().getConfig().getBoolean("TeleportDown.Enable"))
        {
            Player player = event.getPlayer();
            if (!(Features.getABTN().getConfig().getStringList("EnabledWorlds").contains(player.getLocation().getWorld().getName()))) return;
            if (!(player.getLocation().getY() >= Features.getABTN().getConfig().getInt("Height"))) return;
            if (Features.getABTN().getPermissionsConfig().hasPermission(player, "hyult.abtn.bypass", false)) return;

            Location tpLocation = player.getLocation();
            tpLocation.setY(Features.getABTN().getConfig().getInt("TeleportedY"));

            Block block = tpLocation.getBlock();
            if (Features.getABTN().getConfig().getBoolean("TeleportDown.PlaceBlockOnPlayersFeet"))
                block.getRelative(BlockFace.DOWN, 1).setType(Material.getMaterial(Features.getABTN().getConfig().getString("TeleportDown.PlaceMaterial")));
            if (Features.getABTN().getConfig().getBoolean("TeleportDown.RemoveBlockOnPlayersHead"))
            {
                ArrayList<String> materialList = (ArrayList<String>) Features.getABTN().getConfig().getStringList("TeleportDown.RemoveBlockList");
                if (materialList.contains(block.getType().name())) block.setType(Material.AIR);
                if (materialList.contains(block.getRelative(BlockFace.UP, 1).getType().name())) block.getRelative(BlockFace.UP, 1).setType(Material.AIR);
            }

            player.teleport(tpLocation);
            if (Features.getABTN().getConfig().getBoolean("SendMessage")) Features.getABTN().getMessenger().sendMessage(player, "teleported");
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onBlockPlaceEvent(BlockPlaceEvent event)
    {
        if (Features.getABTN().getConfig().getBoolean("AntiBuild"))
        {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            if (!(Features.getABTN().getConfig().getStringList("EnabledWorlds").contains(player.getLocation().getWorld().getName()))) return;
            if (!(block.getY() > Features.getABTN().getConfig().getInt("Height"))) return;
            if (Features.getABTN().getPermissionsConfig().hasPermission(player, "hyult.abtn.bypass", false))return;

            event.setCancelled(true);
            if (Features.getABTN().getConfig().getBoolean("SendMessage")) Features.getABTN().getMessenger().sendMessage(player, "build_event_cancelled");
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onBlockDestroyEvent(BlockBreakEvent event)
    {
        if (Features.getABTN().getConfig().getBoolean("AntiDestroy"))
        {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            if (!(Features.getABTN().getConfig().getStringList("EnabledWorlds").contains(player.getLocation().getWorld().getName()))) return;
            if (!(block.getY() > Features.getABTN().getConfig().getInt("Height"))) return;
            if (Features.getABTN().getPermissionsConfig().hasPermission(player, "hyult.abtn.bypass", false)) return;

            event.setCancelled(true);
            if (Features.getABTN().getConfig().getBoolean("SendMessage")) Features.getABTN().getMessenger().sendMessage(player, "destroy_event_cancelled");
        }
    }
}
