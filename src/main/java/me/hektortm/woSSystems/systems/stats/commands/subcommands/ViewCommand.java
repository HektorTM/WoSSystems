package me.hektortm.woSSystems.systems.stats.commands.subcommands;

import me.hektortm.woSSystems.systems.stats.StatsManager;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.io.File;

public class ViewCommand extends SubCommand {

    private final StatsManager manager;

    public ViewCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "view";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.STATS_VIEW;
    }


    //TODO: WIP needed
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2 || args.length > 3) {
            Utils.error(sender, "stats", "error.usage.take");
            return;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        String id = args[1];

        if (!manager.getStats().containsKey(id)) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        long value = manager.getPlayerStat(p.getUniqueId(), id);

        Utils.successMsg3Values(sender, "stats", "view", "%player%", p.getName(), "%value%", String.valueOf(value), "%id%", id);

    }
}
