import java.util.Set;
import java.util.Vector;

public class MiniMarket extends Magazin {
    public MiniMarket(){ tip = "MiniMarket"; }

    @Override
    public double calculScutiriTaxe() {
        double totalCuTaxe = getTotalCuTaxe();
        totalCuTaxe /= 2;
        Vector<String> tari = new Vector<>();

        for(Factura factura : facturi){
            for(ProdusComandat produsComandat : factura.produseComandate) {
                if (!(tari.contains(produsComandat.getProdus().getTaraOrigine()))){
                    tari.add(produsComandat.getProdus().getTaraOrigine());
                }
            }
        }

        for (String tara : tari){
            double totalTara = 0;
            for (Factura factura : facturi) {
                for (ProdusComandat produsComandat : factura.produseComandate) {
                    if (produsComandat.getProdus().getTaraOrigine().equals(tara)) {
                        totalTara += produsComandat.getCantitate() *
                                produsComandat.getProdus().getPret() *
                                (1 + produsComandat.getTaxa());
                    }
                }
            }
            if (totalTara > totalCuTaxe){
                return 0.1;
            }
        }

        return 0;

    }
}
