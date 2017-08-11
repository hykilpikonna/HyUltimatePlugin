package cc.moecraft.hykilpikonna.ult.util.features.commands;

import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import cc.moecraft.hykilpikonna.ult.util.features.others.FishType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.util.features.Features.getFishModifier;
import static cc.moecraft.hykilpikonna.ult.utils.VersionUtils.getAllVersionItemInHand;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierCommand extends CommandRunner
{
    public FishModifierCommand()
    {
        super(Util.getInstance(), getFishModifier().getId(),
                new TabCompletesBuilder()
                        .append("add")
                        .build()
        );
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (!getFishModifier().getPermissionsConfig().hasPermission(player, "hyult.fishlimit.add", true)) return;
            if (args.size() == 3 && args.get(0).equals("add"))
            {
                double chance;

                try
                {
                    chance = Double.parseDouble(args.get(2));
                }
                catch (Exception catchException)
                {
                    getFishModifier().getMessenger().sendMessage(player, "command_not_number");
                    return;
                }

                if (getAllVersionItemInHand(player) != null)
                {
                    String path = args.get(1);
                    getFishModifier().getConfig().set("Items." + path + ".Types", FishType.ITEM);
                    getFishModifier().getConfig().set("Items." + path + ".ChanceInPercent", chance);
                    getFishModifier().getConfig().set("Items." + path + ".ItemStack", getAllVersionItemInHand(player));
                    getFishModifier().getConfig().save();
                    getFishModifier().getMessenger().sendMessage(player, "command_add_success");
                    getFishModifier().getConfig().reload();
                }
                else getFishModifier().getMessenger().sendMessage(player, "command_add_no_item_in_hand");
            }
            else getFishModifier().getMessenger().sendMessage(player, "command_not_enough_args");
        }
        else getFishModifier().getMessenger().sendMessage(sender, "command_console_not_available");
    }
}
