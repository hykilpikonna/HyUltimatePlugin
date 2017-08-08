package cc.moecraft.hykilpikonna.ult.fun.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ElevatorPermissions extends PermissionsConfig
{
    public ElevatorPermissions()
    {
        super(Fun.getInstance(), Features.getElevator().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.elevator.use", false);
    }
}
