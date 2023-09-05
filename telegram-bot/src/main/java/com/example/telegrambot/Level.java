package com.example.telegrambot;

public enum Level {
    JUNIOR("Junior", false),
    MIDDLE("Middle", false),
    SENIOR("Senior", false);

    private String level;
    private boolean select;

    Level(String level, boolean select) {
        this.level = level;
        this.select = select;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static Level convertToEnum(String text){
        for(Level level:Level.values()){
            if(level.getLevel().equals(text)){
                return level;
            }
        }
        return null;
    }
}
