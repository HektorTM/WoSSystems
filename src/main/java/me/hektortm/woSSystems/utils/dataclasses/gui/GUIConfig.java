package me.hektortm.woSSystems.utils.dataclasses.gui;

import java.util.Map;

public class GUIConfig {
    private String title;
    private int rows;
    private String openAction;
    private String closeAction;
    private Map<Integer, Slot> slots;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOpenAction() {
        return openAction;
    }

    public void setOpenAction(String openAction) {
        this.openAction = openAction;
    }

    public String getCloseAction() {
        return closeAction;
    }

    public void setCloseAction(String closeAction) {
        this.closeAction = closeAction;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public void setSlots(Map<Integer, Slot> slots) {
        this.slots = slots;
    }
}
