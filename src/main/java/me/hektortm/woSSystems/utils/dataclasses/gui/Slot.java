package me.hektortm.woSSystems.utils.dataclasses.gui;


public class Slot {
    private Item item;
    private String citem;
    private Conditions conditions;
    private Attributes attributes;
    private Actions actions;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCitem() {
        return citem;
    }

    public void setCitem(String citem) {
        this.citem = citem;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }
}
