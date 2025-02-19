package models.User;

import java.sql.Timestamp;

public class Reponse {
    private int id;
    private int reclamationId;
    private String utilisateurCin;
    private String message;
    private Timestamp createdAt;

    public Reponse() {}

    public Reponse(int id, int reclamationId, String utilisateurCin, String message, Timestamp createdAt) {
        this.id = id;
        this.reclamationId = reclamationId;
        this.utilisateurCin = utilisateurCin;
        this.message = message;
        this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getReclamationId() { return reclamationId; }
    public void setReclamationId(int reclamationId) { this.reclamationId = reclamationId; }
    public String getUtilisateurCin() { return utilisateurCin; }
    public void setUtilisateurCin(String utilisateurCin) { this.utilisateurCin = utilisateurCin; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", reclamationId=" + reclamationId +
                ", utilisateurCin='" + utilisateurCin + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
