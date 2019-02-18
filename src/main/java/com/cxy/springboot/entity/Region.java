package com.cxy.springboot.entity;

public class Region {
    private String currentLevel;

    private String currentName;

    private String highLevel;

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel == null ? null : currentLevel.trim();
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName == null ? null : currentName.trim();
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel == null ? null : highLevel.trim();
    }
}