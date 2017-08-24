package cc.moecraft.hykilpikonna.ult.fun.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.ElevatorCalculations;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;

import static org.bukkit.block.BlockFace.DOWN;
import static org.bukkit.block.BlockFace.UP;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ElevatorListener extends Listener
{
    public ElevatorListener()
    {
        super(Fun.getInstance());
    }

    @EventHandler
    public void onSneakEvent(PlayerToggleSneakEvent event)
    {
        Player player = event.getPlayer();
        if (Features.getElevator().getPermissionsConfig().hasPermission(player, "hyult.elevator.use", true) && !player.isSneaking())
        {
            Block block = player.getLocation().getBlock().getRelative(DOWN);
            if (block == null) return;
            int blockY = block.getY();

            int index = ElevatorCalculations.getMaterialList().indexOf(block.getType());
            if (index == -1) return;

            if (blockY > ElevatorCalculations.getMaxYList().get(index) || blockY < ElevatorCalculations.getMinYList().get(index)) return;
            if (!ElevatorCalculations.getEnabledWorlds().get(index).contains(block.getWorld().getName())) return;

            if (hasPermissionForBlock(player, index))
            {
                int startPoint = blockY < 1 ? 1 : blockY;
                startPoint = startPoint > 256 ? 256 : startPoint;

                int x = block.getX(); int z = block.getZ();

                int y; int count = 0;
                for (y = startPoint - ElevatorCalculations.getMinHeightList().get(index); y > ElevatorCalculations.getMinYList().get(index); y--)
                {
                    count += 1;
                    if (count >= ElevatorCalculations.getMaxHeightList().get(index)) return;
                    
                    Block blockAtJ = block.getWorld().getBlockAt(x, y, z);
                    if (blockAtJ.getType().equals(block.getType()))
                        if (ElevatorCalculations.getBlock1ExceptionList().contains(blockAtJ.getRelative(UP, 1).getType().name()))
                            if (ElevatorCalculations.getBlock2ExceptionList().contains(blockAtJ.getRelative(UP, 2).getType().name()))
                                teleport(player, y, index, false);
                } 
            }
        }
    }

    @EventHandler
    public void onJumpEvent(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if ((event.getFrom().getY() < event.getTo().getY()) && Features.getElevator().getPermissionsConfig().hasPermission(player, "hyult.elevator.use", true))
        {
            Block block = player.getLocation().getBlock().getRelative(DOWN, 2);
            if (block == null) return;
            int blockY = block.getY();

            int index = ElevatorCalculations.getMaterialList().indexOf(block.getType());
            if (index == -1) return;

            if (blockY > ElevatorCalculations.getMaxYList().get(index) || blockY < ElevatorCalculations.getMinYList().get(index)) return;
            if (!ElevatorCalculations.getEnabledWorlds().get(index).contains(block.getWorld().getName())) return;

            if (hasPermissionForBlock(player, index))
            {
                int startPoint = blockY < 1 ? 1 : blockY;
                startPoint = startPoint > 256 ? 256 : startPoint;

                int x = block.getX(); int z = block.getZ();

                int y, count = 0;
                for (y = startPoint + ElevatorCalculations.getMinHeightList().get(index); y < ElevatorCalculations.getMaxYList().get(index); y++)
                {
                    count += 1;
                    if (count >= ElevatorCalculations.getMaxHeightList().get(index)) return;

                    Block blockAtJ = block.getWorld().getBlockAt(x, y, z);
                    if (blockAtJ.getType().equals(block.getType()))
                        if (ElevatorCalculations.getBlock1ExceptionList().contains(blockAtJ.getRelative(UP, 1).getType().name()))
                            if (ElevatorCalculations.getBlock2ExceptionList().contains(blockAtJ.getRelative(UP, 2).getType().name()))
                                teleport(player, y, index, true);
                }
            }
        }
    }
    
    private void teleport(Player player, int y, int index, boolean up)
    {
        Location playerCurrentLocation = player.getLocation();
        playerCurrentLocation.setY(y + 1);
        player.teleport(playerCurrentLocation);
        player.playSound(playerCurrentLocation, ElevatorCalculations.getSoundList().get(index), 1.0F, 0.0F);
        if (ElevatorCalculations.getSendMessageList().get(index))
            if (up)
                player.sendMessage(Features.getElevator().getMessenger().getPrefix() + ElevatorCalculations.getMessageUpList().get(index));
            else
                player.sendMessage(Features.getElevator().getMessenger().getPrefix() + ElevatorCalculations.getMessageDownList().get(index));
    }

    private boolean hasPermissionForBlock(Player player, int index)
    {
        if (ElevatorCalculations.getPermRequireList().get(index))
        {
            String permission = ElevatorCalculations.getPermNodeList().get(index);
            if (!player.hasPermission(permission))
            {
                Features.getElevator().getPermissionsConfig().sendNoPermissionMessage(player, permission);
                return false;
            }
        }
        return true;
    }

    private void debugArraylist(String message, ArrayList arrayList)
    {
        //什么都不做
        /*
        if (arrayList == null || arrayList.size() < 1) return;
        if (arrayList.get(0) instanceof Double)
        {
            loglogger.Debug(String.format("%s: %s", message, doubleArrayListToString(arrayList)));
        }
        else if (arrayList.get(0) instanceof String)
        {
            loglogger.Debug(String.format("%s: %s", message, arrayListToString(arrayList)));
        }
        else if (arrayList.get(0) instanceof Integer)
        {
            loglogger.Debug(String.format("%s: %s", message, intArrayListToString(arrayList)));
        }
        else if (arrayList.get(0) instanceof Material)
        {
            loglogger.Debug(String.format("%s: %s", message, materialArrayListToString(arrayList)));
        }*/
    }
}
