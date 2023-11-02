package me.sialim.mineronpamonomonomachineplugin;

import me.sialim.mineronpamonomonomachineplugin.Events.InteractListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineronpaMonomonoMachinePlugin extends JavaPlugin
{
    private Economy economy;
    private Permission permission;
    @Override
    public void onEnable()
    {
        if (!setupEconomy() || !setupPermissions()) {
            getLogger().severe("Vault integration failed. Make sure you have a compatible economy and permissions plugin installed.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new InteractListener(economy), this);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
        return (economy != null);
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp != null) {
            permission = rsp.getProvider();
        }
        return (permission != null);
    }
}
