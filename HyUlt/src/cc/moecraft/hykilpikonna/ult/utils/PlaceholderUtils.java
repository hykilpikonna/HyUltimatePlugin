package cc.moecraft.hykilpikonna.ult.utils;

import cc.moecraft.hykilpikonna.ult.HyPluginsDownloadLink;
import com.sun.deploy.util.ArrayUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/07/08 创建!
 * Created by Hykilpikonna on 2017/07/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PlaceholderUtils
{
    public static ArrayList<String> replaceTabPlaceholders(ArrayList<String> string)
    {
        for (int i = 0; i < string.size(); i++)
        {
            String oneString = string.get(i);
            if (oneString.contains("%ONLINE_PLAYERS%")) replaceOnlinePlayers(oneString, string, i);
            if (oneString.contains("%HYPLUGINS%")) replaceHyPlugins(oneString, string, i);
        }
        return string;
    }

    private static void replaceOnlinePlayers(String oneString, ArrayList<String> string, int i)
    {
        string.remove(i);
        ArrayList<String> frontAndBack = ArrayUtils.stringArrayToArrayList(oneString.split("%ONLINE_PLAYERS%"));
        if (frontAndBack.size() < 2)
        {
            frontAndBack.add("");
            frontAndBack.add("");
        }
        for (Player player : Bukkit.getOnlinePlayers())
        {
            string.add(i, ChatColor.YELLOW + frontAndBack.get(0) + player.getName() + frontAndBack.get(1) + ChatColor.RESET);
        }
    }

    private static void replaceHyPlugins(String oneString, ArrayList<String> string, int i)
    {
        //TODO: Finish This
        string.remove(i);
        ArrayList<String> frontAndBack = ArrayUtils.stringArrayToArrayList(oneString.split("%HYPLUGINS%"));
        if (frontAndBack.size() < 2)
        {
            frontAndBack.add("");
            frontAndBack.add("");
        }
        for (String name : HyPluginsDownloadLink.getNameList())
        {
            string.add(i, ChatColor.YELLOW + frontAndBack.get(0) + name + frontAndBack.get(1) + ChatColor.RESET);
        }
    }
}
