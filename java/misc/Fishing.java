package misc;

import commands.GetAllItems;
import events.ChatListener;
import events.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Fishing extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("getfishes")).setExecutor((new GetAllItems()));
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
