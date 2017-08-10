package cc.moecraft.hykilpikonna.ult.util.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.util.features.calculations.ItemFixCalculations.fixAll;
import static cc.moecraft.hykilpikonna.ult.util.features.calculations.ItemFixCalculations.fixItemInHand;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ItemFixCommand extends CommandRunner
{
    public ItemFixCommand()
    {
        super(Util.getInstance(), Features.getItemFix().getId(),
                new TabCompletesBuilder()
                        .append("fix %ONLINE_PLAYERS%")
                        .append("fix all %ONLINE_PLAYERS%")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (args.size() == 0)
        {
            if (!Features.getItemFix().getPermissionsConfig().hasPermission(sender, "hyult.command.fix.self", true)) return;
            if (sender instanceof Player)
            {
                fixItemInHand((Player) sender);
                Features.getItemFix().getMessenger().sendMessage(sender, "fix_self_success");
            }
            else Features.getItemFix().getMessenger().sendMessage(sender, "fix_self_console_unallowed");
            return;
        }

        if (args.get(0).toLowerCase().equals("all"))
        {
            if (args.size() == 1 && Features.getItemFix().getPermissionsConfig().hasPermission(sender, "hyult.command.fix.self.all", true))
                if (sender instanceof Player) sender.sendMessage(Features.getItemFix().getMessenger().getWithPrefix("fix_all_self_success").replace("%COUNT%", Integer.toString(fixAll((Player) sender))));
                else Features.getItemFix().getMessenger().sendMessage(sender, "fix_all_self_console_unallowed");
            else if (Features.getItemFix().getPermissionsConfig().hasPermission(sender, "hyult.command.fix.others.all", true))
            {
                Player targetPlayer = Bukkit.getPlayer(args.get(0));
                if (targetPlayer != null)
                {
                    int count = fixAll(targetPlayer);
                    sender.sendMessage(Features.getItemFix().getMessenger().getWithPrefix("fix_all_other_success_sender").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%COUNT%", Integer.toString(count)));
                    targetPlayer.sendMessage(Features.getItemFix().getMessenger().getWithPrefix("fix_all_other_success_target").replace("%PLAYER%", sender.getName()).replace("%COUNT%", Integer.toString(count)));
                }
                else Features.getItemFix().getMessenger().sendMessage(sender, "fix_player_not_found");
            }
        }
        else
        {
            if (args.size() == 1 && Features.getItemFix().getPermissionsConfig().hasPermission(sender, "hyult.command.fix.others", true))
            {
                Player targetPlayer = Bukkit.getPlayer(args.get(0));
                if (targetPlayer != null)
                {
                    fixItemInHand(targetPlayer);
                    sender.sendMessage(Features.getItemFix().getMessenger().getWithPrefix("fix_other_success_sender").replace("%TARGETPLAYER%", targetPlayer.getName()));
                    targetPlayer.sendMessage(Features.getItemFix().getMessenger().getWithPrefix("fix_other_success_target").replace("%PLAYER%", sender.getName()));
                }
                else Features.getItemFix().getMessenger().sendMessage(sender, "fix_player_not_found");
            }
        }
    }
}
