package me.hektortm.woSSystems.systems.stats.commands.subcommands;

import me.hektortm.woSSystems.systems.stats.StatsManager;
import me.hektortm.woSSystems.systems.stats.commands.StatsSubCommand;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class GlobalDeleteCommand extends StatsSubCommand {

    private final StatsManager manager;
    public GlobalDeleteCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.error(sender, "general", "error.notplayer");
            return;
        }

        if (!PermissionUtil.hasPermission(sender, Permissions.STATS_GLOBAL_DELETE)) return;

        Player p = (Player) sender;
        String id = args[0].toLowerCase();

        File statFile = new File(manager.globalStatsFolder, id + ".yml");
        if (!statFile.exists()) {
            Utils.error(p, "stats", "error.not-found");
            return;
        }
        if (args.length == 1) {
            Utils.successMsg1Value(p, "stats", "delete.confirm", "%id%", id);
            return;
        }

        if(args.length == 2 && args[1].equals("confirm")) {
            manager.deleteStat(p, id, true);
            Utils.successMsg1Value(p, "stats", "delete.success", "%id%", id);
        }

    }
}