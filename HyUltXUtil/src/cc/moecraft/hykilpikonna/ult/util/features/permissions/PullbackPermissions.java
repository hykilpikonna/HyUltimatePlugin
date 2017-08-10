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
public class PullbackPermissions extends PermissionsConfig
{
    public PullbackPermissions()
    {
        super(Util.getInstance(), Features.getPullback().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.pullback", false);
    }
}
