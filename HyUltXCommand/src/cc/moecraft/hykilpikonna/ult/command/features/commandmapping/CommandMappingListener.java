package cc.moecraft.hykilpikonna.ult.command.features.commandmapping;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandMappingListener extends Listener
{
    public CommandMappingListener()
    {
        super(HyUltXCommand.getInstance());
    }

    @EventHandler (ignoreCancelled = true)
    public void onEvent(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage().split(" ").length > 0 ? event.getMessage().split(" ")[0] : "";
        if (!(CommandMappingStaticVariables.getCommandMappingNames().contains(command))) return;
        ArrayList<Command> toCommands = CommandMappingStaticVariables.getCommandMappings().get(CommandMappingStaticVariables.getCommandMappingNames().indexOf(command)).check(command, event.getPlayer());
        if (toCommands == null) return;
        event.setCancelled(true);
        toCommands.forEach(toCommand -> {toCommand.sendCommand(event.getPlayer());});
    }
}
