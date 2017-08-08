package cc.moecraft.hykilpikonna.ult.fun.features.calculations;

import org.bukkit.Material;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class OreGenCalculations 
{
    private static  ArrayList<Double> chanceList;
    private static  ArrayList<Material> materialList;
    private static  ArrayList<String> keys;

    private static  ArrayList<String> enabledWorlds = new ArrayList<>();

    private static  double chanceDivideBy;
    private static  ArrayList<Integer> intChance;

    public static ArrayList<Double> getChanceList() 
    {
        return chanceList;
    }

    public static void setChanceList(ArrayList<Double> chanceList) 
    {
        OreGenCalculations.chanceList = chanceList;
    }

    public static ArrayList<Material> getMaterialList() 
    {
        return materialList;
    }

    public static void setMaterialList(ArrayList<Material> materialList) 
    {
        OreGenCalculations.materialList = materialList;
    }

    public static ArrayList<String> getKeys() 
    {
        return keys;
    }

    public static void setKeys(ArrayList<String> keys) 
    {
        OreGenCalculations.keys = keys;
    }

    public static ArrayList<String> getEnabledWorlds() 
    {
        return enabledWorlds;
    }

    public static void setEnabledWorlds(ArrayList<String> enabledWorlds) 
    {
        OreGenCalculations.enabledWorlds = enabledWorlds;
    }

    public static double getChanceDivideBy() 
    {
        return chanceDivideBy;
    }

    public static void setChanceDivideBy(double chanceDivideBy) 
    {
        OreGenCalculations.chanceDivideBy = chanceDivideBy;
    }

    public static ArrayList<Integer> getIntChance() 
    {
        return intChance;
    }

    public static void setIntChance(ArrayList<Integer> intChance) 
    {
        OreGenCalculations.intChance = intChance;
    }
}
