package models.Activite;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activite {

    int id;
    String name, description;

    LocalDateTime start_time, end_time;

    public Activite() {
    }

    public Activite(int id, String name, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Activite(String name, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start) {
        this.start_time = start;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end) {
        this.end_time = end;
    }

    @Override
    public String toString() {
        return "activite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start_time +
                ", end=" + end_time +
                '}';
    }
}


