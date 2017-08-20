package cc.moecraft.hykilpikonna.ult.essentials.features.entity;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.essentials.HyUltXEssentials;
import cc.moecraft.hykilpikonna.ult.essentials.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/08/20 创建!
 * Created by Hykilpikonna on 2017/08/20!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class EntityPermissions extends PermissionsConfig
{
    public EntityPermissions()
    {
        super(HyUltXEssentials.getInstance(), Features.getEntityCommand().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyu.essentials.entity.command", true);
    }
}
