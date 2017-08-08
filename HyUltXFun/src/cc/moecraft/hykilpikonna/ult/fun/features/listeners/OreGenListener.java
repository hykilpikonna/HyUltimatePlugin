package cc.moecraft.hykilpikonna.ult.fun.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.OreGenCalculations;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

import static org.bukkit.Material.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class OreGenListener extends Listener
{
    public OreGenListener()
    {
        super(Fun.getInstance());
    }

    @EventHandler
    public void onEvent(BlockFromToEvent event)
    {
        Block block = event.getBlock();
        Material material = block.getType();
        if (!(material.equals(Material.LAVA) || material.equals(Material.WATER) || material.equals(Material.STATIONARY_LAVA) || material.equals(Material.STATIONARY_WATER))) return;
        Block toBlock = event.getToBlock();
        Material toBlockMaterial = toBlock.getType();
        if (toBlockMaterial.equals(Material.AIR) && generatesCobble(material, toBlock))
        {
            //判断世界
            if (OreGenCalculations.getEnabledWorlds().contains(block.getLocation().getWorld().getName()))
            {
                //随机
                Random t = new Random();
                int randomNumber = new Random().nextInt((int) Math.round(OreGenCalculations.getChanceDivideBy()));

                for (int i = 0; i < OreGenCalculations.getIntChance().size(); i++)
                {
                    if (OreGenCalculations.getIntChance().get(i) > randomNumber)
                    {
                        toBlock.setType(OreGenCalculations.getMaterialList().get(i));
                        return;
                    }
                }
            }
        }
    }

    private final BlockFace[] faces = { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

    private boolean generatesCobble(Material material, Block block)
    {
        Material mirrorMaterial = material.equals(WATER) || material.equals(STATIONARY_WATER) ? LAVA : WATER;
        Material mirrorMaterialStationary = mirrorMaterial.equals(LAVA) ? STATIONARY_LAVA : STATIONARY_WATER;

        for (BlockFace face : faces)
        {
            Block r = block.getRelative(face, 1);
            if (r.getType().equals(mirrorMaterial) || r.getType().equals(mirrorMaterialStationary))
            {
                return true;
            }
        }
        return false;
    }
}
