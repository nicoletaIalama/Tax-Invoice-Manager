public class MagazinFactory {
    public Magazin makeMagazin(String tipMagazin){
        Magazin newMagazin = null;
        if(tipMagazin.equals("MiniMarket")){
            newMagazin = new MiniMarket();
        }
        else if(tipMagazin.equals("MediumMarket")){
            newMagazin = new MediumMarket();
        }
        else if(tipMagazin.equals("HyperMarket")){
            newMagazin = new HyperMarket();
        }
        return newMagazin;
    }
}
