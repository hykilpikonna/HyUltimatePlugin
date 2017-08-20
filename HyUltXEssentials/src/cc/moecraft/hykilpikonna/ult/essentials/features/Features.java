package cc.moecraft.hykilpikonna.ult.essentials.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.essentials.HyUltXEssentials;
import cc.moecraft.hykilpikonna.ult.essentials.features.entity.EntityCommand;
import cc.moecraft.hykilpikonna.ult.essentials.features.entity.EntityMessenger;
import cc.moecraft.hykilpikonna.ult.essentials.features.entity.EntityPermissions;

/**
 * 此类由 Hykilpikonna 在 2017/08/13 创建!
 * Created by Hykilpikonna on 2017/08/13!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature entityCommand;

    public Features()
    {
        if (HyUltXEssentials.getInstance().getSwitches().getBoolean("Entity.Enable"))
        {
            entityCommand = new Feature();
            entityCommand.setId("hyentity");
            entityCommand.setFriendlyName("Entity");
            entityCommand.setCommandRunner(new EntityCommand());
            entityCommand.setMessenger(new EntityMessenger());
            entityCommand.setPermissionsConfig(new EntityPermissions());
        }
    }

    public static Feature getEntityCommand() {
        return entityCommand;
    }
}
