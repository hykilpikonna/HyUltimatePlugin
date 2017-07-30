package cc.moecraft.hykilpikonna.ult.chat.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.api.FeatureBuilder;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.commands.ClsCommand;
import cc.moecraft.hykilpikonna.ult.chat.features.commands.NickCommand;
import cc.moecraft.hykilpikonna.ult.chat.features.configs.ClsConfig;
import cc.moecraft.hykilpikonna.ult.chat.features.configs.NickConfig;
import cc.moecraft.hykilpikonna.ult.chat.features.databases.NickDatabase;
import cc.moecraft.hykilpikonna.ult.chat.features.listeners.NickListener;
import cc.moecraft.hykilpikonna.ult.chat.features.messengers.ClsMessenger;
import cc.moecraft.hykilpikonna.ult.chat.features.messengers.NickMessenger;
import cc.moecraft.hykilpikonna.ult.chat.features.permissions.ClsPermissions;
import cc.moecraft.hykilpikonna.ult.chat.features.permissions.NickPermissions;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    public static Feature CLS;
    public static Feature NICK;

    public Features()
    {
        tempDebug("执行了Features");
        if (Chat.getHyUltXChat().switches.getBoolean("Cls.Enable"))
        {
            CLS = new Feature();
            CLS.setId("cls");
            CLS.setFriendlyName("Cls");
            CLS.setPermissionsConfig(new ClsPermissions());
            CLS.setMessenger(new ClsMessenger());
            CLS.setConfig(new ClsConfig());
            CLS.setCommandRunner(new ClsCommand());
        }
        if (Chat.getHyUltXChat().switches.getBoolean("Nick.Enable"))
        {
            NICK = new Feature();
            NICK.setId("nick");
            NICK.setFriendlyName("Nick");
            NICK.setPermissionsConfig(new NickPermissions());
            NICK.setMessenger(new NickMessenger());
            NICK.setConfig(new NickConfig());
            NICK.setDatabase(new NickDatabase());
            NICK.setCommandRunner(new NickCommand());
            NICK.setListener(new NickListener());
        }
    }
}
