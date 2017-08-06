package cc.moecraft.hykilpikonna.ult.fix.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AsinpConfigs;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpCommands extends CommandRunner
{
    public AsinpCommands() {
        super(Fix.getInstance(), Features.getASINP().getId(), new TabCompletesBuilder().append("setspawn").build());
    }

    @Override
    public void runCommand(CommandSender commandSender, Command command, String alias, ArrayList<String> args) {
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if (args.size() < 1 || !args.get(0).equals("setspawn"))
            {
                Features.getASINP().getMessenger().sendMessage(player, "command_error");
                return;
            }

            if (Features.getASINP().getPermissionsConfig().hasPermission(player, "hyult.command.asinp.setspawn", true))
            {
                Location location = player.getLocation();
                ((AsinpConfigs) Features.getASINP().getConfig()).setConfigLocation(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

                String message = Features.getASINP().getMessenger()
                        .getWithPrefix("command_setspawn_success")
                        .replace("%WORLD%", location.getWorld().getName())
                        .replace("%X%", ((Integer) location.getBlockX()).toString())
                        .replace("%Y%", ((Integer) location.getBlockY()).toString())
                        .replace("%Z%", ((Integer) location.getBlockZ()).toString())
                        .replace("%YAW%", ((Float) location.getYaw()).toString())
                        .replace("%PITCH%", ((Float) location.getPitch()).toString());
                player.sendMessage(message);
            }
        }
        else
        {
            Features.getASINP().getMessenger().sendMessage(commandSender, "command_console_not_allowed");
        }
    }
}
