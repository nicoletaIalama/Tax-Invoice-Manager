import java.util.Vector;

public class Factura {
    public String denumire;
    public Vector<ProdusComandat> produseComandate = new Vector<>();

    public double getTotalFaraTaxe() {
        int i;
        double TotalFaraTaxe = 0;
        for (i = 0; i < produseComandate.size(); i++)
            TotalFaraTaxe += produseComandate.get(i).getProdus().getPret() *
                    produseComandate.get(i).getCantitate();
        return TotalFaraTaxe;
    }

    public double getTaxe() {
        return getTotalCuTaxe() - getTotalFaraTaxe();
    }

    public double getTotalCuTaxe() {
        int i;
        double TotalCuTaxe = 0;
        for (i = 0; i < produseComandate.size(); i++)
            TotalCuTaxe += produseComandate.get(i).getProdus().getPret() *
                    (1 + produseComandate.get(i).getTaxa()) *
                    produseComandate.get(i).getCantitate();
        return TotalCuTaxe;
    }

    public double getTotalTaraFaraTaxe(String tara) {
        int i;
        double TotalTaraFaraTaxe = 0;
        for (i = 0; i < produseComandate.size(); i++)
            if (produseComandate.get(i).getProdus().getTaraOrigine().equals(tara))
                TotalTaraFaraTaxe += produseComandate.get(i).getProdus().getPret() *
                        produseComandate.get(i).getCantitate() ;
        return TotalTaraFaraTaxe;
    }

    public double getTaxeTara(String tara) {
        return getTotalTaraCuTaxe(tara) - getTotalTaraFaraTaxe(tara);
    }

    public double getTotalTaraCuTaxe(String tara) {
        int i;
        double TotalTaraCuTaxe = 0;
        for (i = 0; i < produseComandate.size(); i++)
            if (produseComandate.get(i).getProdus().getTaraOrigine().equals(tara))
                TotalTaraCuTaxe += produseComandate.get(i).getProdus().getPret() *
                        (1 + produseComandate.get(i).getTaxa()) *
                        produseComandate.get(i).getCantitate();
        return TotalTaraCuTaxe;
    }

    @Override
    public String toString() {
        return denumire + "\n" + produseComandate + "\n";
    }
}