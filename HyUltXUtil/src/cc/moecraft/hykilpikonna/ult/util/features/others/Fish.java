package cc.moecraft.hykilpikonna.ult.util.features.others;

import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/11 创建!
 * Created by Hykilpikonna on 2017/08/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Fish
{
    //Types
    private ArrayList<FishType> fishTypes;

    //ITEM
    private ItemStack itemStack = null;

    //COMMAND
    private ArrayList<Command> commands = null;

    //MESSAGE
    private ArrayList<String> messages = null;

    public Fish(ArrayList<FishType> types)
    {
        fishTypes = types;
    }

    Fish() {}

    public ItemStack sendFishToPlayer(Player player)
    {
        if (fishTypes.contains(FishType.COMMAND))
            for (Command command : commands) command.sendCommand(player);
        if (fishTypes.contains(FishType.MESSAGE))
            for (String message : messages) player.sendMessage(message);
        if (fishTypes.contains(FishType.ITEM))
            return itemStack == null ? new ItemStack(Material.AIR) : itemStack;
        return null;
    }

    public ArrayList<String> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<String> messages)
    {
        this.messages = messages;
    }

    public ArrayList<FishType> getFishTypes()
    {
        return fishTypes;
    }

    public void setFishTypes(ArrayList<FishType> fishTypes)
    {
        this.fishTypes = fishTypes;
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }

    public ArrayList<Command> getCommands()
    {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands)
    {
        this.commands = commands;
    }
}
