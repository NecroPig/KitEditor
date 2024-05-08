package ru.matt.kiteditor.listener;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ru.matt.kiteditor.menus.Other;
import ru.matt.kiteditor.menus.PVP;
import ru.matt.kiteditor.menus.Utils;

import java.io.File;

/* Листенер для клика по блоку
   Никто бы не догадался, да?
   @author: deadlyPig
 */

public class OnClickedBlock implements Listener {
    private JavaPlugin plugin;
    private PVP pvpmenu;
    private Utils utilsmenu;
    private Other othermenu;
    private int blockXPVP;
    private int blockYPVP;
    private int blockZPVP;
    private int blockXUtils;
    private int blockYUtils;
    private int blockZUtils;
    private int blockXOther;
    private int blockYOther;
    private int blockZOther;

    public OnClickedBlock(JavaPlugin plugin, PVP pvpmenu) {
        this.plugin = plugin;
        this.pvpmenu = pvpmenu;
        loadBlockCoordinates();
    }
    public OnClickedBlock(JavaPlugin plugin, Utils utilsmenu) {
        this.plugin = plugin;
        this.utilsmenu = utilsmenu;
        loadBlockCoordinates();
    }
    public OnClickedBlock(JavaPlugin plugin, Other othermenu) {
        this.plugin = plugin;
        this.othermenu = othermenu;
        loadBlockCoordinates();
    }

    private void loadBlockCoordinates() {
        File itemconfig = new File(plugin.getDataFolder(), "items.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(itemconfig);
        File utilsconfig = new File(plugin.getDataFolder(), "utils.yml");
        YamlConfiguration configYamlUtils = YamlConfiguration.loadConfiguration(utilsconfig);
        File otherconfig = new File(plugin.getDataFolder(), "other.yml");
        YamlConfiguration configYamlOther = YamlConfiguration.loadConfiguration(otherconfig);
        blockXPVP = configYaml.getInt("pvp.block.X");
        blockYPVP = configYaml.getInt("pvp.block.Y");
        blockZPVP = configYaml.getInt("pvp.block.Z");
        blockXUtils = configYamlUtils.getInt("utils.block.X");
        blockYUtils = configYamlUtils.getInt("utils.block.Y");
        blockZUtils = configYamlUtils.getInt("utils.block.Z");
        blockXOther = configYamlOther.getInt("other.block.X");
        blockYOther = configYamlOther.getInt("other.block.Y");
        blockZOther = configYamlOther.getInt("other.block.Z");
    }

    @EventHandler
    public void onBlockClicked(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && clickedBlock.getX() == blockXPVP && clickedBlock.getY() == blockYPVP && clickedBlock.getZ() == blockZPVP) {
                event.getPlayer().openInventory(pvpmenu.createNewPvpInventory());
            } else if (clickedBlock != null && clickedBlock.getX() == blockXUtils && clickedBlock.getY() == blockYUtils && clickedBlock.getZ() == blockZUtils) {
                event.getPlayer().openInventory(utilsmenu.createNewUtilsInventory());
            } else if (clickedBlock != null && clickedBlock.getX() == blockXOther && clickedBlock.getY() == blockYOther && clickedBlock.getZ() == blockZOther) {
                event.getPlayer().openInventory(othermenu.createNewOtherInventory());
            }
        }
    }
}