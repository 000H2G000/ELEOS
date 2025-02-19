package models;

import java.util.List;
import java.util.ArrayList;

public class Soin {
    private int id;
    private String name;
    private String description;
    private List<Activite> listeActivite = new ArrayList<>();
    private double prix;

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

    public List<Activite> getListeActivite() {
        return listeActivite;
    }

    public void setListeActivite(List<Activite> listeActivite) {
        this.listeActivite = listeActivite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Soin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", listeActivite=" + listeActivite +
                ", prix=" + prix +
                '}';
    }
}
