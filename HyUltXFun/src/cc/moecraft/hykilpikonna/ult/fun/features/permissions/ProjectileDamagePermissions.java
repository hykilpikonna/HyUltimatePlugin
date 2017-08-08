package cc.moecraft.hykilpikonna.ult.fun.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ProjectileDamagePermissions extends PermissionsConfig
{
    public ProjectileDamagePermissions()
    {
        super(Fun.getInstance(), Features.getProjectileDamage().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.pd.damage", false);
    }
}
