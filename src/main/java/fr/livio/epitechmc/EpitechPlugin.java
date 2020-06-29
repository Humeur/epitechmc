package fr.livio.epitechmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class EpitechPlugin extends JavaPlugin {
    public static final Logger LOGGER = Bukkit.getLogger();
    @Override
    public void onEnable() {
        LOGGER.info("Démarrage de EpitechMC v1");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
