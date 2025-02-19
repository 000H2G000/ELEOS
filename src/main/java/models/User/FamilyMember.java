package models.User;

public class FamilyMember extends Utilisateur {
    private String patientCin; // foreign key to Patient's cin

    public FamilyMember() { super(); }
    public FamilyMember(String cin, String nom, String prenom, String email, String motDePasse,
                        String telephone, String addresse, int age, String gender, String patientCin) {
        super(cin, nom, prenom, email, motDePasse, telephone, addresse, age, gender);
        this.patientCin = patientCin;
    }
    public String getPatientCin() { return patientCin; }
    public void setPatientCin(String patientCin) { this.patientCin = patientCin; }

    @Override
    public String toString() {
        return String.format("""
        ┌─────────── Infermier Information ───────────┐
        │ CIN            : %-25s │
        │ Name           : %-25s │
        │ First Name     : %-25s │
        │ Email          : %-25s │
        │ Phone          : %-25s │
        │ Address        : %-25s │
        │ Age            : %-25d │
        │ Gender         : %-25s │
        │ Patient CIN    : %-25s │
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
                getPatientCin()
        );
    }

}
