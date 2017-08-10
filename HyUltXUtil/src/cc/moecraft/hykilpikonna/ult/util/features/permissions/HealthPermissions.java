package cc.moecraft.hykilpikonna.ult.util.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HealthPermissions extends PermissionsConfig
{
    public HealthPermissions()
    {
        super(Util.getInstance(), Features.getHealth().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.health.self", true);
        add("hyult.health.others", true);
        add("hyult.health.see.self", true);
        add("hyult.health.see.others", true);
        add("hyult.health.lock.self", true);
        add("hyult.health.lock.others", true);
        add("hyult.health.unlock.self", true);
        add("hyult.health.unlock.others", true);
        add("hyult.health.max.self", true);
        add("hyult.health.max.others", true);
    }
}
