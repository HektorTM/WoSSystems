package me.hektortm.woSSystems.stats.commands.subcommands;

import me.hektortm.woSSystems.stats.StatsManager;
import me.hektortm.woSSystems.stats.commands.StatsSubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

import static me.hektortm.woSSystems.stats.utils.Operation.GIVE;
import static me.hektortm.woSSystems.stats.utils.Operation.TAKE;

public class TakeCommand extends StatsSubCommand {

    private final StatsManager manager;
    public TakeCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "take";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("stats.take")) {
            Utils.error(sender, "general", "error.perms");
            return;
        }
        if (args.length < 2 || args.length > 3) {
            Utils.error(sender, "stats", "error.usage.take");
            return;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        String id = args[1].toLowerCase();
        long amount = 0;

        File statFile = new File(manager.statsFolder, id + ".yml");
        if (!statFile.exists()) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        try {
            amount = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            Utils.error(sender, "stats", "error.invalid-amount");
            return;
        }

        if (amount < 0) {
            Utils.error(sender, "stats", "error.invalid-amount");
            return;
        }

        if (!manager.getStats().containsKey(id)) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        manager.modifyStat(p.getUniqueId(), id, amount, TAKE);
        if (sender instanceof Player) {
            Utils.successMsg3Values(sender, "stats", "take", "%player%", p.getName(),"%stat%", id, "%amount%", String.valueOf(amount));
        }

    }
}