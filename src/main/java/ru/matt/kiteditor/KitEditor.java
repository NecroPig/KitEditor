package ru.matt.kiteditor;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.matt.kiteditor.listener.OnClickedBlock;
import ru.matt.kiteditor.listener.OnClickedMenu;
import ru.matt.kiteditor.menus.PVP;

import java.io.File;
import java.io.IOException;

/* Если Рома зассыт это ставить, то
   Я начну жеска плакатттт...(((
   @author: deadlyPig
 */

public final class KitEditor extends JavaPlugin {
    private PVP pvpmenu;

    @Override
    public void onEnable() {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        File itemconfig = new File(getDataFolder(), "items.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!itemconfig.exists()) {
            try {
                itemconfig.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        YamlConfiguration items = YamlConfiguration.loadConfiguration(itemconfig);

        pvpmenu = new PVP(this);
        OnClickedBlock blockClickListener = new OnClickedBlock(this, pvpmenu);
        getServer().getPluginManager().registerEvents(blockClickListener, this);

        OnClickedMenu clickedMenuListener = new OnClickedMenu();
        getServer().getPluginManager().registerEvents(clickedMenuListener, this);
    }

    @Override
    public void onDisable() {
    }
}
