package cc.moecraft.hykilpikonna.ult.fix.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AbtnPermissions extends PermissionsConfig
{
    public AbtnPermissions()
    {
        super(Fix.getInstance(), Features.getABTN().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.abtn.bypass", true);
    }
}
