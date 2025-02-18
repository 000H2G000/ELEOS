package models.User;

import java.sql.Timestamp;

public class Reclamation {
    private int id;
    private String utilisateurCin;
    private String sujet;
    private String message;
    private Timestamp createdAt;
    private String status;
    private Timestamp updatedAt;

    public Reclamation() {}

    public Reclamation(int id, String utilisateurCin, String sujet, String message,
                       Timestamp createdAt, String status, Timestamp updatedAt) {
        this.id = id;
        this.utilisateurCin = utilisateurCin;
        this.sujet = sujet;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
        this.updatedAt = updatedAt;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUtilisateurCin() { return utilisateurCin; }
    public void setUtilisateurCin(String utilisateurCin) { this.utilisateurCin = utilisateurCin; }
    public String getSujet() { return sujet; }
    public void setSujet(String sujet) { this.sujet = sujet; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", utilisateurCin='" + utilisateurCin + '\'' +
                ", sujet='" + sujet + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
