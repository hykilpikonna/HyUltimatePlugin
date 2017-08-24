package cc.moecraft.hykilpikonna.ult.command.features.commandloop;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.ingame.PlayerCommand;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/18 创建!
 * Created by Hykilpikonna on 2017/08/18!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandLoopCommand extends CommandRunner
{
    public CommandLoopCommand()
    {
        super(HyUltXCommand.getInstance(), "hyc");
    }

    @Override
    public void runCommand(CommandSender sender, org.bukkit.command.Command command, String alias, ArrayList<String> args)
    {
        if (args.size() <= 1)
        {
            Main.sendHelpMessage(sender);
            return;
        }
        if (!(args.get(0).equals("loop"))) return;
        switch (args.get(1))
        {
            case "start":
                if (!(sender instanceof Player))
                {
                    Features.getCommandLoop().getMessenger().sendMessage(sender, "console_not_allowed");
                    return;
                }
                if (!Features.getCommandLoop().getPermissionsConfig().hasPermission(sender, "hyc.loop.start", true)) return;

                boolean async = false;
                int delay = 0, period = 40, time = 400;
                ArrayList<PlayerCommand> commands = new ArrayList<>();
                ArrayList<PlayerCommand> startCommands = new ArrayList<>();
                ArrayList<PlayerCommand> endCommands = new ArrayList<>();

                for (int i = 0; i < args.size(); i++)
                {
                    String arg = args.get(i);
                    if (arg.contains("-delay:")) try { delay = Integer.parseInt(arg.replace("-delay:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    else if (arg.contains("-async:")) try { async = Boolean.parseBoolean(arg.replace("-async:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    else if (arg.contains("-period:")) try { period = Integer.parseInt(arg.replace("-period:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    else if (arg.contains("-time:")) try { time = Integer.parseInt(arg.replace("-time:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    else if (arg.contains("-command:{"))
                    {
                        if (arg.contains("}")) commands.add(new PlayerCommand((Player) sender, arg.replace("-command:{", "").replace("}", "")));
                        else try
                        {
                            String oneCommand = arg.replace("-command:{", "");
                            i++;
                            arg = args.get(i);
                            while (!(arg.contains("}")))
                            {
                                oneCommand += " " + arg;

                                i++;
                                arg = args.get(i);
                            }
                            oneCommand += " " + arg.replace("}", "");
                            commands.add(new PlayerCommand((Player) sender, oneCommand));
                        } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    }
                    else if (arg.contains("-startcommand:{"))
                    {
                        if (arg.contains("}")) startCommands.add(new PlayerCommand((Player) sender, arg.replace("-startcommand:{", "").replace("}", "")));
                        else try
                        {
                            String oneCommand = arg.replace("-startcommand:{", "");
                            i++;
                            arg = args.get(i);
                            while (!(arg.contains("}")))
                            {
                                oneCommand += " " + arg;

                                i++;
                                arg = args.get(i);
                            }
                            oneCommand += " " + arg.replace("}", "");
                            startCommands.add(new PlayerCommand((Player) sender, oneCommand));
                        } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    }
                    else if (arg.contains("-endcommand:{"))
                    {
                        if (arg.contains("}")) endCommands.add(new PlayerCommand((Player) sender, arg.replace("-endcommand:{", "").replace("}", "")));
                        else try
                        {
                            String oneCommand = arg.replace("-endcommand:{", "");
                            i++;
                            arg = args.get(i);
                            while (!(arg.contains("}")))
                            {
                                oneCommand += " " + arg;

                                i++;
                                arg = args.get(i);
                            }
                            oneCommand += " " + arg.replace("}", "");
                            endCommands.add(new PlayerCommand((Player) sender, oneCommand));
                        } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                    }
                }

                CommandLoopCalculations.scheduleRunnable((Player) sender, startCommands, commands, endCommands, delay, period, time, async);
                break;
        }
    }

    public void sendFailedToReadTagMessage(CommandSender player, String tag)
    {
        player.sendMessage(Features.getCommandLoop().getMessenger().getWithPrefix("failed_to_read_tag").replace("%TAG%", tag));
    }
}
