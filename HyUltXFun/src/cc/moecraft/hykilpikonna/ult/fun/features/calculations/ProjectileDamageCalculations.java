package cc.moecraft.hykilpikonna.ult.fun.features.calculations;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ProjectileDamageCalculations
{
    private static ArrayList<String> staticEnabledWorldList = new ArrayList<>();

    public static ArrayList<String> getStaticEnabledWorldList()
    {
        return staticEnabledWorldList;
    }

    public static void setStaticEnabledWorldList(ArrayList<String> staticEnabledWorldList)
    {
        ProjectileDamageCalculations.staticEnabledWorldList = staticEnabledWorldList;
    }
}
