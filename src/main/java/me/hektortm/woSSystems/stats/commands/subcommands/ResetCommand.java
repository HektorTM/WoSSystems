package me.hektortm.woSSystems.stats.commands.subcommands;

import me.hektortm.woSSystems.stats.StatsManager;
import me.hektortm.woSSystems.stats.commands.StatsSubCommand;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

import static me.hektortm.woSSystems.stats.utils.Operation.RESET;
import static me.hektortm.woSSystems.stats.utils.Operation.SET;

public class ResetCommand extends StatsSubCommand {

    private final StatsManager manager;
    public ResetCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!PermissionUtil.hasPermission(sender, Permissions.STATS_RESET)) return;

        if (args.length < 1 || args.length > 2) {
            Utils.error(sender, "stats", "error.usage.reset");
            return;
        }



        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        String id = args[1].toLowerCase();

        File statFile = new File(manager.statsFolder, id + ".yml");
        if (!statFile.exists()) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        if (!manager.getStats().containsKey(id)) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        manager.modifyStat(p.getUniqueId(), id, 0, RESET);
        if (sender instanceof Player) {
            Utils.successMsg2Values(sender, "stats", "reset", "%player%", p.getName(),"%stat%", id);
        }
    }
}