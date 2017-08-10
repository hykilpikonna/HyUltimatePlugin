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
public class FoodLevelPermissions extends PermissionsConfig
{
    public FoodLevelPermissions()
    {
        super(Util.getInstance(), Features.getFoodLevel().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.foodlevel.self", true);
        add("hyult.foodlevel.others", true);
        add("hyult.foodlevel.see.self", true);
        add("hyult.foodlevel.see.others", true);
        add("hyult.foodlevel.lock.self", true);
        add("hyult.foodlevel.lock.others", true);
        add("hyult.foodlevel.unlock.self", true);
        add("hyult.foodlevel.unlock.others", true);
    }
}
