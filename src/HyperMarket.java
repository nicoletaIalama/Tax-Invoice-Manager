public class HyperMarket extends Magazin {
    public HyperMarket() { tip = "HyperMarket" ;}

    @Override
    public double calculScutiriTaxe() {
        int i;
        double TotalCuTaxe = getTotalCuTaxe();
        TotalCuTaxe /= 10;
       for(Factura factura : facturi)
           if(factura.getTotalCuTaxe() > TotalCuTaxe)
                return 0.01;
       return 0;
    }
}
