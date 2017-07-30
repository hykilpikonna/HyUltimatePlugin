package cc.moecraft.hykilpikonna.ult.chat.features.permissions;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ClsPermissions extends PermissionsConfig 
{
    public ClsPermissions() 
    {
        super(Chat.getHyUltXChat(), Features.CLS.getFriendlyName());
    }

    @Override
    public void writeConfig() 
    {
        add("hyult.chat.cls.recieve", false);

        add("hyult.command.cls.self", false);
        add("hyult.command.cls.all", true);
        add("hyult.command.cls.others", true);
    }
}
