package cc.moecraft.hykilpikonna.ult.fix.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2017/08/12 创建!
 * Created by Hykilpikonna on 2017/08/12!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CurseOfVanishingFixPermissions extends PermissionsConfig
{
    public CurseOfVanishingFixPermissions()
    {
        super(Fix.getInstance(), Features.getCurseOfVanishingFix().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.covf.bypass", true);
    }
}
