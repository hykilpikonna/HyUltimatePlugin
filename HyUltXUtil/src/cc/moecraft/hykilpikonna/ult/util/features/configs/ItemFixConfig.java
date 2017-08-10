package cc.moecraft.hykilpikonna.ult.util.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import cc.moecraft.hykilpikonna.ult.util.features.calculations.ItemFixCalculations;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ItemFixConfig extends Config
{
    public ItemFixConfig()
    {
        super(Util.getInstance(), "Features", Features.getItemFix().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        ItemFixCalculations.getFixItems().addAll(getStringList("ItemsToFix"));
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        ArrayList<String> fixItems = new ArrayList<>();

        fixItems.add(Material.ELYTRA.name());
        fixItems.add(Material.BOW.name());
        fixItems.add(Material.CARROT_STICK.name());
        fixItems.add(Material.SHIELD.name());
        fixItems.add(Material.FISHING_ROD.name());
        fixItems.add(Material.SHEARS.name());
        fixItems.add(Material.FLINT_AND_STEEL.name());

        fixItems.add(Material.IRON_SWORD.name());
        fixItems.add(Material.WOOD_SWORD.name());
        fixItems.add(Material.GOLD_SWORD.name());
        fixItems.add(Material.DIAMOND_SWORD.name());
        fixItems.add(Material.STONE_SWORD.name());

        fixItems.add(Material.IRON_PICKAXE.name());
        fixItems.add(Material.WOOD_PICKAXE.name());
        fixItems.add(Material.GOLD_PICKAXE.name());
        fixItems.add(Material.DIAMOND_PICKAXE.name());
        fixItems.add(Material.STONE_PICKAXE.name());

        fixItems.add(Material.IRON_AXE.name());
        fixItems.add(Material.WOOD_AXE.name());
        fixItems.add(Material.GOLD_AXE.name());
        fixItems.add(Material.DIAMOND_AXE.name());
        fixItems.add(Material.STONE_AXE.name());

        fixItems.add(Material.IRON_HOE.name());
        fixItems.add(Material.WOOD_HOE.name());
        fixItems.add(Material.GOLD_HOE.name());
        fixItems.add(Material.DIAMOND_HOE.name());
        fixItems.add(Material.STONE_HOE.name());

        fixItems.add(Material.IRON_SPADE.name());
        fixItems.add(Material.WOOD_SPADE.name());
        fixItems.add(Material.GOLD_SPADE.name());
        fixItems.add(Material.DIAMOND_SPADE.name());
        fixItems.add(Material.STONE_SPADE.name());

        fixItems.add(Material.IRON_HELMET.name());
        fixItems.add(Material.LEATHER_HELMET.name());
        fixItems.add(Material.GOLD_HELMET.name());
        fixItems.add(Material.DIAMOND_HELMET.name());

        fixItems.add(Material.IRON_CHESTPLATE.name());
        fixItems.add(Material.LEATHER_CHESTPLATE.name());
        fixItems.add(Material.GOLD_CHESTPLATE.name());
        fixItems.add(Material.DIAMOND_CHESTPLATE.name());

        fixItems.add(Material.IRON_LEGGINGS.name());
        fixItems.add(Material.LEATHER_LEGGINGS.name());
        fixItems.add(Material.GOLD_LEGGINGS.name());
        fixItems.add(Material.DIAMOND_LEGGINGS.name());

        fixItems.add(Material.IRON_BOOTS.name());
        fixItems.add(Material.LEATHER_BOOTS.name());
        fixItems.add(Material.GOLD_BOOTS.name());
        fixItems.add(Material.DIAMOND_BOOTS.name());

        addDefault("ItemsToFix", fixItems);
    }
}
