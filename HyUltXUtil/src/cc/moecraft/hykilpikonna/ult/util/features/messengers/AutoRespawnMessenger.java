package cc.moecraft.hykilpikonna.ult.util.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.GREEN;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AutoRespawnMessenger extends Messenger
{
    public AutoRespawnMessenger()
    {
        super(Util.getInstance(), Features.getAutoRespawn().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l重生&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("respawn_success", GREEN + "您已自动重生");
    }
}
