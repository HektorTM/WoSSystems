package me.hektortm.woSSystems.database.dao;

import me.hektortm.woSSystems.WoSSystems;
import me.hektortm.woSSystems.database.DAOHub;
import me.hektortm.wosCore.database.DatabaseManager;
import me.hektortm.wosCore.database.IDAO;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerDAO implements IDAO {
    private final Connection conn;
    private final DatabaseManager db;
    private final DAOHub daoHub;
    private final WoSSystems plugin = WoSSystems.getPlugin(WoSSystems.class);

    public PlayerDAO(DatabaseManager db, DAOHub daoHub) {
        this.db = db;
        this.daoHub = daoHub;
        this.conn = db.getConnection();
    }

    @Override
    public void initializeTable() throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS playerdata (" +
                    "uuid TEXT PRIMARY KEY, " +
                    "username TEXT NOT NULL, "+
                    "nickname TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS reserved_nicknames (" +
                    "uuid TEXT PRIMARY KEY, "+
                    "username TEXT NOT NULL, "+
                    "reserved_nick TEXT NOT NULL)");
        }
    }



    public void addPlayer(Player player) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO playerdata (uuid, username) VALUES (?, ?) ON CONFLICT(uuid) DO NOTHING")) {
            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setString(2, player.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNickname(Player player, String nickname) {
        if (isNickReserved(nickname)) {
            if (getWhoReservedNick(nickname) != player.getUniqueId()) {
                return;
            }
        }
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE playerdata SET nickname = ? WHERE uuid = ?")) {
            stmt.setString(1, nickname);
            stmt.setString(2, player.getUniqueId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            plugin.writeLog("PlayerDAO", Level.SEVERE, "Error setting Nickname: " + e.getMessage());
        }
    }

    public String getNickname(Player player) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT nickname FROM playerdata WHERE uuid = ?")) {
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.getString("nickname") == null) return null;
            return rs.getString("nickname");
        } catch (SQLException e) {
            plugin.writeLog("PlayerDAO", Level.SEVERE, "Error getting Nickname: " + e.getMessage());
            return null;
        }
    }
    // TODO maybe error system with INT return
    public void reserveNick(Player p, String nickname) {
        if (isNickReserved(nickname)) {
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO reserved_nicknames (uuid, username, reserved_nick) VALUES (?,?,?)")) {
            stmt.setString(1, p.getUniqueId().toString());
            stmt.setString(2, p.getDisplayName());
            stmt.setString(3, nickname);
        } catch (SQLException e) {
            plugin.writeLog("PlayerDAO", Level.SEVERE, "Error reserving Nickname: " + e.getMessage());
        }
    }

    public boolean isNickReserved(String nickname) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT 1 from reserved_nicknames WHERE reserved_nick = ?")) {
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            plugin.writeLog("PlayerDAO", Level.SEVERE, "Error getting reserved Nickname BOOL: " + e.getMessage());
            return false;
        }
    }

    public UUID getWhoReservedNick(String nickname) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT uuid FROM reserved_nicknames WHERE reserved_nick = ?")) {
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return UUID.fromString(rs.getString("uuid"));
            else return null;
        } catch (SQLException e) {
            plugin.writeLog("PlayerDAO", Level.SEVERE, "Error getting UUID for reserved Nickname: " + e.getMessage());
            return null;
        }
    }

}
