package cc.moecraft.hykilpikonna.ult.util.features.uuidgetter;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.ChatColor;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class UUIDGetterMessenger extends Messenger
{
    public UUIDGetterMessenger()
    {
        super(Util.getInstance(), Features.getUuidGetter().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&lUUID获取&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("get_online", ChatColor.GREEN + "在线玩家%PLAYER%的UUID是: %UUID%");
        addDefault("get_offline", ChatColor.GREEN + "离线玩家%PLAYER%的UUID是: %UUID% (可能不准确)");
    }
}
