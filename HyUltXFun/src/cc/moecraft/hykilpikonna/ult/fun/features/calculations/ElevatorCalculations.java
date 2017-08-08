package cc.moecraft.hykilpikonna.ult.fun.features.calculations;

import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ElevatorCalculations 
{
    private static ArrayList<String> permNodeList = new ArrayList<>();
    private static ArrayList<Boolean> permRequireList = new ArrayList<>();
    private static ArrayList<Material> materialList = new ArrayList<>();
    private static ArrayList<Integer> maxHeightList = new ArrayList<>();
    private static ArrayList<Integer> minHeightList = new ArrayList<>();
    private static ArrayList<Integer> maxYList = new ArrayList<>();
    private static ArrayList<Integer> minYList = new ArrayList<>();
    private static ArrayList<Boolean> sendMessageList = new ArrayList<>();
    private static ArrayList<String> messageUpList = new ArrayList<>();
    private static ArrayList<String> messageDownList = new ArrayList<>();
    private static ArrayList<Sound> soundList = new ArrayList<>();

    private static ArrayList<String> block1ExceptionList = new ArrayList<>();
    private static ArrayList<String> block2ExceptionList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> enabledWorlds = new ArrayList<>();

    public static ArrayList<String> getPermNodeList()
    {
        return permNodeList;
    }

    public static void setPermNodeList(ArrayList<String> permNodeList)
    {
        ElevatorCalculations.permNodeList = permNodeList;
    }

    public static ArrayList<Boolean> getPermRequireList()
    {
        return permRequireList;
    }

    public static void setPermRequireList(ArrayList<Boolean> permRequireList)
    {
        ElevatorCalculations.permRequireList = permRequireList;
    }

    public static ArrayList<Material> getMaterialList()
    {
        return materialList;
    }

    public static void setMaterialList(ArrayList<Material> materialList)
    {
        ElevatorCalculations.materialList = materialList;
    }

    public static ArrayList<Integer> getMaxHeightList()
    {
        return maxHeightList;
    }

    public static void setMaxHeightList(ArrayList<Integer> maxHeightList)
    {
        ElevatorCalculations.maxHeightList = maxHeightList;
    }

    public static ArrayList<Integer> getMinHeightList()
    {
        return minHeightList;
    }

    public static void setMinHeightList(ArrayList<Integer> minHeightList)
    {
        ElevatorCalculations.minHeightList = minHeightList;
    }

    public static ArrayList<Integer> getMaxYList()
    {
        return maxYList;
    }

    public static void setMaxYList(ArrayList<Integer> maxYList)
    {
        ElevatorCalculations.maxYList = maxYList;
    }

    public static ArrayList<Integer> getMinYList()
    {
        return minYList;
    }

    public static void setMinYList(ArrayList<Integer> minYList)
    {
        ElevatorCalculations.minYList = minYList;
    }

    public static ArrayList<Boolean> getSendMessageList()
    {
        return sendMessageList;
    }

    public static void setSendMessageList(ArrayList<Boolean> sendMessageList)
    {
        ElevatorCalculations.sendMessageList = sendMessageList;
    }

    public static ArrayList<String> getMessageUpList()
    {
        return messageUpList;
    }

    public static void setMessageUpList(ArrayList<String> messageUpList)
    {
        ElevatorCalculations.messageUpList = messageUpList;
    }

    public static ArrayList<String> getMessageDownList()
    {
        return messageDownList;
    }

    public static void setMessageDownList(ArrayList<String> messageDownList)
    {
        ElevatorCalculations.messageDownList = messageDownList;
    }

    public static ArrayList<Sound> getSoundList()
    {
        return soundList;
    }

    public static void setSoundList(ArrayList<Sound> soundList)
    {
        ElevatorCalculations.soundList = soundList;
    }

    public static ArrayList<String> getBlock1ExceptionList()
    {
        return block1ExceptionList;
    }

    public static void setBlock1ExceptionList(ArrayList<String> block1ExceptionList)
    {
        ElevatorCalculations.block1ExceptionList = block1ExceptionList;
    }

    public static ArrayList<String> getBlock2ExceptionList()
    {
        return block2ExceptionList;
    }

    public static void setBlock2ExceptionList(ArrayList<String> block2ExceptionList)
    {
        ElevatorCalculations.block2ExceptionList = block2ExceptionList;
    }

    public static ArrayList<ArrayList<String>> getEnabledWorlds()
    {
        return enabledWorlds;
    }

    public static void setEnabledWorlds(ArrayList<ArrayList<String>> enabledWorlds)
    {
        ElevatorCalculations.enabledWorlds = enabledWorlds;
    }
}
