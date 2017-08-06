package cc.moecraft.hykilpikonna.ult.fix.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import cc.moecraft.hykilpikonna.ult.fix.features.calculations.AsinpCalculations;
import cc.moecraft.hykilpikonna.ult.fix.features.listeners.AsinpListener;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpConfigs extends Config 
{
    public AsinpConfigs()
    {
        super(Fix.getInstance(), "Features", Features.getASINP().getFriendlyName());
    }

    @Override
    public void readConfig() 
    {
        for (String one : getStringList("Detection.PortalBlockFaceList"))
            try { AsinpListener.blockFaces.add(BlockFace.valueOf(one)); } catch (Exception ignored) {}
    }

    @Override
    public void writeConfig() 
    {
        Location defaultSpawn = AsinpCalculations.getEssentialsSpawn();

        addDefault("UseTimer", true);
        addDefault("TimerTimeOutInSeconds", 10);
        addDefault("TpToASpecificPointInsteadOfOffset", true);
        addDefault("SpecificPoint.UseEssentialsSpawn", false);
        addDefault("SpecificPoint.ChangeWorld", true);
        addDefault("SpecificPoint.World", defaultSpawn.getWorld().getName());
        addDefault("SpecificPoint.ChangeX", true);
        addDefault("SpecificPoint.X", defaultSpawn.getX());
        addDefault("SpecificPoint.ChangeY", true);
        addDefault("SpecificPoint.Y", defaultSpawn.getY());
        addDefault("SpecificPoint.ChangeZ", true);
        addDefault("SpecificPoint.Z", defaultSpawn.getZ());
        addDefault("SpecificPoint.ChangeYaw", false);
        addDefault("SpecificPoint.Yaw", defaultSpawn.getYaw());
        addDefault("SpecificPoint.ChangePitch", false);
        addDefault("SpecificPoint.Pitch", defaultSpawn.getPitch());
        addDefault("Offset.ChangeWorld", true);
        addDefault("Offset.World", defaultSpawn.getWorld().getName());
        addDefault("Offset.ChangeX", true);
        addDefault("Offset.X", 3);
        addDefault("Offset.ChangeY", true);
        addDefault("Offset.Y", 5);
        addDefault("Offset.ChangeZ", true);
        addDefault("Offset.Z", 3);
        addDefault("Offset.ChangeYaw", false);
        addDefault("Offset.Yaw", 0);
        addDefault("Offset.ChangePitch", false);
        addDefault("Offset.Pitch", 0);
    }

    @Override
    public void writeDefaultConfig() 
    {
        ArrayList<String> blockFaceList = new ArrayList<>();
        blockFaceList.add(BlockFace.SELF.name());
        blockFaceList.add(BlockFace.UP.name());
        blockFaceList.add(BlockFace.NORTH.name());
        blockFaceList.add(BlockFace.EAST.name());
        blockFaceList.add(BlockFace.WEST.name());
        blockFaceList.add(BlockFace.SOUTH.name());
        addDefault("Detection.PortalBlockFaceList", blockFaceList);
    }

    public void setConfigLocation(World world, Double x, Double y, Double z, Float yaw, Float pitch)
    {
        set("SpecificPoint.UseEssentialsSpawn", false);
        set("SpecificPoint.ChangeWorld", true);
        set("SpecificPoint.World", world.getName());
        set("SpecificPoint.ChangeX", true);
        set("SpecificPoint.X", x);
        set("SpecificPoint.ChangeY", true);
        set("SpecificPoint.Y", y);
        set("SpecificPoint.ChangeZ", true);
        set("SpecificPoint.Z", z);
        set("SpecificPoint.ChangeYaw", true);
        set("SpecificPoint.Yaw", yaw);
        set("SpecificPoint.ChangePitch", true);
        set("SpecificPoint.Pitch", pitch);
        save();
    }
}
