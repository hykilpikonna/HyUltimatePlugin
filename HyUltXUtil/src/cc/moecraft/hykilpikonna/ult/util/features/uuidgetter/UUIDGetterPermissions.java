package cc.moecraft.hykilpikonna.ult.util.features.uuidgetter;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/08/25 创建!
 * Created by Hykilpikonna on 2017/08/25!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class UUIDGetterPermissions extends PermissionsConfig
{
    public UUIDGetterPermissions()
    {
        super(Util.getInstance(), Features.getUuidGetter().getFriendlyName());
    }

    @Override
    public void writeConfig() {
        add("hyult.util.uuidgetter.get", true);
    }
}
