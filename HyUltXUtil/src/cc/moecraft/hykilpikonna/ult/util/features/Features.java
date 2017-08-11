package cc.moecraft.hykilpikonna.ult.util.features;

import cc.moecraft.hykilpikonna.ult.Configs;
import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.commands.*;
import cc.moecraft.hykilpikonna.ult.util.features.configs.AutoRespawnConfig;
import cc.moecraft.hykilpikonna.ult.util.features.configs.FishModifierConfig;
import cc.moecraft.hykilpikonna.ult.util.features.configs.ItemFixConfig;
import cc.moecraft.hykilpikonna.ult.util.features.configs.PullbackConfig;
import cc.moecraft.hykilpikonna.ult.util.features.databases.FoodLevelDatabase;
import cc.moecraft.hykilpikonna.ult.util.features.databases.HealthDatabase;
import cc.moecraft.hykilpikonna.ult.util.features.listeners.*;
import cc.moecraft.hykilpikonna.ult.util.features.messengers.*;
import cc.moecraft.hykilpikonna.ult.util.features.permissions.*;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature
            itemFix,
            getHead,
            health,
            foodLevel,
            pullback,
            autoRespawn,
            fishModifier
    ;

    public Features()
    {
        if (Util.getInstance().getSwitches().getBoolean("ItemFix.Enable"))
        {
            itemFix = new Feature();
            itemFix.setId("itemfix");
            itemFix.setFriendlyName("ItemFix");
            itemFix.setConfig(new ItemFixConfig());
            itemFix.setMessenger(new ItemFixMessenger());
            itemFix.setPermissionsConfig(new ItemFixPermissions());
            itemFix.setCommandRunner(new ItemFixCommand());
        }
        if (Util.getInstance().getSwitches().getBoolean("GetHead.Enable"))
        {
            getHead = new Feature();
            getHead.setId("gethead");
            getHead.setFriendlyName("GetHead");
            getHead.setMessenger(new GetHeadMessenger());
            getHead.setPermissionsConfig(new GetHeadPermissions());
            getHead.setCommandRunner(new GetHeadCommand());
        }
        if (Util.getInstance().getSwitches().getBoolean("Health.Enable"))
        {
            health = new Feature();
            health.setId("health");
            health.setFriendlyName("Health");
            health.setDatabase(new HealthDatabase());
            health.setMessenger(new HealthMessenger());
            health.setPermissionsConfig(new HealthPermissions());
            health.setCommandRunner(new HealthCommand());
            health.setListener(new HealthListener());
        }
        if (Util.getInstance().getSwitches().getBoolean("FoodLevel.Enable"))
        {
            foodLevel = new Feature();
            foodLevel.setId("foodlevel");
            foodLevel.setFriendlyName("FoodLevel");
            foodLevel.setDatabase(new FoodLevelDatabase());
            foodLevel.setMessenger(new FoodLevelMessenger());
            foodLevel.setPermissionsConfig(new FoodLevelPermissions());
            foodLevel.setCommandRunner(new FoodLevelCommand());
            foodLevel.setListener(new FoodLevelListener());
        }
        if (Util.getInstance().getSwitches().getBoolean("Pullback.Enable"))
        {
            pullback = new Feature();
            pullback.setId("pullback");
            pullback.setFriendlyName("Pullback");
            pullback.setConfig(new PullbackConfig());
            pullback.setMessenger(new PullbackMessenger());
            pullback.setPermissionsConfig(new PullbackPermissions());
            pullback.setListener(new PullbackListener());
        }
        if (Util.getInstance().getSwitches().getBoolean("AutoRespawn.Enable"))
        {
            autoRespawn = new Feature();
            autoRespawn.setId("autorespawn");
            autoRespawn.setFriendlyName("AutoRespawn");
            autoRespawn.setConfig(new AutoRespawnConfig());
            autoRespawn.setMessenger(new AutoRespawnMessenger());
            autoRespawn.setPermissionsConfig(new AutoRespawnPermissions());
            autoRespawn.setListener(new AutoRespawnListener());
        }
        if (Util.getInstance().getSwitches().getBoolean("FishModifier.Enable"))
        {
            fishModifier = new Feature();
            fishModifier.setId("fishmodifier");
            fishModifier.setFriendlyName("FishModifier");
            fishModifier.setMessenger(new FishModifierMessenger());
            fishModifier.setConfig(new FishModifierConfig());
            fishModifier.setPermissionsConfig(new FishModifierPermissions());
            fishModifier.setListener(new FishModifierListener());
            fishModifier.setCommandRunner(new FishModifierCommand());
        }
    }

    public static Feature getItemFix() {
        return itemFix;
    }

    public static Feature getGetHead() {
        return getHead;
    }

    public static Feature getPullback() {
        return pullback;
    }

    public static Feature getAutoRespawn() {
        return autoRespawn;
    }

    public static Feature getFishModifier() {
        return fishModifier;
    }

    public static Feature getHealth() {
        return health;
    }

    public static Feature getFoodLevel() {
        return foodLevel;
    }
}
