package cc.moecraft.hykilpikonna.ult.command.features.blockcommand;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class BlockCommandListener extends Listener
{
    public BlockCommandListener()
    {
        super(HyUltXCommand.getInstance());
    }

    @EventHandler (ignoreCancelled = true)
    public void onEvent(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage().split(" ").length > 0 ? event.getMessage().split(" ")[0] : "";
        if (!(BlockCommandStaticVariables.getBlockedCommandNames().contains(command))) return;
        if (BlockCommandStaticVariables.getBlockedCommands().get(BlockCommandStaticVariables.getBlockedCommandNames().indexOf(command)).check(event.getPlayer())) event.setCancelled(true);
    }
}
