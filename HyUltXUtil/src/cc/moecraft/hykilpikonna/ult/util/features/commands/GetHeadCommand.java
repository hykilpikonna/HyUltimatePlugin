package cc.moecraft.hykilpikonna.ult.util.features.commands;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import cc.moecraft.hykilpikonna.ult.util.features.calculations.GetHeadCalculations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GetHeadCommand extends CommandRunner
{
    public GetHeadCommand()
    {
        super(Util.getInstance(), Features.getGetHead().getId(), new TabCompletesBuilder()
                .append("%ONLINE_PLAYERS% -amount:")
                .append("-amount:")
                .build()
        );
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (!(sender instanceof Player))
        {
            Features.getGetHead().getMessenger().sendMessage(sender, "console_not_allowed");
            return;
        }
        int amount = 1;
        for (int i = 0; i < args.size(); i++)
        {
            String arg = args.get(i);
            if (arg.contains("-amount:"))
            {
                try
                {
                    amount = Integer.parseInt(arg.split("-amount:")[1]);
                }
                catch (Exception ignored)
                {
                    Features.getGetHead().getMessenger().sendMessage(sender, "failed_to_get_amount");
                    return;
                }
                args.remove(i);
            }
        }
        switch (args.size())
        {
            case 0:
                if (Features.getGetHead().getPermissionsConfig().hasPermission(sender, "hyult.gethead.self", true))
                    GetHeadCalculations.commandGetHead((Player) sender, amount, sender.getName());
                break;
            case 1:
                if (Features.getGetHead().getPermissionsConfig().hasPermission(sender, "hyult.gethead.others", true))
                    GetHeadCalculations.commandGetHead((Player) sender, amount, args.get(0));
                break;
            default:
                Main.sendHelpMessage(sender);
                break;
        }
    }
}
