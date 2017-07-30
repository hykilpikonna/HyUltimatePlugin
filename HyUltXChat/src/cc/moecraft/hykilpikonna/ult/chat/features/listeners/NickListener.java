package cc.moecraft.hykilpikonna.ult.chat.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.chat.Chat;

import cc.moecraft.hykilpikonna.ult.chat.features.Features;
import cc.moecraft.hykilpikonna.ult.chat.features.databases.NickDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/06/21 创建!
 * Created by Hykilpikonna on 2017/06/21!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class NickListener extends Listener
{
    private Config database;

    public NickListener()
    {
        super(Chat.getHyUltXChat());
    }

    @EventHandler
    public void onEvent(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        if (((NickDatabase) Features.NICK.getDatabase()).getNickname(player.getName()) != null)
        {
            if (Features.NICK.getPermissionsConfig().hasPermission(player, "hyult.nickname.show", false))
            {
                player.setDisplayName(((NickDatabase) Features.NICK.getDatabase()).getNickname(player.getName()) + RESET);
            }
        }
    }
}
