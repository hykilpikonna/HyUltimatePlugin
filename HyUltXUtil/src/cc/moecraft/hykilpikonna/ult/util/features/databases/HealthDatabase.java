package cc.moecraft.hykilpikonna.ult.util.features.databases;

import cc.moecraft.hykilpikonna.ult.api.Database;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.entity.Player;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HealthDatabase extends Database
{
    public HealthDatabase()
    {
        super(Util.getInstance(), Features.getHealth().getFriendlyName());
    }
}
