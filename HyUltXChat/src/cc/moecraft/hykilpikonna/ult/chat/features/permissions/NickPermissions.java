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
public class NickPermissions extends PermissionsConfig
{
    public NickPermissions()
    {
        super(Chat.getHyUltXChat(), Features.NICK.getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.nickname.show", false);
        
        add("hyult.command.nick.set.limit.bypass", true);
        add("hyult.command.nick.set.others", true);
        add("hyult.command.nick.set.self", true);
        add("hyult.command.nick.set.color", true);
        add("hyult.command.nick.see.self", true);
        add("hyult.command.nick.see.others", true);
    }
}
