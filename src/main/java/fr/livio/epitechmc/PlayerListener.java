package fr.livio.epitechmc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PlayerListener implements Listener {
    private final Plugin plugin;

    public PlayerListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    URLConnection url = new URL("https://intra.epitech.eu/auth-XXXXXXXXXXXXXXXXX/user/?format=json").openConnection();
                    url.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    reader.lines().forEach(sb::append);
                    JsonObject jobj = new JsonParser().parse(sb.toString()).getAsJsonObject();
                    String name = jobj.get("title").getAsString();
                    player.sendMessage("§aBonjour §f" + name);
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.runTaskAsynchronously(this.plugin);
    }
}
