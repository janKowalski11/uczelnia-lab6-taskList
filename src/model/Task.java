package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.time.LocalDate;


public class Task {

    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private LocalDate expDate;
    private Priority priority;


    //returns color based on priority
    public Color getTaskColor( ) {

        switch (this.priority) {
            case LOW:
                return Color.GREEN;
            case MEDIUM:
                return Color.ORANGE;
            case HIGH:
                return Color.RED;
            default:
                throw new IllegalArgumentException("sth wrong with priority values? ");
        }

    }

    @Override
    public String toString() {
        return title.getValue();
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public LocalDate getExpDate() {
        return expDate;
    }
}
