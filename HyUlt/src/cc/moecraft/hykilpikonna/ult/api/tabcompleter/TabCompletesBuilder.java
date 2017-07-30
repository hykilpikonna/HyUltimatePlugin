package cc.moecraft.hykilpikonna.ult.api.tabcompleter;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class TabCompletesBuilder
{
    private TabCompletes tabCompletes;

    /**
     * 新建一个指令格式的Tab补全建造器
     */
    public TabCompletesBuilder()
    {
        this.tabCompletes = new TabCompletes();
    }

    /**
     * 添加一条指令
     * 字符串数组类型
     * 数组里每一项的空格将被忽略
     *
     * @param command 指令
     * @return this
     */
    public TabCompletesBuilder append(ArrayList<String> command)
    {
        tabCompletes.add(command);
        return this;
    }

    /**
     * 添加一条指令
     * 字符串类型
     * 字符串里每个空格会被分解
     *
     * @param command 字符串指令
     * @return this
     */
    public TabCompletesBuilder append(String command)
    {
        tabCompletes.add(command);
        return this;
    }

    /**
     * 构建
     *
     * @return 指令格式的Tab补全
     */
    public TabCompletes build()
    {
        return tabCompletes;
    }
}
