package cc.moecraft.hykilpikonna.ult.fix.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.commands.AsinpCommands;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AbtnConfig;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AsinpConfigs;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AbtnListener;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AsinpListener;
import cc.moecraft.hykilpikonna.ult.fix.features.messengers.AbtnMessenger;
import cc.moecraft.hykilpikonna.ult.fix.features.messengers.AsinpMessenger;
import cc.moecraft.hykilpikonna.ult.fix.features.permissions.AbtnPermissions;
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
    private static Feature ABTN;

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
        if (Fix.getInstance().getSwitches().getBoolean("Abtn.Enable"))
        {
            ABTN = new Feature();
            ABTN.setId("abtn");
            ABTN.setFriendlyName("Abtn");
            ABTN.setConfig(new AbtnConfig());
            ABTN.setMessenger(new AbtnMessenger(Fix.getInstance(), ABTN.getFriendlyName()));
            ABTN.setListener(new AbtnListener());
            ABTN.setPermissionsConfig(new AbtnPermissions());
        }
    }

    public static Feature getASINP()
    {
        return ASINP;
    }

    public static Feature getABTN()
    {
        return ABTN;
    }
}
