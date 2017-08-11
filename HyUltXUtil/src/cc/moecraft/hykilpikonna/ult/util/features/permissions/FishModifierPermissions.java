package cc.moecraft.hykilpikonna.ult.util.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CORBA.PERSIST_STORE;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierPermissions extends PermissionsConfig
{
    public FishModifierPermissions()
    {
        super(Util.getInstance(), Features.getFishModifier().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.fishlimit.add", true);
    }
}
