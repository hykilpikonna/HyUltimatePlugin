package cc.moecraft.hykilpikonna.ult.fix.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.antientityexplode.AntiEntityExplodeConfig;
import cc.moecraft.hykilpikonna.ult.fix.features.antientityexplode.AntiEntityExplodeListener;
import cc.moecraft.hykilpikonna.ult.fix.features.commands.AsinpCommands;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AbtnConfig;
import cc.moecraft.hykilpikonna.ult.fix.features.configs.AsinpConfigs;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AbtnListener;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AsinpListener;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.CurseOfVanishingFixListener;
import cc.moecraft.hykilpikonna.ult.fix.features.messengers.AbtnMessenger;
import cc.moecraft.hykilpikonna.ult.fix.features.messengers.AsinpMessenger;
import cc.moecraft.hykilpikonna.ult.fix.features.permissions.AbtnPermissions;
import cc.moecraft.hykilpikonna.ult.fix.features.permissions.AsinpPermissions;
import cc.moecraft.hykilpikonna.ult.fix.features.permissions.CurseOfVanishingFixPermissions;

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
    private static Feature curseOfVanishingFix;
    private static Feature antiEntityExplode;

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
        if (Fix.getInstance().getSwitches().getBoolean("CurseOfVanishingFix.Enable"))
        {
            curseOfVanishingFix = new Feature();
            curseOfVanishingFix.setId("curseofvanishingfix");
            curseOfVanishingFix.setFriendlyName("CurseOfVanishingFix");
            curseOfVanishingFix.setListener(new CurseOfVanishingFixListener());
            curseOfVanishingFix.setPermissionsConfig(new CurseOfVanishingFixPermissions());
        }
        if (Fix.getInstance().getSwitches().getBoolean("AntiEntityExplode.Enable"))
        {
            curseOfVanishingFix = new Feature();
            curseOfVanishingFix.setId("antientityexplode");
            curseOfVanishingFix.setFriendlyName("AntiEntityExplode");
            curseOfVanishingFix.setListener(new AntiEntityExplodeListener());
            curseOfVanishingFix.setConfig(new AntiEntityExplodeConfig());
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

    public static Feature getCurseOfVanishingFix()
    {
        return curseOfVanishingFix;
    }

    public static Feature getAntiEntityExplode() {
        return antiEntityExplode;
    }
}
