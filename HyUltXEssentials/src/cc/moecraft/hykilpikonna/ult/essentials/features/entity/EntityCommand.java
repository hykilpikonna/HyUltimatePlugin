package cc.moecraft.hykilpikonna.ult.essentials.features.entity;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.essentials.HyUltXEssentials;
import cc.moecraft.hykilpikonna.ult.essentials.features.Features;
import cc.moecraft.hykilpikonna.ult.utils.PlaceholderUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/20 创建!
 * Created by Hykilpikonna on 2017/08/20!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class EntityCommand extends CommandRunner
{
    public EntityCommand()
    {
        super(HyUltXEssentials.getInstance(), Features.getEntityCommand().getId());
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (!Features.getEntityCommand().getPermissionsConfig().hasPermission(sender, "hyu.essentials.entity.command", true)) return;
        if (args.size() < 2)
        {
            Main.sendHelpMessage(sender);
            return;
        }

        String type = args.get(0);
        String entity = args.get(1);

        if (sender instanceof Player)
        {
            Location location = ((Player) sender).getLocation().clone();
            int amount = 1;
            boolean msg = true, glow = false;

            //Projectiles
            double dirx = 0, diry = 0, dirz = 0;
            boolean dirrel = true;

            //Arrow
            boolean critical = false, bounce = false, changeDamage = false;
            int fireTicks = 0, damage = 0;

            for (int i = 2; i < args.size(); i++)
            {
                String arg = PlaceholderUtils.calculateAndReplaceRandom(args.get(i));

                if (arg.contains("-x:")) try { location.setX(Double.parseDouble(arg.replace("-x:", ""))); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-y:")) try { location.setY(Double.parseDouble(arg.replace("-y:", ""))); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-z:")) try { location.setZ(Double.parseDouble(arg.replace("-z:", ""))); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-amount:")) try { amount = Integer.parseInt(arg.replace("-amount:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-glow:")) try { glow = Boolean.parseBoolean(arg.replace("-glow:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-msg:")) try { msg = Boolean.parseBoolean(arg.replace("-msg:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }

                //Projectiles
                else if (arg.contains("-dirx:")) try { dirx = Double.parseDouble(arg.replace("-dirx:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-diry:")) try { diry = Double.parseDouble(arg.replace("-diry:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-dirz:")) try { dirz = Double.parseDouble(arg.replace("-dirz:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-dirrel:")) try { dirrel = Boolean.parseBoolean(arg.replace("-dirrel:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }

                //Arrow
                else if (arg.contains("-critical:")) try { critical = Boolean.parseBoolean(arg.replace("-critical:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-bounce:")) try { bounce = Boolean.parseBoolean(arg.replace("-bounce:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-fireticks:")) try { fireTicks = Integer.parseInt(arg.replace("-fireticks:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
                else if (arg.contains("-damage:")) try { changeDamage = true; damage = Integer.parseInt(arg.replace("-damage:", "")); } catch (Exception ignored) { sendFailedToReadTagMessage(sender, arg); return; }
            }
            Vector direction;
            if (dirrel) direction = new Vector(dirx, diry, dirz).add(((Player) sender).getEyeLocation().getDirection().multiply(2.8));
            else direction = new Vector(dirx, diry, dirz);

            //临时
            int temoAmount = amount;

            switch (type)
            {
                case "common":
                    try
                    {
                        for (; temoAmount > 0; temoAmount--) CommonEntities.fromName(args.get(1)).spawn(((Player) sender).getWorld(), Bukkit.getServer(), location, glow);
                    }
                    catch (CommonEntities.CommonEntitiesException e)
                    {
                        Features.getEntityCommand().getMessenger().sendMessage(sender, "failed");
                        e.printStackTrace();
                        return;
                    }
                    catch (NullPointerException ignored)
                    {
                        sender.sendMessage(Features.getEntityCommand().getMessenger().getWithPrefix("error_entity_not_found").replace("%entity%", entity).replace("%class%", "Common"));
                        return;
                    }
                    if (msg) sender.sendMessage(Features.getEntityCommand().getMessenger().getWithPrefix("success").replace("%amount%", String.valueOf(amount)).replace("%entity%", entity));
                    break;
                case "custom":
                    CustomEntities entities = CustomEntities.fromName(entity);
                    if (entities == CustomEntities.ARROW)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            Arrow arrow = (Arrow) ((Player) sender).launchProjectile(Arrow.class, direction);
                            arrow.setCritical(critical);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.setTicksLived(100);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);
                            arrow.setPickupStatus(Arrow.PickupStatus.CREATIVE_ONLY);
                            if (changeDamage) arrow.spigot().setDamage(damage);
                        }
                    }
                    else if (entities == CustomEntities.FIREBALL)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            Fireball arrow = ((Player) sender).launchProjectile(Fireball.class, direction);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);

                        }
                    }
                    else if (entities == CustomEntities.DRAGON_FIREBALL)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            DragonFireball arrow = ((Player) sender).launchProjectile(DragonFireball.class, direction);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);
                        }
                    }
                    else if (entities == CustomEntities.SMALL_FIREBALL)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            SmallFireball arrow = ((Player) sender).launchProjectile(SmallFireball.class, direction);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);
                        }
                    }
                    else if (entities == CustomEntities.SNOWBALL)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            Snowball arrow = ((Player) sender).launchProjectile(Snowball.class, direction);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);
                        }
                    }
                    else if (entities == CustomEntities.EGG)
                    {
                        for (; temoAmount > 0; temoAmount--)
                        {
                            Egg arrow = ((Player) sender).launchProjectile(Egg.class, direction);
                            arrow.setFireTicks(fireTicks);
                            arrow.setBounce(bounce);
                            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.5f, 1f);
                        }
                    }
                    else
                    {
                        sender.sendMessage(Features.getEntityCommand().getMessenger().getWithPrefix("error_entity_not_found").replace("%entity%", entity).replace("%class%", "Custom"));
                        return;
                    }
                    if (msg) sender.sendMessage(Features.getEntityCommand().getMessenger().getWithPrefix("success").replace("%amount%", String.valueOf(amount)).replace("%entity%", entity));
                    break;
            }
        }
    }

    public void sendFailedToReadTagMessage(CommandSender player, String tag)
    {
        player.sendMessage(Features.getEntityCommand().getMessenger().getWithPrefix("failed_to_read_tag").replace("%TAG%", tag));
    }
}
