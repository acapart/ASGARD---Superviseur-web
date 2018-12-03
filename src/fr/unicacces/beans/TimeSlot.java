package fr.unicacces.beans;

public class TimeSlot {

    private int id;
    private String name;
    private int dayBegin;
    private int dayEnd;
    private String hourBegin;
    private String hourEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayBegin() {
        return dayBegin;
    }

    public void setDayBegin(int dayBegin) {
        this.dayBegin = dayBegin;
    }

    public int getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(int dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getHourBegin() {
        return hourBegin;
    }

    public void setHourBegin(String hourBegin) {
        this.hourBegin = hourBegin;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }
}
