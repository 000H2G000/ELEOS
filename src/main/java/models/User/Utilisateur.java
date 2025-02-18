package models.User;

public class Utilisateur {
    private String cin; // primary key: 8-digit string
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String addresse;
    private int age;
    private String gender; // "Male", "Female", "Other"

    public Utilisateur() {}

    public Utilisateur(String cin, String nom, String prenom, String email, String motDePasse,
                       String telephone, String addresse, int age, String gender) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.addresse = addresse;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAddresse() { return addresse; }
    public void setAddresse(String addresse) { this.addresse = addresse; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return String.format("""
        ┌─────────── User Information ───────────┐
        │ CIN        : %-25s │
        │ Name       : %-25s │
        │ First Name : %-25s │
        │ Email      : %-25s │
        │ Phone      : %-25s │
        │ Address    : %-25s │
        │ Age        : %-25d │
        │ Gender     : %-25s │
        └────────────────────────────────────────┘
        """,
                cin,
                nom,
                prenom,
                email,
                telephone,
                addresse,
                age,
                gender
        );
    }
}
