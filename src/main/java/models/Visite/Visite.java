package models.Visite;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Visite {
    private Integer id;
    private Integer patientId;
    private Integer workerId;
    private LocalDate dateVisite;
    private VisiteStatus status = VisiteStatus.en_attente;
    private VisiteType typeVisite = VisiteType.ordinaire;
    private LocalDateTime createdAt;

    // @PrePersist
    //protected void onCreate() {
    //  this.createdAt = LocalDateTime.now();}


    //public Visite() {}

    public Visite(int patientId, int workerId, String date, String status, String typeVisite) {
        this.patientId = patientId;
        this.workerId = workerId;
        this.dateVisite = LocalDate.parse(date);
        this.status = VisiteStatus.valueOf(status);
        this.typeVisite = VisiteType.valueOf(typeVisite);
    }

    public Visite(LocalDate dateVisite, VisiteType typeVisite, LocalDateTime createdAt) {
        this.dateVisite = dateVisite;
        this.typeVisite = typeVisite;
        this.createdAt = createdAt;
    }

    public Visite() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public VisiteStatus getStatus() {
        return status;
    }

    public void setStatus(VisiteStatus status) {
        this.status = status;
    }

    public VisiteType getTypeVisite() {
        return typeVisite;
    }

    public void setTypeVisite(VisiteType typeVisite) {
        this.typeVisite = typeVisite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", workerId=" + workerId +
                ", dateVisite=" + dateVisite +
                ", status=" + status +
                ", typeVisite=" + typeVisite +
                ", createdAt=" + createdAt +
                '}';
    }
}
