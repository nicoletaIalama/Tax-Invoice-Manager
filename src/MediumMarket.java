import java.util.Vector;

public class MediumMarket extends Magazin {
    public MediumMarket(){tip = "MediumMarket";}

    @Override
    public double calculScutiriTaxe() {
        double totalCuTaxe = getTotalCuTaxe();
        totalCuTaxe /= 2;
        Vector<String> categorii = new Vector<>();

        for(Factura factura : facturi){
            for(ProdusComandat produsComandat : factura.produseComandate){
                if(!(categorii.contains(produsComandat.getProdus().getCategorie())))
                    categorii.add(produsComandat.getProdus().getCategorie());
            }
        }

        for (String categorie : categorii){
            double totalCategorie = 0;
            for (Factura factura : facturi) {
                for (ProdusComandat produsComandat : factura.produseComandate) {
                    if (produsComandat.getProdus().getCategorie().equals(categorie)) {
                        totalCategorie += produsComandat.getCantitate() *
                                produsComandat.getProdus().getPret() *
                                (1 + produsComandat.getTaxa());
                    }
                }
            }
            if (totalCategorie > totalCuTaxe){
                return 0.05;
            }
        }

        return 0;

    }
}
