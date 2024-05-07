package ru.matt.kiteditor.listener;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;
import ru.matt.kiteditor.menus.PVP;

import java.io.File;

public class OnClickedBlock implements Listener {
    private JavaPlugin plugin;
    private PVP pvpmenu;
    private int blockX;
    private int blockY;
    private int blockZ;

    public OnClickedBlock(JavaPlugin plugin, PVP pvpmenu) {
        this.plugin = plugin;
        this.pvpmenu = pvpmenu;
        loadBlockCoordinates();
    }

    private void loadBlockCoordinates() {
        File itemconfig = new File(plugin.getDataFolder(), "items.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(itemconfig);
        blockX = configYaml.getInt("pvp.block.X");
        blockY = configYaml.getInt("pvp.block.Y");
        blockZ = configYaml.getInt("pvp.block.Z");
    }

    @EventHandler
    public void onBlockClicked(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && clickedBlock.getX() == blockX && clickedBlock.getY() == blockY && clickedBlock.getZ() == blockZ) {
                event.getPlayer().openInventory(pvpmenu.getPvp());
            }
        }
    }
}