package cc.moecraft.hykilpikonna.ult.util.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.GREEN;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GetHeadMessenger extends Messenger
{
    public GetHeadMessenger()
    {
        super(Util.getInstance(), Features.getGetHead().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l头颅获取&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("console_not_allowed", String.format("%s后台无法获取自己的头颅", RED));
        addDefault("failed_to_get_amount", String.format("%s错误! -amount:标签数据读取失败!", RED));
        addDefault("success", String.format("%s您成功获取%s个%s%s%s%s的头颅", GREEN, "%AMOUNT%", AQUA, BOLD, "%PLAYER%", GREEN));
    }
}
