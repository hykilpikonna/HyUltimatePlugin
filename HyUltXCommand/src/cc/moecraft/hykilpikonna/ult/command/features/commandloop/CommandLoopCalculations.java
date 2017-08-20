package cc.moecraft.hykilpikonna.ult.command.features.commandloop;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.ingame.PlayerCommand;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/17 创建!
 * Created by Hykilpikonna on 2017/08/17!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandLoopCalculations
{
    /**
     * 执行一条循环指令 (所有时间单位为Ticks)
     *
     * @param player 玩家
     * @param delay 开始延迟
     * @param period 每两次执行之间的延迟
     * @param time 总时长
     */
    public static void scheduleRunnable(Player player, ArrayList<PlayerCommand> startCommands, ArrayList<PlayerCommand> commands, ArrayList<PlayerCommand> endCommands, int delay, int period, int time, boolean async)
    {
        Main.tempDebug("计划指令循环: ");
        Main.tempDebug("  - Player = " + player.getName());
        Main.tempDebug("  - Commands = " + commands.toString());
        Main.tempDebug("  - Delay = " + delay);
        Main.tempDebug("  - Period = " + period);
        Main.tempDebug("  - Time = " + time);
        Main.tempDebug("  - Async = " + async);

        if (startCommands != null) startCommands.forEach(PlayerCommand::sendCommands);

        BukkitRunnable runCommand = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                try { commands.forEach(PlayerCommand::sendCommands); } catch (Exception ignored) {}
            }
        };

        BukkitRunnable cancelTask = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                runCommand.cancel();
                if (endCommands != null) endCommands.forEach(PlayerCommand::sendCommands);
            }
        };

        if (async)
        {
            runCommand.runTaskTimerAsynchronously(HyUltXCommand.getInstance(), delay, period);
            cancelTask.runTaskLaterAsynchronously(HyUltXCommand.getInstance(), time);
        }
        else
        {
            runCommand.runTaskTimer(HyUltXCommand.getInstance(), delay, period);
            cancelTask.runTaskLater(HyUltXCommand.getInstance(), time);
        }
    }
}
