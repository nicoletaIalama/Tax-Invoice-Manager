public class ProdusComandat {
    private Produs produs;
    private Double taxa;
    private int cantitate;

    public void setProdus(Produs produs) {
        this.produs = produs;
    }

    public Produs getProdus() {
        return produs;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Double getTaxa() {
        return taxa / 100;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getCantitate() {
        return cantitate;
    }

    @Override
    public String toString() {
        return produs.toString() + " Taxa: " + taxa + " Cantitate: " + cantitate + "\n";
    }
}
