import java.util.Vector;

public abstract class  Magazin implements IMagazin {
    String nume;
    Vector<Factura> facturi = new Vector<>();
    public String tip;

    @Override
    public double getTotalFaraTaxe() {
        int i;
        double TotalFaraTaxe = 0;
        for (i = 0; i < facturi.size(); i++)
            TotalFaraTaxe += facturi.get(i).getTotalFaraTaxe();
        return TotalFaraTaxe;
    }

    @Override
    public double getTotalCuTaxe() {
        int i;
        double TotalCuTaxe = 0;
        for (i = 0; i < facturi.size(); i++)
            TotalCuTaxe += facturi.get(i).getTotalCuTaxe();
        return TotalCuTaxe;
    }

    @Override
    public double getTotalCuTaxeScutite() {
        double scutiriTaxe = calculScutiriTaxe();
        return getTotalCuTaxe() * (1 - scutiriTaxe);
    }

    @Override
    public double getTotalTaraFaraTaxe(String tara) {
        int i;
        double TotalTaraFaraTaxe = 0;
        for (i = 0; i < facturi.size(); i++ )
            TotalTaraFaraTaxe += facturi.get(i).getTotalTaraFaraTaxe(tara);
        return  TotalTaraFaraTaxe;
    }

    @Override
    public double getTotalTaraCuTaxe(String tara) {
        int i;
        double TotalTaraCuTaxe = 0;
        for (i = 0; i < facturi.size(); i++ )
            TotalTaraCuTaxe += facturi.get(i).getTotalTaraCuTaxe(tara);
        return TotalTaraCuTaxe;
    }

    @Override
    public double getTotalTaraCuTaxeScutite(String tara) {
        return  getTotalTaraCuTaxe(tara) * (1 - calculScutiriTaxe());
    }

    @Override
    public String toString() {
        return nume + facturi + "\n" + getTotalFaraTaxe() + " " +
                getTotalCuTaxe() + " " + getTotalCuTaxeScutite() + "\n";
    }


}
