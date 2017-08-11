package cc.moecraft.hykilpikonna.ult.util.features.others;

import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/11 创建!
 * Created by Hykilpikonna on 2017/08/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishBuilder
{
    private Fish fish;

    public FishBuilder()
    {
        fish = new Fish();
    }

    public FishBuilder(ArrayList<FishType> types)
    {
        fish = new Fish(types);
    }

    public FishBuilder setMessages(ArrayList<String> messages)
    {
        fish.setMessages(messages);
        return this;
    }

    public FishBuilder setFishTypes(ArrayList<FishType> fishTypes)
    {
        fish.setFishTypes(fishTypes);
        return this;
    }

    public FishBuilder setItemStack(ItemStack itemStack)
    {
        fish.setItemStack(itemStack);
        return this;
    }

    public FishBuilder setCommands(ArrayList<Command> commands)
    {
        fish.setCommands(commands);
        return this;
    }

    public FishBuilder addCommand(Command command)
    {
        fish.getCommands().add(command);
        return this;
    }

    public FishBuilder addMessage(String message)
    {
        fish.getMessages().add(message);
        return this;
    }

    public FishBuilder addFishType(FishType fishType)
    {
        fish.getFishTypes().add(fishType);
        return this;
    }

    public Fish build()
    {
        return fish;
    }
}
