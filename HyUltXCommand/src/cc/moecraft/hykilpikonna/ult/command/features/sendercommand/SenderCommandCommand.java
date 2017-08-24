package cc.moecraft.hykilpikonna.ult.command.features.sendercommand;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import cc.moecraft.hykilpikonna.ult.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class SenderCommandCommand extends CommandRunner
{
    public SenderCommandCommand()
    {
        super(HyUltXCommand.getInstance(), Features.getSenderCommand().getId());
        Bukkit.getPluginCommand("ccmd").setExecutor(this);
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (command.getName().equals("pcmd"))
        {
            if (!Features.getSenderCommand().getPermissionsConfig().hasPermission(sender, "hyult.commands.sendercommand.player", true)) return;
            if (!(args.size() < 2))
            {
                Player player = Bukkit.getPlayer(args.get(0));
                if (player != null)
                {
                    String commandToSend = ArrayUtils.getTheRestToString(args, 1);
                    player.chat(commandToSend);
                    sender.sendMessage(Features.getSenderCommand().getMessenger().getWithPrefix("sent_player").replace("%PLAYER%", player.getName()).replace("%MESSAGE%", commandToSend));
                }
                else sender.sendMessage(Features.getSenderCommand().getMessenger().getWithPrefix("player_not_found").replace("%PLAYER%", args.get(0)));
            }
            else Main.sendHelpMessage(sender);
        }
        else
        {
            if (!Features.getSenderCommand().getPermissionsConfig().hasPermission(sender, "hyult.commands.sendercommand.console", true)) return;
            if (!(args.size() < 1))
            {
                String commandToSend = ArrayUtils.getTheRestToString(args, 0);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToSend);
                sender.sendMessage(Features.getSenderCommand().getMessenger().getWithPrefix("sent_console").replace("%MESSAGE%", commandToSend));
            }
            else Main.sendHelpMessage(sender);
        }
    }
}
