package cc.moecraft.hykilpikonna.ult.util.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import cc.moecraft.hykilpikonna.ult.util.features.calculations.HealthCalculations;
import cc.moecraft.hykilpikonna.ult.util.features.databases.HealthDatabase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.util.features.Features.getHealth;
import static cc.moecraft.hykilpikonna.ult.utils.PlayerUtils.isPlayer;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.isInt;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HealthCommand extends CommandRunner
{
    public HealthCommand()
    {
        super(Util.getInstance(), getHealth().getId(),
                new TabCompletesBuilder()
                        .append("see %ONLINE_PLAYERS%")
                        .append("lock")
                        .append("unlock %ONLINE_PLAYERS%")
                        .append("max")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (args.size() <= 0)
        {
            getHealth().getMessenger().sendMessage(sender,"command_missing_args");
            return;
        }

        Player targetPlayer;
        int healthLock;
        switch (args.get(0))
        {
            case "see":
                commandSee(sender, args);
                break;

            case "lock":
                if (args.size() >= 2) commandLock(sender, args);
                else getHealth().getMessenger().sendMessage(sender,"command_missing_args");
                break;

            case "unlock":
                commandUnlock(sender, args);
                break;

            case "max":
                commandMax(sender, args);
                break;

            default:
                commandHealth(sender, args);
                break;
        }
        getHealth().getDatabase().save();
    }

    private void commandHealth(CommandSender sender, ArrayList<String> args)
    {
        if (isInt(args.get(0)))
        {
            if (args.size() == 1)
                if (getHealth().getPermissionsConfig().hasPermission(sender, "hyult.health.self", true))
                {
                    if (isPlayer(sender))
                    {
                        int healthLock = Integer.parseInt(args.get(0)) > HealthCalculations.getMaxHealth((Player) sender) ? (int) Math.round(HealthCalculations.getMaxHealth((Player) sender)) : Integer.parseInt(args.get(0));
                        HealthCalculations.setHealth((Player) sender, healthLock);
                        sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_set_self").replace("%HEALTH%", Double.toString(((Player) sender).getHealth())));
                    }
                    else getHealth().getMessenger().sendMessage(sender, "command_console_not_allowed");
                }
            else if (getHealth().getPermissionsConfig().hasPermission(sender, "hyult.health.others", true))
            {
                Player targetPlayer = Bukkit.getPlayer(args.get(1));
                if (targetPlayer != null)
                {
                    int healthLock = Integer.parseInt(args.get(0)) > HealthCalculations.getMaxHealth(targetPlayer) ? (int) Math.round(HealthCalculations.getMaxHealth(targetPlayer)) : Integer.parseInt(args.get(0));
                    HealthCalculations.setHealth(targetPlayer, healthLock);
                    sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_set_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%HEALTH%", Integer.toString(healthLock)));
                    targetPlayer.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_set_target").replace("%HEALTH%", Integer.toString(healthLock)));
                }
                else sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(1)));
            }
        }
        else getHealth().getMessenger().sendMessage(sender, "command_not_numeric");
    }

    private void commandSee(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.health.see", args, 1);
        if (targetPlayer != null) sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_see").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%HEALTH%", Double.toString(targetPlayer.getHealth())));
    }

    private void commandLock(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.health.lock", args, 2);
        if (isInt(args.get(1)))
        {
            int healthLock= Integer.parseInt(args.get(1));
            if (healthLock > HealthCalculations.getMaxHealth(targetPlayer))
            {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("max");
                arrayList.add(Integer.toString(healthLock));
                arrayList.add(targetPlayer.getName());
                commandMax(sender, arrayList);
            }

            if (targetPlayer != null)
            {
                HealthCalculations.lockPlayerHealth(targetPlayer, healthLock);
                HealthCalculations.setHealth(targetPlayer, healthLock);
                HealthCalculations.setMaxHealth(targetPlayer, healthLock);
                sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_lock_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%HEALTHLOCK%", Integer.toString(healthLock)));
                targetPlayer.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_lock_target").replace("%HEALTHLOCK%", Integer.toString(healthLock)));
            }
        }
        else getHealth().getMessenger().sendMessage(sender, "command_not_numeric");
    }

    private void commandUnlock(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.health.unlock", args, 1);

        if (targetPlayer != null)
        {
            HealthCalculations.unlockPlayerHealth(targetPlayer);
            HealthCalculations.setMaxHealth(targetPlayer, 20);
            HealthCalculations.setHealth(targetPlayer, 20);
            sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_unlock_sender").replace("%TARGETPLAYER%", targetPlayer.getName()));
            targetPlayer.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_unlock_target"));
        }
    }

    private void commandMax(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.health.max", args, 2);

        if (isInt(args.get(1)))
        {
            int healthLock = Integer.parseInt(args.get(1));
            if (targetPlayer != null)
            {
                HealthCalculations.setMaxHealth(targetPlayer, healthLock);
                HealthCalculations.setHealth(targetPlayer, healthLock);
                sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_max_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%MAX%", Integer.toString(healthLock)));
                targetPlayer.sendMessage(getHealth().getMessenger().getWithPrefix("command_health_max_target").replace("%MAX%", Integer.toString(healthLock)));
            }
        }
        else getHealth().getMessenger().sendMessage(sender, "command_not_numeric");
    }

    private Player getPlayer(CommandSender sender, String permission, ArrayList<String> args, int index)
    {
        Player targetPlayer = null;
        if (args.size() == index)
        {
            if (getHealth().getPermissionsConfig().hasPermission(sender, permission + ".self", true))
            {
                if (isPlayer(sender)) targetPlayer = (Player) sender;
                else getHealth().getMessenger().sendMessage(sender, "command_console_not_allowed");
            }
        }
        else if (getHealth().getPermissionsConfig().hasPermission(sender, permission + ".others", true))
        {
            if (Bukkit.getPlayer(args.get(index)) != null) targetPlayer = Bukkit.getPlayer(args.get(index));
            else sender.sendMessage(getHealth().getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(index)));
        }

        return targetPlayer;
    }
}
