package cc.moecraft.hykilpikonna.ult.command.features.commandmapping;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public enum CommandMappingProperties
{
    BYPASSABLE, SEND_MESSAGE, CUSTOM_MESSAGE, WORLD;

    @Override
    public String toString() {
        return name();
    }
}
