package me.hektortm.woSSystems.utils.dataclasses.gui;

import java.util.Map;

public class Conditions {
    private Map<String, String> unlockable;
    private Map<String, String> citem;
    private ElseAction elseAction;

    public Map<String, String> getUnlockable() {
        return unlockable;
    }

    public void setUnlockable(Map<String, String> unlockable) {
        this.unlockable = unlockable;
    }

    public Map<String, String> getCitem() {
        return citem;
    }

    public void setCitem(Map<String, String> citem) {
        this.citem = citem;
    }

    public ElseAction getElseAction() {
        return elseAction;
    }

    public void setElseAction(ElseAction elseAction) {
        this.elseAction = elseAction;
    }
}
