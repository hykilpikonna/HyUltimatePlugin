package cc.moecraft.hykilpikonna.ult.fix.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.commands.AsinpCommands;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AsinpConfigs;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AsinpListener;
import cc.moecraft.hykilpikonna.ult.fix.features.messengers.AsinpMessenger;
import cc.moecraft.hykilpikonna.ult.fix.features.permissions.AsinpPermissions;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature ASINP;

    public Features()
    {
        if (Fix.getInstance().getSwitches().getBoolean("Asinp.Enable"))
        {
            ASINP = new Feature();
            ASINP.setId("asinp");
            ASINP.setFriendlyName("Asinp");
            ASINP.setPermissionsConfig(new AsinpPermissions());
            ASINP.setMessenger(new AsinpMessenger());
            ASINP.setConfig(new AsinpConfigs());
            ASINP.setCommandRunner(new AsinpCommands());
            ASINP.setListener(new AsinpListener());
        }
    }

    public static Feature getASINP() {
        return ASINP;
    }
}
