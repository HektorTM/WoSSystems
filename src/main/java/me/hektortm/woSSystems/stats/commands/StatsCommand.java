package me.hektortm.woSSystems.stats.commands;

import me.hektortm.woSSystems.WoSSystems;
import me.hektortm.woSSystems.stats.StatsManager;
import me.hektortm.woSSystems.stats.commands.subcommands.*;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StatsCommand implements CommandExecutor {

    private final Map<String, StatsSubCommand> subCommands = new HashMap<>();
    private final WoSSystems plugin;
    private final StatsManager manager;

    public StatsCommand(WoSSystems plugin, StatsManager manager) {
        this.plugin = plugin;
        this.manager = manager;

        subCommands.put("give", new GiveCommand(manager));
        subCommands.put("take", new TakeCommand(manager));
        subCommands.put("set", new SetCommand(manager));
        //subCommands.put("help", new HelpCommand());
        subCommands.put("reload", new ReloadCommand(manager));
        subCommands.put("create", new CreateCommand(manager));
        subCommands.put("delete", new DeleteCommand(manager));
        //subCommands.put("view", new ViewCommand());

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            Utils.error(sender, "stats", "error.usage.stats");
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        StatsSubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand != null) {
            subCommand.execute(sender, java.util.Arrays.copyOfRange(args, 1, args.length));
        } else {
            Utils.error(sender, "stats", "error.usage.stats");
        }


        return true;
    }
}