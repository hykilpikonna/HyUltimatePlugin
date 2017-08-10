package cc.moecraft.hykilpikonna.ult.util.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.util.Util;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static cc.moecraft.hykilpikonna.ult.util.features.Features.getPullback;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PullbackListener extends Listener
{
    public PullbackListener()
    {
        super(Util.getInstance());
    }

    @EventHandler
    public void onEvent(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if (getPullback().getPermissionsConfig().hasPermission(player, "hyult.pullback", false))
            if (player.getLocation().getBlockY() <= getPullback().getConfig().getInt("DefaultY")) pullback(player);
    }

    private void pullback(Player player)
    {
        if (getPullback().getConfig().getBoolean("DisableFallDamage"))
        {
            player.setFallDistance(-255.0F);
        }
        Location newLoc = player.getLocation();
        newLoc.setY(getPullback().getConfig().getInt("DefaultPullbackY"));
        if (getPullback().getConfig().getBoolean("SetFacingDown")) newLoc.setPitch(90);
        player.teleport(newLoc);
        getPullback().getMessenger().sendMessage(player, "pullback_success");
    }
}
