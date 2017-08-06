package cc.moecraft.hykilpikonna.ult.fix.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpPermissions extends PermissionsConfig
{
    public AsinpPermissions()
    {
        super(Fix.getInstance(), Features.getASINP().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.asinp.tp", false);

        add("hyult.command.asinp.setspawn", true);
    }
}
