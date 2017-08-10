package cc.moecraft.hykilpikonna.ult.util.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.calculations.FoodLevelCalculations;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.util.features.Features.getFoodLevel;
import static cc.moecraft.hykilpikonna.ult.util.features.Features.getHealth;
import static cc.moecraft.hykilpikonna.ult.utils.PlayerUtils.isPlayer;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.isInt;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FoodLevelCommand extends CommandRunner
{
    public FoodLevelCommand()
    {
        super(Util.getInstance(), getFoodLevel().getFriendlyName(),
                new TabCompletesBuilder()
                        .append("see %ONLINE_PLAYERS%")
                        .append("lock")
                        .append("unlock %ONLINE_PLAYERS%")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (args.size() <= 0)
        {
            getFoodLevel().getMessenger().sendMessage(sender, "command_missing_args");
            return;
        }
        
        Player targetPlayer;
        int SatLock;
        switch (args.get(0))
        {
            case "see":
                commandSee(sender, args);
                break;

            case "lock":
                if (args.size() >= 2) commandLock(sender, args);
                else getFoodLevel().getMessenger().sendMessage(sender, "command_missing_args");
                break;

            case "unlock":
                commandUnlock(sender, args);
                break;

            default:
                commandFoodLevel(sender, args);
                break;
        }
        getFoodLevel().getDatabase().save();
    }

    private void commandFoodLevel(CommandSender sender, ArrayList<String> args)
    {
        if (isInt(args.get(0)))
        {
            int SatLock = Integer.parseInt(args.get(0)) > 20 ? 20 : Integer.parseInt(args.get(0));

            if (args.size() == 1)
                if (getFoodLevel().getPermissionsConfig().hasPermission(sender, "hyult.foodlevel.self", true))
                {
                    if (isPlayer(sender))
                    {
                        FoodLevelCalculations.setFoodLevel((Player) sender, SatLock);
                        sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_set_self").replace("%FOOD%", Integer.toString(SatLock)));
                    }
                    else getFoodLevel().getMessenger().sendMessage(sender, "command_console_not_allowed");
                }
            else if (getFoodLevel().getPermissionsConfig().hasPermission(sender, "hyult.foodlevel.others", true))
            {
                Player targetPlayer = Bukkit.getPlayer(args.get(1));
                if (targetPlayer != null)
                {
                    FoodLevelCalculations.setFoodLevel(targetPlayer, SatLock);
                    sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_set_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%FOOD%", Integer.toString(SatLock)));
                    targetPlayer.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_set_target").replace("%FOOD%", Integer.toString(SatLock)));
                }
                else sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(1)));
            }
        }
        else getFoodLevel().getMessenger().sendMessage(sender, "command_not_numeric");
    }

    private void commandUnlock(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.foodlevel.unlock", args, 1);

        if (targetPlayer != null)
        {
            FoodLevelCalculations.unlockPlayerFoodLevel(targetPlayer);
            FoodLevelCalculations.setFoodLevel(targetPlayer, 20);
            sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_unlock_sender").replace("%TARGETPLAYER%", targetPlayer.getName()));
            targetPlayer.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_unlock_target"));
        }
    }

    private void commandLock(CommandSender sender, ArrayList<String> args) 
    {
        Player targetPlayer = getPlayer(sender, "hyult.foodlevel.lock", args, 2);

        if (isInt(args.get(1)))
        {
            int SatLock = Integer.parseInt(args.get(1)) > 20 ? 20 : Integer.parseInt(args.get(1));
            
            if (targetPlayer != null)
            {
                FoodLevelCalculations.lockPlayerFoodLevel(targetPlayer, SatLock);
                FoodLevelCalculations.setFoodLevel(targetPlayer, SatLock);
                sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_lock_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%FOOD%", Integer.toString(SatLock)));
                targetPlayer.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_lock_target").replace("%FOOD%", Integer.toString(SatLock)));
            }
        }
        else getFoodLevel().getMessenger().sendMessage(sender, "command_not_numeric");
    }

    private void commandSee(CommandSender sender, ArrayList<String> args)
    {
        Player targetPlayer = getPlayer(sender, "hyult.foodlevel.see", args, 1);
        if (targetPlayer != null) sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_sat_see").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%FOOD%", Integer.toString(targetPlayer.getFoodLevel())));
    }

    private Player getPlayer(CommandSender sender, String permission, ArrayList<String> args, int index)
    {
        Player targetPlayer = null;
        if (args.size() == index)
            if (getFoodLevel().getPermissionsConfig().hasPermission(sender, permission + ".self", true))
                if (isPlayer(sender)) targetPlayer = (Player) sender;
                else getFoodLevel().getMessenger().sendMessage(sender, "command_console_not_allowed");
            else if (getFoodLevel().getPermissionsConfig().hasPermission(sender, permission + ".others", true))
                if (Bukkit.getPlayer(args.get(index)) != null) targetPlayer = Bukkit.getPlayer(args.get(index));
                else sender.sendMessage(getFoodLevel().getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(index)));

        return targetPlayer;
    }
}
