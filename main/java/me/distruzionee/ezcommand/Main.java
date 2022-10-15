package me.distruzionee.ezcommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @EventHandler
            (priority = EventPriority.NORMAL)
    public void onMessage(PlayerCommandPreprocessEvent event) {
            Player player = event.getPlayer();
            String command = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
            if (topic == null) {
                String name = this.getConfig().getString("name");
                String message = this.getConfig().getString("message");
                String nameTranslator = ChatColor.translateAlternateColorCodes('&', name);
                String messageTranslator = ChatColor.translateAlternateColorCodes('&', message);
                player.sendMessage(String.valueOf(nameTranslator) + " " + ChatColor.GOLD + command + " " + messageTranslator);
                event.setCancelled(true);
            }
    }

    public void onEnable() {

        Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
}
