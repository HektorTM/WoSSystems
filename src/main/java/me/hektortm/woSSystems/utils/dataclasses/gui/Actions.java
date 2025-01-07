package me.hektortm.woSSystems.utils.dataclasses.gui;

import java.util.List;

public class Actions {
    private List<String> left;
    private List<String> right;
    private List<String> shiftLeft;
    private List<String> shiftRight;
    private List<String> drop;

    public List<String> getLeft() {
        return left;
    }

    public void setLeft(List<String> left) {
        this.left = left;
    }

    public List<String> getRight() {
        return right;
    }

    public void setRight(List<String> right) {
        this.right = right;
    }

    public List<String> getShiftLeft() {
        return shiftLeft;
    }

    public void setShiftLeft(List<String> shiftLeft) {
        this.shiftLeft = shiftLeft;
    }

    public List<String> getShiftRight() {
        return shiftRight;
    }

    public void setShiftRight(List<String> shiftRight) {
        this.shiftRight = shiftRight;
    }

    public List<String> getDrop() {
        return drop;
    }

    public void setDrop(List<String> drop) {
        this.drop = drop;
    }
}
