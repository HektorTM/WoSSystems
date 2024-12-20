package me.hektortm.woSSystems.utils.dataclasses;

public class ChannelData {

    private final String name;
    private final String shortName;
    private final String prefix;
    private final int range;
    private final String permission;
    private final String format;

    public ChannelData(String name, String shortName, String prefix, int range, String permission, String format) {
        this.name = name;
        this.shortName = shortName;
        this.prefix = prefix;
        this.range = range;
        this.permission = permission;
        this.format = format;
    }

    public String getName() {
        return name;
    }
    public String getShortName() {
        return shortName;
    }
    public String getPrefix() {
        return prefix;
    }
    public int getRange() {
        return range;
    }
    public String getPermission() {
        return permission;
    }
    public String getFormat() {
        return format;
    }

}
