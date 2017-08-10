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
public class ItemFixPermissions extends PermissionsConfig
{
    public ItemFixPermissions()
    {
        super(Util.getInstance(), Features.getItemFix().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.command.fix.self", true);
        add("hyult.command.fix.self.all", true);
        add("hyult.command.fix.others", true);
        add("hyult.command.fix.others.all", true);
    }
}
