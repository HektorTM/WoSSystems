package me.hektortm.woSSystems.chat.commands.subcommands;

import me.hektortm.woSSystems.chat.ChatManager;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave extends SubCommand {

    private final ChatManager chatManager;

    public Leave(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public Permissions getPermission() {
        return null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String channel = args[0];
        Player p = (Player) sender;

        chatManager.leaveChannel(p, channel);
    }
}