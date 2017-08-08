package cc.moecraft.hykilpikonna.ult.fun.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.OreGenCalculations;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class OreGenConfig extends Config
{
    public OreGenConfig()
    {
        super(Fun.getInstance(), "Features", Features.getOreGenerator().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        for (String one : getStringList("EnabledWorlds")) OreGenCalculations.getEnabledWorlds().add(one);

        OreGenCalculations.setKeys(getItems());
        OreGenCalculations.setMaterialList(getMaterialList());
        OreGenCalculations.setChanceList(getChanceList());

        //排序
        sortChanceAndMaterial();

        ArrayList<Double> changedChanceList = new ArrayList<>();
        changedChanceList.addAll(OreGenCalculations.getChanceList());

        //转换为百分比分布
        double last = 0.0;
        for (int i = 0; i < changedChanceList.size(); i++)
        {
            changedChanceList.set(i, changedChanceList.get(i) + last);
            last = changedChanceList.get(i);
        }

        //判断是否总比例大于100%
        if (changedChanceList.get(changedChanceList.size() - 1) > 100.0)
        {
            return;
        }

        //得出乘数
        Double chanceDivideBy = 100.0;
        //这个Count是为了防止无限循环
        int count = 0;
        while (count < 100)
        {
            int rounded = (int) Math.round(changedChanceList.get(0));
            if (rounded == changedChanceList.get(0))
            {
                break;
            }
            else
            {
                count += 1;
                for (int i = 0; i < changedChanceList.size(); i++)
                {
                    changedChanceList.set(i, changedChanceList.get(i) * 10.0);
                }
                chanceDivideBy *= 10;
            }
        }

        ArrayList<Integer> intChance = new ArrayList<>();

        //四舍五入
        for (Double aChangedChanceList : changedChanceList) intChance.add((int) Math.round(aChangedChanceList));

        OreGenCalculations.setIntChance(intChance);
        OreGenCalculations.setChanceList(changedChanceList);
        OreGenCalculations.setChanceDivideBy(chanceDivideBy);
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        //是否用<Material>补充剩余的百分比
        addDefault("FillAllTheRest.Enable", true);
        addDefault("FillAllTheRest.Material", Material.COBBLESTONE.name());

        //世界
        ArrayList<String> worldNames = new ArrayList<>();
        for (World world : Bukkit.getServer().getWorlds()) worldNames.add(world.getName());
        addDefault("EnabledWorlds", worldNames);

        //生成物品列表
        addDefault("Items.CoalOre.MaterialID", Material.COAL_ORE.toString());
        addDefault("Items.CoalOre.ChanceInPercent", 1.5);
        addDefault("Items.RedstoneOre.MaterialID", Material.REDSTONE_ORE.toString());
        addDefault("Items.RedstoneOre.ChanceInPercent", 0.6);
        addDefault("Items.IronOre.MaterialID", Material.IRON_ORE.toString());
        addDefault("Items.IronOre.ChanceInPercent", 0.6);
        addDefault("Items.GoldOre.MaterialID", Material.GOLD_ORE.toString());
        addDefault("Items.GoldOre.ChanceInPercent", 0.4);
        addDefault("Items.LapisOre.MaterialID", Material.LAPIS_ORE.toString());
        addDefault("Items.LapisOre.ChanceInPercent", 0.3);
        addDefault("Items.EmeraldOre.MaterialID", Material.EMERALD_ORE.toString());
        addDefault("Items.EmeraldOre.ChanceInPercent", 0.2);
        addDefault("Items.DiamondOre.MaterialID", Material.DIAMOND_ORE.toString());
        addDefault("Items.DiamondOre.ChanceInPercent", 0.1);
        addDefault("Items.土.MaterialID", Material.DIRT.toString());
        addDefault("Items.土.ChanceInPercent", 0.08);
        addDefault("Items.玻璃.MaterialID", Material.GLASS.toString());
        addDefault("Items.玻璃.ChanceInPercent", 5);
    }

    private ArrayList<String> getItems()
    {
        ArrayList<String> output = new ArrayList<>();
        output.addAll(getConfigurationSection("Items").getKeys(false));
        return output;
    }

    private ArrayList<Material> getMaterialList()
    {
        ArrayList<Material> output = new ArrayList<>();
        for (String key : OreGenCalculations.getKeys())
        {
            output.add(Material.getMaterial(getString(String.format("Items.%s.MaterialID", key))));
        }
        if (getBoolean("FillAllTheRest.Enable"))
        {
            output.add(Material.getMaterial(getString("FillAllTheRest.Material")));
        }
        return output;
    }

    private ArrayList<Double> getChanceList()
    {
        ArrayList<Double> output = new ArrayList<>();
        for (String key : OreGenCalculations.getKeys())
        {
            output.add(getDouble(String.format("Items.%s.ChanceInPercent", key)));
        }
        if (getBoolean("FillAllTheRest.Enable"))
        {
            Double total = 99.0D;
            for (Double one : output)
            {
                total -= one;
            }
            output.add(total);
        }
        return output;
    }

    /**
     * 将几率和物品排序
     * 算法: 互换排序
     */
    private void sortChanceAndMaterial()
    {
        int currentMinIndex;
        for (int i = 0; i < OreGenCalculations.getChanceList().size() - 1; i++)
        {
            currentMinIndex = i;
            for (int j = i + 1; j < OreGenCalculations.getChanceList().size(); j++)
            {
                if(OreGenCalculations.getChanceList().get(j) < OreGenCalculations.getChanceList().get(currentMinIndex))
                {
                    currentMinIndex = j;
                }
            }

            //需要时互换
            if (i != currentMinIndex)
            {
                Double tempChance = OreGenCalculations.getChanceList().get(currentMinIndex);
                Material tempMaterial = OreGenCalculations.getMaterialList().get(currentMinIndex);
                OreGenCalculations.getChanceList().set(currentMinIndex, OreGenCalculations.getChanceList().get(i));
                OreGenCalculations.getMaterialList().set(currentMinIndex, OreGenCalculations.getMaterialList().get(i));
                OreGenCalculations.getChanceList().set(i, tempChance);
                OreGenCalculations.getMaterialList().set(i, tempMaterial);
            }
        }
    }
}
