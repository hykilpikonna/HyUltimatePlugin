package cc.moecraft.hykilpikonna.ult.fun.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.configs.ElevatorConfig;
import cc.moecraft.hykilpikonna.ult.fun.features.configs.OreGenConfig;
import cc.moecraft.hykilpikonna.ult.fun.features.configs.ProjectileDamageConfig;
import cc.moecraft.hykilpikonna.ult.fun.features.listeners.ElevatorListener;
import cc.moecraft.hykilpikonna.ult.fun.features.listeners.OreGenListener;
import cc.moecraft.hykilpikonna.ult.fun.features.listeners.ProjectileDamageListener;
import cc.moecraft.hykilpikonna.ult.fun.features.messengers.ElevatorMessenger;
import cc.moecraft.hykilpikonna.ult.fun.features.messengers.ProjectileDamageMessenger;
import cc.moecraft.hykilpikonna.ult.fun.features.permissions.ElevatorPermissions;
import cc.moecraft.hykilpikonna.ult.fun.features.permissions.ProjectileDamagePermissions;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature OreGenerator;
    private static Feature ProjectileDamage;
    private static Feature Elevator;

    public Features()
    {
        if (Fun.getInstance().getSwitches().getBoolean("OreGenerator.Enable"))
        {
            OreGenerator = new Feature();
            OreGenerator.setId("oregenerator");
            OreGenerator.setFriendlyName("OreGenerator");
            OreGenerator.setListener(new OreGenListener());
            OreGenerator.setConfig(new OreGenConfig());
        }
        if (Fun.getInstance().getSwitches().getBoolean("ProjectileDamage.Enable"))
        {
            ProjectileDamage = new Feature();
            ProjectileDamage.setId("projectiledamage");
            ProjectileDamage.setFriendlyName("ProjectileDamage");
            ProjectileDamage.setMessenger(new ProjectileDamageMessenger());
            ProjectileDamage.setConfig(new ProjectileDamageConfig());
            ProjectileDamage.setListener(new ProjectileDamageListener());
            ProjectileDamage.setPermissionsConfig(new ProjectileDamagePermissions());
        }
        if (Fun.getInstance().getSwitches().getBoolean("Elevator.Enable"))
        {
            Elevator = new Feature();
            Elevator.setId("elevator");
            Elevator.setFriendlyName("Elevator");
            Elevator.setConfig(new ElevatorConfig());
            Elevator.setPermissionsConfig(new ElevatorPermissions());
            Elevator.setListener(new ElevatorListener());
            Elevator.setMessenger(new ElevatorMessenger());
        }
    }

    public static Feature getElevator()
    {
        return Elevator;
    }

    public static Feature getOreGenerator()
    {
        return OreGenerator;
    }

    public static Feature getProjectileDamage()
    {
        return ProjectileDamage;
    }
}
