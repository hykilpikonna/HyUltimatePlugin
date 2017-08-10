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
public class PullbackMessenger extends Messenger
{
    public PullbackMessenger()
    {
        super(Util.getInstance(), Features.getPullback().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l拉回&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("pullback_success", GREEN + "您已被从虚空拉回!");
    }
}
