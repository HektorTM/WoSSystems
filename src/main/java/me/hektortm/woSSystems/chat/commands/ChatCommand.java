package me.hektortm.woSSystems.chat.commands;

import me.hektortm.woSSystems.chat.ChatManager;
import me.hektortm.woSSystems.chat.commands.subcommands.Focus;
import me.hektortm.woSSystems.chat.commands.subcommands.Join;
import me.hektortm.woSSystems.chat.commands.subcommands.Leave;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ChatCommand implements CommandExecutor {

    private final ChatManager chatManager;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public ChatCommand(ChatManager chatManager) {
        this.chatManager = chatManager;

        subCommands.put("focus", new Focus(chatManager));
        subCommands.put("join", new Join(chatManager));
        subCommands.put("leave", new Leave(chatManager));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand != null) {
            if(PermissionUtil.hasPermission(sender, subCommand.getPermission())) {
                subCommand.execute(sender, java.util.Arrays.copyOfRange(args, 1, args.length));
            } else {
                return true;
            }
        } else {
            sender.sendMessage("Unknown subcommand: " + subCommandName);
        }

        return true;
    }
}