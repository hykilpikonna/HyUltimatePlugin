package cc.moecraft.hykilpikonna.ult.api.ingame;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.utils.PlaceholderUtils;
import org.bukkit.entity.Player;

/**
 * 此类由 Hykilpikonna 在 2017/08/18 创建!
 * Created by Hykilpikonna on 2017/08/18!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PlayerCommand
{
    private Player player;
    private String command;

    public PlayerCommand(Player player, String command)
    {
        this.player = player;
        this.command = command;
    }

    public void sendCommands()
    {
        player.chat(PlaceholderUtils.calculateAndReplaceRandom(command));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
