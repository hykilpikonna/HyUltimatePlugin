package cc.moecraft.hykilpikonna.ult.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static cc.moecraft.hykilpikonna.ult.Main.getMain;
import static cc.moecraft.hykilpikonna.ult.Setup.loglogger;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.removeSpace;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ArrayUtils
{
    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static ArrayList<String> getTheRest(String[] strings, int index)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = index; i < strings.length; i++)
        {
            output.add(strings[i]);
        }
        return output;
    }
    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static ArrayList<String> getTheRest(ArrayList<String> strings, int index)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = index; i < strings.size(); i++)
        {
            output.add(strings.get(i));
        }
        return output;
    }

    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(String[] strings, int index)
    {
        StringBuilder output = new StringBuilder();
        for (int i = index; i < strings.length; i++)
        {
            output.append((i == strings.length - 1) ? strings[i] : strings[i] + " ");
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output + ";");
        return output.toString();
    }

    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(ArrayList<String> strings, int index)
    {
        StringBuilder output = new StringBuilder();
        for (int i = index; i < strings.size(); i++)
        {
            output.append((i == strings.size() - 1) ? strings.get(i) : strings.get(i) + " ");
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output);
        return output.toString();
    }

    /**
     * 获取剩下的
     * @param string String
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(String string, int index)
    {
        StringBuilder output = new StringBuilder();
        for (int i = index; i < string.length(); i++)
        {
            output.append(string.charAt(i));
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output);
        return output.toString();
    }

    /**
     * 把Lore转换成技能表
     * @param lore Lore
     * @return 技能表
     */
    public static ArrayList<String> readLore(ArrayList<String> lore)
    {
        if (lore == null)
        {
            loglogger.Debug("[Lore]Lore是Null");
            return null;
        }

        loglogger.Debug("[Lore]正在把Lore转换为技能列表");
        ArrayList<String> list = new ArrayList<>();
        String prefix = removeSpace(removeColorCode(getMain().getConfig().getString("Lore.Prefix")));
        for (int i = 0; i < lore.size(); i++)
        {
            String tempString = removeSpace(removeColorCode(lore.get(i)));
            if (tempString.contains(prefix))
            {
                list.add(tempString.replace(prefix, ""));
                loglogger.Debug(String.format("[Lore]行数:%s, 值:%s", i, list.get(i)));
            }
            else
            {
                loglogger.Debug(String.format("[Lore]未通过验证: Prefix = %s, Lore = %s", prefix, tempString));
            }
        }
        return list;
    }

    public static ArrayList<String> removeFirst(ArrayList<String> original, String toRemove)
    {
        //ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < original.size(); i++)
        {
            if (original.get(i).equals(toRemove))
            {
                original.remove(i);
                return original;
            }
        }
        return original;
    }

    public static String arrayListToString(ArrayList<String> arrayList)
    {
        String output = "[";
        for (int i = 0; i < arrayList.size(); i++)
        {
            output += (i == arrayList.size() - 1) ? arrayList.get(i) + "]" : arrayList.get(i) + ", ";
        }
        return output;
    }

    public static String doubleArrayListToString(ArrayList<Double> arrayList)
    {
        String output = "[";
        for (int i = 0; i < arrayList.size(); i++)
        {
            output += (i == arrayList.size() - 1) ? arrayList.get(i) + "]" : arrayList.get(i) + ", ";
        }
        return output;
    }

    public static String intArrayListToString(ArrayList<Integer> arrayList)
    {
        String output = "[";
        for (int i = 0; i < arrayList.size(); i++)
        {
            output += (i == arrayList.size() - 1) ? arrayList.get(i) + "]" : arrayList.get(i) + ", ";
        }
        return output;
    }

    public static String materialArrayListToString(ArrayList<Material> arrayList)
    {
        String output = "[";
        for (int i = 0; i < arrayList.size(); i++)
        {
            output += (i == arrayList.size() - 1) ? arrayList.get(i).name() + "]" : arrayList.get(i).name() + ", ";
        }
        return output;
    }

    public static ArrayList<String> getWorldNameArrayList()
    {
        ArrayList<String> out = new ArrayList<>();
        for (World oneWorld : Bukkit.getWorlds())
        {
            out.add(oneWorld.getName());
        }
        return out;
    }

    public static ArrayList<String> stringArrayToArrayList(String[] stringArray)
    {
        ArrayList<String> output= new ArrayList<>();
        Collections.addAll(output, stringArray);
        return output;
    }

    public static ArrayList<String> removeDuplicates(ArrayList<String> original)
    {
        if (original == null || original.size() == 0) return new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        hs.addAll(original);
        output.addAll(hs);
        return output;
    }
}
