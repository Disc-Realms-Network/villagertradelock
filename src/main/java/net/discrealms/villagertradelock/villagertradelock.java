package net.discrealms.villagertradelock;

import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import org.bukkit.Location;
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
        // Save default config if not there
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
            // Run the check async on a separate thread
            CompletableFuture.runAsync(() -> checkAndLockTrades(villager));
        } else {
            // Run the check async within the region
            checkAndLockTrades(villager);
        }
    }

    private void checkAndLockTrades(Villager villager) {
        Location location = villager.getLocation();
        RegionScheduler scheduler = getServer().getRegionScheduler();

        scheduler.execute(this, location, () -> {
            // Prevent trades from being rerolled by marking villager's trades as locked
            if (villager.getVillagerExperience() > 0) {
                return;
            }
            // Add 1 experience to lock trades
            villager.setVillagerExperience(1);
        });
    }
}