package cc.moecraft.hykilpikonna.ult.util.features.uuidgetter;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class UUIDGetterCommand extends CommandRunner
{
    public UUIDGetterCommand()
    {
        super(Util.getInstance(), Features.getUuidGetter().getId());
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (!Features.getUuidGetter().getPermissionsConfig().hasPermission(sender, "hyult.util.uuidgetter.get", true)) return;
        if (args.size() == 1)
        {
            boolean offline = false;
            OfflinePlayer player = Bukkit.getPlayer(args.get(0));
            if (player == null)
            {
                player = Bukkit.getOfflinePlayer(args.get(0));
                offline = true;
            }
            String UUID = player.getUniqueId().toString();
            if (offline) sender.sendMessage(Features.getUuidGetter().getMessenger().getWithPrefix("get_offline").replace("%UUID%", UUID).replace("%PLAYER%", player.getName()));
            else sender.sendMessage(Features.getUuidGetter().getMessenger().getWithPrefix("get_online").replace("%UUID%", UUID).replace("%PLAYER%", player.getName()));
        }
        else Main.sendHelpMessage(sender);
    }
}
