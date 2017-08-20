package cc.moecraft.hykilpikonna.ult.api.tabcompleter;

import cc.moecraft.hykilpikonna.ult.utils.PlaceholderUtils;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.stringArrayToArrayList;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.removeSpace;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class TabCompletes
{
    ArrayList<ArrayList<String>> commandMap;

    int greatestSize = 0;

    /**
     * 新建一个指令格式的Tab自动补全
     */
    public TabCompletes()
    {
        commandMap = new ArrayList<>();
    }

    /**
     * 新建一个指令格式的Tab自动补全
     */
    public TabCompletes(ArrayList<ArrayList<String>> commandMap)
    {
        this.commandMap = commandMap;
    }

    /**
     * 添加一条字符串数组格式指令
     *
     * @param command 字符串数组指令
     */
    public void add(ArrayList<String> command)
    {
        greatestSize = greatestSize > command.size() ? greatestSize : command.size();
        commandMap.add(removeSpace(command));
    }

    /**
     * 添加一条字符串指令
     * 空格会自动分解
     *
     * @param command 字符串指令
     */
    public void add(String command)
    {
        String[] commands = command.split(" ");
        greatestSize = greatestSize > commands.length ? greatestSize : commands.length;
        ArrayList<String> commandsAL = stringArrayToArrayList(commands);
        commandMap.add(commandsAL);
    }

    public ArrayList<String> get(String[] args)
    {
        if (greatestSize < args.length) return new ArrayList<>();
        return filterOutAllNonMatching(commandMap, stringArrayToArrayList(args));
    }

    private int count = 0;

    /**
     * 递归过滤掉所有不符合标准的指令
     * 例子:
     * strings:
     * - ArrayList 1:
     *    ["hp", "%INT%"]
     * - ArrayList 2:
     *    ["kill", "%PLAYER%"]
     * - ArrayList 3:
     *    ["kill", "entity", "%ALL_ENTITY%"]
     * - ArrayList 4:
     *    ["say", "%NOT_NULL%"]
     *
     * command:
     * ["kill"]
     *
     * 返回:
     * ["%PLAYER%", "entity"]
     *
     * @param strings 补全的指令表
     * @param command 玩家输入的指令
     * @return 过滤后的指令
     */
    public ArrayList<String> filterOutAllNonMatching(ArrayList<ArrayList<String>> strings, ArrayList<String> command)
    {
        //递归结束器
        if (command.size() == 0)
        {
            count = 0;
            return PlaceholderUtils.replaceTabPlaceholders(getOneRow(0, strings));
        }
        //获取一个arg并从数组删除
        String thisCommand = command.get(0);
        command.remove(0);
        //新建输出, 输出将会是下次递归的strings
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++)
        {
            ArrayList<String> oneString = new ArrayList<>();
            oneString.addAll(strings.get(i));
            if (oneString.size() > 0)
            {
                if (oneString.get(0).contains(thisCommand))
                {
                    if (count >  0) oneString.remove(0);
                    output.add(oneString);
                }
            }
        }
        //递归
        count += 1;
        return filterOutAllNonMatching(output, command);
    }

    /**
     * 获取双层数组中的一行
     * 例子:
     * 0
     * - ArrayList1
     *     ["第一个一", "第二个一"]
     * - ArrayList2
     *     ["第一个二"]
     * - ArrayList3
     * - ArrayList4
     *     ["第一个四", "第二个四", "第三个四"]
     *
     * 返回:
     * - 第一个一
     * - 第一个二
     * - 第一个四
     *
     * @param index 行
     * @param strings 数组
     * @return 一行
     */
    public ArrayList<String> getOneRow(int index, ArrayList<ArrayList<String>> strings)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++)
        {
            if (strings.get(i).size() > index) output.add(strings.get(i).get(index));
        }
        return output;
    }
}
