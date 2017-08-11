package cc.moecraft.hykilpikonna.ult.util.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import cc.moecraft.hykilpikonna.ult.util.features.calculations.FishModifierCalculations;
import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import cc.moecraft.hykilpikonna.ult.api.ingame.CommandType;
import cc.moecraft.hykilpikonna.ult.util.features.others.Fish;
import cc.moecraft.hykilpikonna.ult.util.features.others.FishBuilder;
import cc.moecraft.hykilpikonna.ult.util.features.others.FishType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.getWorldNameArrayList;
import static org.bukkit.ChatColor.BLUE;
import static cc.moecraft.hykilpikonna.ult.util.features.calculations.FishModifierCalculations.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierConfig extends Config
{
    public FishModifierConfig()
    {
        super(Util.getInstance(), "Features", Features.getFishModifier().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        staticEnabledWorldList.addAll(getStringList("EnabledWorlds"));

        keys = getKeys();
        fishList = getItems();
        chanceList = getChanceList();

        //排序
        sortChanceAndMaterial();

        ArrayList<Double> changedChanceList = new ArrayList<>();
        changedChanceList.addAll(chanceList);

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
            if (rounded == changedChanceList.get(0)) break;
            else
            {
                count += 1;
                for (int i = 0; i < changedChanceList.size(); i++) changedChanceList.set(i, changedChanceList.get(i) * 10.0);
                chanceDivideBy *= 10;
            }
        }

        ArrayList<Integer> intChance = new ArrayList<>();
        //四舍五入
        for (int i = 0; i < changedChanceList.size(); i++)
        {
            intChance.add((int) Math.round(changedChanceList.get(i)));
        }

        FishModifierCalculations.intChance = intChance;
        FishModifierCalculations.chanceList = changedChanceList;
        FishModifierCalculations.chanceDivideBy = chanceDivideBy;
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("FillAllTheRest.Enable", true);
        addDefault("FillAllTheRest.Types", new String[]{FishType.ITEM.name()});
        ItemStack defaultItemStack = itemStackGen(Material.RAW_FISH, 1, 0, null, new String[]{BLUE + "一条很普通...", BLUE + "很普通的小鱼..."}, false, null,null);
        addDefault("FillAllTheRest.ItemStack", defaultItemStack);

        ArrayList<String> worlds = getWorldNameArrayList();
        addDefault("EnabledWorlds", worlds);

        addDefault("Items.一对鱼.ChanceInPercent", 10);
        addDefault("Items.一对鱼.Types", new String[]{FishType.ITEM.name(), FishType.COMMAND.name(), FishType.MESSAGE.name()});
        addDefault("Items.一对鱼.ItemStack", itemStackGen(Material.RAW_FISH, 2, 2, null, new String[]{BLUE + "很普通...", BLUE + "很普通的一对小丑鱼..."}, false, null, null));
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new Command(CommandType.PLAYER, "/say 我钓上了一条鱼! (默认指令, 请联系服主更改配置!)", false).serialize());
        mapList.add(new Command(CommandType.CONSOLE, "say 啦啦啦 (默认后台指令, 请联系服主更改配置!)", false).serialize());
        addDefault("Items.一对鱼.Commands", mapList);
        addDefault("Items.一对鱼.Messages", new String[]{"你成功的钓上来一对鱼! (默认消息, 请联系服主更改配置!)"});

        addDefault("Items.击退鱼.ChanceInPercent", 5);
        addDefault("Items.击退鱼.Types", new String[]{FishType.ITEM.name()});
        addDefault("Items.击退鱼.ItemStack", itemStackGen(Material.RAW_FISH, 1, 1, null, new String[]{BLUE + "很普通...", BLUE + "很普通的一条击退鱼!"}, false, new Enchantment[]{Enchantment.KNOCKBACK}, new Integer[]{1}));

        addDefault("Items.熟鱼.ChanceInPercent", 5);
        addDefault("Items.熟鱼.Types", new String[]{FishType.ITEM.name()});
        addDefault("Items.熟鱼.ItemStack", itemStackGen(Material.COOKED_FISH, 1, 0, null, new String[]{BLUE + "很普通...", BLUE + "很普通的一条熟鱼!"}, false, null, null));

        addDefault("Items.附魔书a.ChanceInPercent", 5);
        addDefault("Items.附魔书a.Types", new String[]{FishType.ITEM.name()});
        addDefault("Items.附魔书a.ItemStack", itemStackGen(Material.ENCHANTED_BOOK, 1, 0, "我是一本普通的附魔书", null, false, new Enchantment[]{Enchantment.DURABILITY}, new Integer[]{1}));
    }

    /**
     * 将几率和物品排序
     * 算法: 互换排序
     */
    private void sortChanceAndMaterial()
    {
        int currentMinIndex;
        for (int i = 0; i < chanceList.size() - 1; i++)
        {
            currentMinIndex = i;
            for (int j = i + 1; j < chanceList.size(); j++)
            {
                if(chanceList.get(j) < chanceList.get(currentMinIndex))
                {
                    currentMinIndex = j;
                }
            }

            //需要时互换
            if (i != currentMinIndex)
            {
                Double tempChance = chanceList.get(currentMinIndex);
                Fish tempMaterial = fishList.get(currentMinIndex);
                chanceList.set(currentMinIndex, chanceList.get(i));
                fishList.set(currentMinIndex, fishList.get(i));
                chanceList.set(i, tempChance);
                fishList.set(i, tempMaterial);
            }
        }
    }

    private ArrayList<String> getKeys()
    {
        ArrayList<String> output = new ArrayList<>();
        output.addAll(getConfigurationSection("Items").getKeys(false));
        return output;
    }



    private ArrayList<Fish> getItems()
    {
        ArrayList<Fish> output = new ArrayList<>();
        for (String key : keys) output.add(getFish(String.format("Items.%s", key)));
        if (getBoolean("FillAllTheRest.Enable")) output.add(getFish("FillAllTheRest"));
        return output;
    }

    public Fish getFish(String path)
    {
        ArrayList<FishType> fishTypes = new ArrayList<>();
        for (String fishTypeInString : getStringList(path + ".Types")) fishTypes.add(FishType.valueOf(fishTypeInString));

        FishBuilder fishBuilder = new FishBuilder(fishTypes);
        if (fishTypes.contains(FishType.ITEM)) fishBuilder.setItemStack(getItemStack(path + ".ItemStack"));
        if (fishTypes.contains(FishType.MESSAGE)) fishBuilder.setMessages((ArrayList<String>) getStringList(path + ".Messages"));
        if (fishTypes.contains(FishType.COMMAND)) fishBuilder.setCommands(getCommandList(path + ".Commands"));
        return fishBuilder.build();
    }

    private ArrayList<Command> getCommandList(String path)
    {
        List<Map<?, ?>> mapList = getMapList(path);
        ArrayList<Command> output = new ArrayList<>();
        for (Map<?, ?> map : mapList) output.add(Command.deserializeWithUnknownMap(map));
        return output;
    }

    private ArrayList<Double> getChanceList()
    {
        ArrayList<Double> output = new ArrayList<>();
        for (String key : keys)
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
}
