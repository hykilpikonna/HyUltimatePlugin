package cc.moecraft.hykilpikonna.ult;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import cc.moecraft.hykilpikonna.essentials.updater.UrlUpdater;
import static cc.moecraft.hykilpikonna.ult.Main.getMain;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Setup
{
    //加载监听器

    public static UrlUpdater urlUpdater;
    public static Logger loglogger;

    static void setup()
    {
        loglogger = new Logger("HyUlt", false);
        loglogger.log("[加载]此插件正在加载......");
        loglogger.setDebug(getMain().getConfig().getBoolean("Debug"));

        loglogger.log("[加载]正在运行Setup");
    }
}
