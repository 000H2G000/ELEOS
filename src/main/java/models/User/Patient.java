package models.User;

public class Patient {
    private String cin; // primary key, references Utilisateur(cin)
    private int age;
    private int numeroChambre;

    public Patient() {}

    public Patient(String cin, int age, int numeroChambre) {
        this.cin = cin;
        this.age = age;
        this.numeroChambre = numeroChambre;
    }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getNumeroChambre() { return numeroChambre; }
    public void setNumeroChambre(int numeroChambre) { this.numeroChambre = numeroChambre; }

    @Override
    public String toString() {
        return String.format("""
        ┌─────────── Infermier Information ───────────┐
        │ CIN                   : %-25s │
        │ Age                   : %-25s │
        │ Numero de chambre     : %-25s │
        └──────────────────────────────────────────┘
        """,
                getCin(),
                getAge(),
                getNumeroChambre()
        );
    }
}
