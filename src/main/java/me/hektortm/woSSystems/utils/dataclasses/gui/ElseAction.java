package me.hektortm.woSSystems.utils.dataclasses.gui;

import java.util.List;

public class ElseAction {
    private List<String> actions;
    private boolean visible;
    private boolean clickable;

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
}
