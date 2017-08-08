package cc.moecraft.hykilpikonna.ult.fun.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.ElevatorCalculations;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static org.bukkit.ChatColor.GREEN;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ElevatorConfig extends Config
{
    public ElevatorConfig()
    {
        super(Fun.getInstance(), "Features", Features.getElevator().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        ElevatorCalculations.getBlock1ExceptionList().addAll(getStringList("Detection.BlockOneException"));
        ElevatorCalculations.getBlock2ExceptionList().addAll(getStringList("Detection.BlockTwoException"));

        ArrayList<String> keys = getEnabledBlocks();
        for (String key : keys)
        {
            ElevatorCalculations.getMaterialList().add(Material.getMaterial(getString(String.format("EnabledBlocks.%s.MaterialID", key))));
            ElevatorCalculations.getPermNodeList().add(getString(String.format("EnabledBlocks.%s.Permission.Node", key)));
            ElevatorCalculations.getPermRequireList().add(getBoolean(String.format("EnabledBlocks.%s.Permission.Require", key)));
            ElevatorCalculations.getMaxHeightList().add(getInt(String.format("EnabledBlocks.%s.MaxHeight", key)));
            ElevatorCalculations.getMinHeightList().add(getInt(String.format("EnabledBlocks.%s.MinHeight", key)));
            ElevatorCalculations.getMaxYList().add(getInt(String.format("EnabledBlocks.%s.MaxY", key)));
            ElevatorCalculations.getMinYList().add(getInt(String.format("EnabledBlocks.%s.MinY", key)));
            ElevatorCalculations.getSendMessageList().add(getBoolean(String.format("EnabledBlocks.%s.SendMessage", key)));
            ElevatorCalculations.getMessageUpList().add(getString(String.format("EnabledBlocks.%s.MessageUp", key)));
            ElevatorCalculations.getMessageDownList().add(getString(String.format("EnabledBlocks.%s.MessageDown", key)));
            try
            {
                ElevatorCalculations.getSoundList().add(Sound.valueOf(getString(String.format("EnabledBlocks.%s.Sound", key))));
            }
            catch (Exception ignored) {}

            ArrayList<String> temp = new ArrayList<>();
            temp.addAll(getStringList(String.format("EnabledBlocks.%s.EnabledWorlds", key)));
            ElevatorCalculations.getEnabledWorlds().add(temp);
        }
    }

    private ArrayList<String> getEnabledBlocks()
    {
        ArrayList<String> output = new ArrayList<>();
        output.addAll(getConfigurationSection("EnabledBlocks").getKeys(false));
        return output;
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        //生成物品列表
        addDefault("EnabledBlocks.IronBlock.MaterialID", Material.IRON_BLOCK.toString());
        addDefault("EnabledBlocks.IronBlock.Permission.Required", false);
        addDefault("EnabledBlocks.IronBlock.Permission.Node", "hyult.elevator.ironblock");
        addDefault("EnabledBlocks.IronBlock.MaxHeight", 100);
        addDefault("EnabledBlocks.IronBlock.MinHeight", 3);
        addDefault("EnabledBlocks.IronBlock.MaxY", 255);
        addDefault("EnabledBlocks.IronBlock.MinY", 1);
        addDefault("EnabledBlocks.IronBlock.Sound", "ENTITY_ENDERMEN_TELEPORT");
        addDefault("EnabledBlocks.IronBlock.SendMessage", true);
        addDefault("EnabledBlocks.IronBlock.MessageUp", GREEN + "已将您拉到上一层");
        addDefault("EnabledBlocks.IronBlock.MessageDown", GREEN + "已将您拉到下一层");
        //世界
        ArrayList<String> worldNames = new ArrayList<>();
        for (World world : Bukkit.getServer().getWorlds()) worldNames.add(world.getName());
        addDefault("EnabledBlocks.IronBlock.EnabledWorlds", worldNames);

        //生成物品列表
        addDefault("EnabledBlocks.GoldBlock.MaterialID", Material.GOLD_BLOCK.toString());
        addDefault("EnabledBlocks.GoldBlock.Permission.Required", false);
        addDefault("EnabledBlocks.GoldBlock.Permission.Node", "hyult.elevator.goldblock");
        addDefault("EnabledBlocks.GoldBlock.MaxHeight", 150);
        addDefault("EnabledBlocks.GoldBlock.MinHeight", 3);
        addDefault("EnabledBlocks.GoldBlock.MaxY", 255);
        addDefault("EnabledBlocks.GoldBlock.MinY", 1);
        addDefault("EnabledBlocks.GoldBlock.Sound", "ENTITY_ENDERMEN_TELEPORT");
        addDefault("EnabledBlocks.GoldBlock.SendMessage", true);
        addDefault("EnabledBlocks.GoldBlock.MessageUp", GREEN + "已将您拉到上一层");
        addDefault("EnabledBlocks.GoldBlock.MessageDown", GREEN + "已将您拉到下一层");
        addDefault("EnabledBlocks.GoldBlock.EnabledWorlds", worldNames);

        addDefault("Detection.BlockOneException", new String[]{Material.AIR.name(), Material.CARPET.name()});
        addDefault("Detection.BlockTwoException", new String[]{Material.AIR.name()});
    }
}
