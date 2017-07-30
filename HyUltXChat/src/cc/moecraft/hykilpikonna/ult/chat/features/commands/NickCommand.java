package cc.moecraft.hykilpikonna.ult.chat.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.databases.NickDatabase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.plugin2.main.server.Plugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.chat.features.Features.NICK;
import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.ult.utils.PlayerUtils.isPlayer;

/**
 * 此类由 Hykilpikonna 在 2017/07/19 创建!
 * Created by Hykilpikonna on 2017/07/19!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class NickCommand extends CommandRunner
{
    public NickCommand()
    {
        super(Chat.getHyUltXChat(), NICK.getId(),
                new TabCompletesBuilder()
                        .append("set %ONLINE_PLAYERS%")
                        .append("see %ONLINE_PLAYERS%")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender player, Command command, String s, ArrayList<String> args)
    {
        if (args.size() == 0)
        {
            player.sendMessage(NICK.getMessenger().getWithPrefix("command_missing_args"));
            return;
        }
        Player targetPlayer;
        switch (args.get(0))
        {
            case "see":
                if (args.size() == 1)
                {
                    if (!NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.see.self", true)) return;

                    if (isPlayer(player)) player.sendMessage(NICK.getMessenger().getWithPrefix("command_see_self").replace("%NICK%", ((NickDatabase)NICK.getDatabase()).getNickname(player.getName())));
                    else player.sendMessage(NICK.getMessenger().getWithPrefix("command_console_not_allowed"));
                    return;
                }
                if (!NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.see.others", true)) return;
                targetPlayer = Bukkit.getPlayer(args.get(1));

                if (targetPlayer != null) player.sendMessage(NICK.getMessenger().getWithPrefix("command_see_other").replace("%TARGETPLAYER%", targetPlayer.getName()).replace("%NICK%", ((NickDatabase)NICK.getDatabase()).getNickname(targetPlayer.getName())));
                else player.sendMessage(NICK.getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(1)));
                break;

            case "set":
                if (! NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.set.others", true)) return;
                if (args.size() < 3)
                {
                    player.sendMessage(NICK.getMessenger().getWithPrefix("command_missing_args"));
                    return;
                }
                targetPlayer = Bukkit.getPlayer(args.get(1));
                if (targetPlayer != null)
                {
                    ((NickDatabase)NICK.getDatabase()).setNickname(targetPlayer, getTheRestToString(args, 2));
                    player.sendMessage(NICK.getMessenger().getWithPrefix("command_set_other")
                            .replace("%TARGETPLAYER%", targetPlayer.getName())
                            .replace("%NICK%", ((NickDatabase)NICK.getDatabase()).getNickname(targetPlayer)));
                }
                else player.sendMessage(NICK.getMessenger().getWithPrefix("command_player_not_found").replace("%PLAYER%", args.get(1)));
                break;
            default:
                if (!NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.set.self", true)) return;
                if (isPlayer(player))
                {
                    ((NickDatabase)NICK.getDatabase()).setNickname((Player) player, getTheRestToString(args, 0));
                    player.sendMessage(NICK.getMessenger().getWithPrefix("command_set_self").replace("%NICK%", ((NickDatabase)NICK.getDatabase()).getNickname((Player) player)));
                }
                else
                {
                    player.sendMessage(NICK.getMessenger().getWithPrefix("command_console_not_allowed"));
                }   
                break;
        }
        return;
    }
}
