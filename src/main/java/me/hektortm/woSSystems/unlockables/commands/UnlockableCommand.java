package me.hektortm.woSSystems.unlockables.commands;

import me.hektortm.woSSystems.stats.commands.StatsSubCommand;
import me.hektortm.woSSystems.unlockables.UnlockableManager;
import me.hektortm.woSSystems.unlockables.commands.subcommands.CreateCommand;
import me.hektortm.woSSystems.unlockables.commands.subcommands.CreateTempCommand;
import me.hektortm.woSSystems.unlockables.commands.subcommands.GiveCommand;
import me.hektortm.woSSystems.unlockables.commands.subcommands.GiveTempCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UnlockableCommand implements CommandExecutor {

    private final Map<String, UnlockableSubCommand> subCommands = new HashMap<>();
    private final UnlockableManager manager;

    public UnlockableCommand(UnlockableManager manager) {

        this.manager = manager;

        subCommands.put("create", new CreateCommand(manager));
        subCommands.put("creaatetemp", new CreateTempCommand(manager));
        subCommands.put("give", new GiveCommand(manager));
        subCommands.put("givetemp", new GiveTempCommand(manager));
        subCommands.put("take");
        subCommands.put("taketemp");
        subCommands.put("delete");

    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}