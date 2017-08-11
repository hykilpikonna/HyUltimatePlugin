package cc.moecraft.hykilpikonna.ult;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/01 创建!
 * Created by Hykilpikonna on 2017/08/01!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Permissions extends PermissionsConfig
{
    public Permissions() {
        super(Main.getMain(), "Permissions");
    }

    @Override
    public void writeConfig()
    {
        add("hyult.command.admin.reload", true);
        add("hyult.command.admin.reload.hyplugins", true);
        add("hyult.command.admin.reload.all", true);

        add("hyult.command.admin.install.url", true);
        add("hyult.command.admin.install.hyplugins", true);

        add("hyult.command.admin.load", true);
        add("hyult.command.admin.unload", true);
        add("hyult.command.admin.delete", true);
    }
}
