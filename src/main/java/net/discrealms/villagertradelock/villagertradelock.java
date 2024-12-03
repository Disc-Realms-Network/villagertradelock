package net.discrealms.villagertradelock;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class villagertradelock extends JavaPlugin implements Listener {

    private boolean useAsyncCheck;

    @Override
    public void onEnable() {
        // Save default config if not present
        saveDefaultConfig();
        loadConfig();

        // Register events
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        loadConfig();
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();
        useAsyncCheck = config.getBoolean("use-async-check", true);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.VILLAGER) return;

        Villager villager = (Villager) event.getRightClicked();

        if (useAsyncCheck) {
            // Run the check asynchronously on a separate thread
            CompletableFuture.runAsync(() -> checkAndLockTrades(villager));
        } else {
            // Run the check synchronously on the server thread
            checkAndLockTrades(villager);
        }
    }

    private void checkAndLockTrades(Villager villager) {
        // Ensure this operation is thread-safe
        Bukkit.getScheduler().runTask(this, () -> {
            if (villager.getVillagerExperience() > 0) return;

            // Lock the villager by adding 1 experience
            villager.setVillagerExperience(1);
        });
    }
}