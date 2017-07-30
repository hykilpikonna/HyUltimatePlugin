package cc.moecraft.hykilpikonna.ult.chat.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.calculations.ClsCalculations;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;
import cc.moecraft.hykilpikonna.ult.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.utils.PlayerUtils.isPlayer;
import static cc.moecraft.hykilpikonna.ult.chat.features.calculations.ClsCalculations.clsAll;
import static cc.moecraft.hykilpikonna.ult.chat.features.calculations.ClsCalculations.sendClsMessage;

/**
 * 此类由 Hykilpikonna 在 2017/06/21 创建!
 * Created by Hykilpikonna on 2017/06/21!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ClsCommand extends CommandRunner
{
    public ClsCommand()
    {
        //创建指令和自动补全
        super(
                Chat.getHyUltXChat(),
                "cls",
                new TabCompletesBuilder()
                        .append("%ONLINE_PLAYERS%")
                        .append("all %ONLINE_PLAYERS%")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender player, Command command, String s, ArrayList<String> args)
    {
        if (args.size() == 0)
        {
            if (Features.CLS.getPermissionsConfig().hasPermission(player,"hyult.command.cls.self", true))
            {
                if (isPlayer(player)) ClsCalculations.sendClsMessage(player);
                else Features.CLS.getMessenger().sendMessage(player, "console_unallowed_cls_self");
            }
            return;
        }
        switch (args.get(0).toLowerCase())
        {
            case "all":
                if (Features.CLS.getPermissionsConfig().hasPermission(player,"hyult.command.cls.all", true))
                {
                    clsAll();
                    Features.CLS.getMessenger().sendMessage(player, "cls_all_success");
                }
                break;
            default:
                if (Features.CLS.getPermissionsConfig().hasPermission(player,"hyult.command.cls.others", true))
                {
                    Player clsPlayer = Bukkit.getPlayer(args.get(0));
                    if (clsPlayer != null)
                    {
                        sendClsMessage(clsPlayer);
                        player.sendMessage(Features.CLS.getMessenger().getWithPrefix("cls_player_success").replace("%PLAYER%", clsPlayer.getName()));
                    }
                    else player.sendMessage(Features.CLS.getMessenger().getWithPrefix("cls_player_not_found").replace("%PLAYER%", args.get(0)));
                }
                break;
        }
    }
}
