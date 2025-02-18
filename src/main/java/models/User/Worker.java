package models.User;

import java.sql.Date;

public class Worker extends Utilisateur {
    private Date dateEmbauche;
    private Date dateFinContrat;
    private String poste;

    public Worker() {
        super();
    }
    public Worker(String cin, String nom, String prenom, String email, String motDePasse,
                  String telephone, String addresse, int age, String gender,
                  Date dateEmbauche, Date dateFinContrat, String poste) {
        super(cin, nom, prenom, email, motDePasse, telephone, addresse, age, gender);
        this.dateEmbauche = dateEmbauche;
        this.dateFinContrat = dateFinContrat;
        this.poste = poste;
    }
    // Getters and Setters
    public Date getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(Date dateEmbauche) { this.dateEmbauche = dateEmbauche; }
    public Date getDateFinContrat() { return dateFinContrat; }
    public void setDateFinContrat(Date dateFinContrat) { this.dateFinContrat = dateFinContrat; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    @Override
    public String toString() {
        return String.format("""
        ┌─────────── Worker Information ───────────┐
        │ CIN            : %-25s │
        │ Name           : %-25s │
        │ First Name     : %-25s │
        │ Email          : %-25s │
        │ Phone          : %-25s │
        │ Address        : %-25s │
        │ Age            : %-25d │
        │ Gender         : %-25s │
        │ Hire Date      : %-25s │
        │ Contract End   : %-25s │
        │ Position       : %-25s │
        └──────────────────────────────────────────┘
        """,
                getCin(),
                getNom(),
                getPrenom(),
                getEmail(),
                getTelephone(),
                getAddresse(),
                getAge(),
                getGender(),
                dateEmbauche,
                dateFinContrat,
                poste
        );
    }
}
