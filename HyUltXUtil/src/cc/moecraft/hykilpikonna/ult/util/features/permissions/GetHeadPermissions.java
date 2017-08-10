package cc.moecraft.hykilpikonna.ult.util.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GetHeadPermissions extends PermissionsConfig
{
    public GetHeadPermissions()
    {
        super(Util.getInstance(), Features.getGetHead().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.gethead.self", true);
        add("hyult.gethead.others", true);
    }
}
