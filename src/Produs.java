public class Produs {
   private String denumire;
   private String categorie;
   private String taraOrigine;
   private double pret;

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setTaraOrigine(String taraOrigine) {
        this.taraOrigine = taraOrigine;
    }

    public String getTaraOrigine() {
        return taraOrigine;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return "Denumire: " + denumire + " Categorie: " + categorie + " Tara: " + taraOrigine + " Pret: " + pret;
    }
}
