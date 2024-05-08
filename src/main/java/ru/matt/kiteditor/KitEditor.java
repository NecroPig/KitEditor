package ru.matt.kiteditor;

import org.bukkit.plugin.java.JavaPlugin;
import ru.matt.kiteditor.listener.OnClickedBlock;
import ru.matt.kiteditor.menus.Other;
import ru.matt.kiteditor.menus.PVP;
import ru.matt.kiteditor.menus.Utils;

import java.io.File;
import java.io.IOException;

/* Если Рома зассыт это ставить, то
   Я начну жеска плакатттт...(((
   @author: deadlyPig

   UPD: АХАХАХ БЛЯ Я В АХУЕ СКОК ТУТ ГАВНИЩА

   UPD 2.0: тасвей, если ты это видишь
   то прости за гавна кодек ❤
 */

public final class KitEditor extends JavaPlugin {
    private PVP pvpmenu;
    private Utils utilsmenu;
    private Other othermenu;

    @Override
    public void onEnable() {
        createConfig();
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        File itemconfig = new File(getDataFolder(), "items.yml");
        File utilconfig = new File(getDataFolder(), "utils.yml");
        File otherconfig = new File(getDataFolder(), "other.yml");
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
        if (!utilconfig.exists()) {
            try {
                utilconfig.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!otherconfig.exists()) {
            try {
                otherconfig.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pvpmenu = new PVP(this);
        OnClickedBlock blockClickListener = new OnClickedBlock(this, pvpmenu);
        getServer().getPluginManager().registerEvents(blockClickListener, this);

        utilsmenu = new Utils(this);
        OnClickedBlock blockClickListner = new OnClickedBlock(this, utilsmenu);
        getServer().getPluginManager().registerEvents(blockClickListner, this);

        othermenu = new Other(this);
        OnClickedBlock onClickedBlock = new OnClickedBlock(this, othermenu);
        getServer().getPluginManager().registerEvents(onClickedBlock, this);
    }

    @Override
    public void onDisable() {
    }

    private void createConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        File itemconfig = new File(getDataFolder(), "items.yml");
        File utilconfig = new File(getDataFolder(), "utils.yml");
        File otherconfig = new File(getDataFolder(), "other.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        if (!itemconfig.exists()) {
            itemconfig.getParentFile().mkdirs();
            saveResource("items.yml", false);
        }
        if (!utilconfig.exists()) {
            utilconfig.getParentFile().mkdirs();
            saveResource("utils.yml", false);
        }
        if (!otherconfig.exists()) {
            otherconfig.getParentFile().mkdirs();
            saveResource("other.yml", false);
        }
    }
}