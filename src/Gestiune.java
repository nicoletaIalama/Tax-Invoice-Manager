import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Gestiune {

    //Singleton pattern
    private static Gestiune INSTANCE = new Gestiune();

    private Gestiune() {}

    public static Gestiune getInstance() {
        return INSTANCE;
    }

    ArrayList<Produs>  produse = new ArrayList<>();
    ArrayList<Magazin> magazine = new ArrayList<>();
    HashMap<String,HashMap<String,Double>> taxe = new HashMap<>();


    void citireProduse(String nume_fisier) {
        ArrayList<String> tari = new ArrayList<>();
        Scanner scan = null;
        File f = new File(nume_fisier);
        try{
            scan = new Scanner(f);
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String line = scan.nextLine();

        //citire tari
        StringTokenizer token = new StringTokenizer(line, " ");
        token.nextToken(); //ignor primul cuvant din fisier
        token.nextToken(); //ignor al doilea cuvant din fisier
        while (token.hasMoreTokens()){
            tari.add(token.nextToken());
        }

        while (scan.hasNextLine()){
            int i;
            line = scan.nextLine();
            token = new StringTokenizer(line," ");
            String denumire = token.nextToken();
            String categorie = token.nextToken();
            for(i = 0; i < tari.size() && token.hasMoreTokens(); i++) {
                Produs p = new Produs();
                p.setDenumire(denumire);
                p.setCategorie(categorie);
                p.setPret(Double.parseDouble(token.nextToken()));
                p.setTaraOrigine(tari.get(i));
                produse.add(p);
            }
        }
    }

    void citireFacturi(String nume_fisier) {
        Scanner scan = null;
        File f = new File(nume_fisier);
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        String line = scan.nextLine();

        //citesc informatiile pentru fiecare magazin
        while (line.startsWith("Magazin:")) {
            StringTokenizer token = new StringTokenizer(line, ":");
            token.nextToken();
            //Factory pattern
            MagazinFactory magazinFactory = new MagazinFactory();
            Magazin newMagazin = magazinFactory.makeMagazin(token.nextToken());
            newMagazin.nume = token.nextToken();
            line = scan.nextLine(); //ignor linia libera
            line = scan.nextLine(); //linia corescunzatoare unei facturi

            while (line.startsWith("Factura")) {
                Factura newFactura = new Factura();
                token = new StringTokenizer(line, " ");
                newFactura.denumire = token.nextToken();
                line = scan.nextLine(); //line descriptiva sau linie goala
                if ((line.equals(""))) {
                    break; //ultima factura a unui magazin
                } else { //citire produse
                    while (scan.hasNextLine()) {
                        line = scan.nextLine();
                        if ((line.equals(""))) {
                            line = scan.nextLine(); //o alta factura sau alt magazin
                            break; //ultimul produs al unei facturi
                        } else { //linie valida de produs
                            ProdusComandat newProdusComandat = new ProdusComandat();
                            Produs newProdus = new Produs();
                            token = new StringTokenizer(line, " ");
                            newProdus.setDenumire(token.nextToken());
                            newProdus.setTaraOrigine(token.nextToken());
                            //adaug noilor informatii despre produse informatiile din citirile anterioare
                            for (Produs produs : produse)
                                if(newProdus.getDenumire().equals(produs.getDenumire()) &&
                                        newProdus.getTaraOrigine().equals(produs.getTaraOrigine())){
                                    newProdus.setPret(produs.getPret());
                                    newProdus.setCategorie(produs.getCategorie());
                                    break;
                                }
                            newProdusComandat.setProdus(newProdus);
                            HashMap<String, Double> categorieProcent = new HashMap<>();
                            categorieProcent = taxe.get(newProdus.getTaraOrigine());
                            newProdusComandat.setTaxa(categorieProcent.get(newProdus.getCategorie()));
                            Integer x = Integer.valueOf(token.nextToken());
                            newProdusComandat.setCantitate(x);
                            //adaug noul produs
                            newFactura.produseComandate.add(newProdusComandat);
                        }
                    }
                }
                //adaug noua factura
                newMagazin.facturi.add(newFactura);
            }
            //adaug noul magazin
            magazine.add(newMagazin);
        }
    }



    void citireTaxe(String nume_fisier) {
        ArrayList<String> tari = new ArrayList<>();
        Scanner scan = null;
        File f = new File(nume_fisier);
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        String line = scan.nextLine();
        StringTokenizer token = new StringTokenizer(line, " ");
        token.nextToken();
        //citesc tarile
        while (token.hasMoreTokens()){
            tari.add(token.nextToken());
        }

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            token = new StringTokenizer(line, " ");
            String categorie = token.nextToken();
            for(String tara : tari){
                HashMap<String,Double> categorieProcent = new HashMap<>();
                if(taxe.get(tara)!=null) //daca valoarea asociata tarii exista
                    categorieProcent = taxe.get(tara);
                //adaug in dictionarul <categorie, procent>
                categorieProcent.put
                        (categorie, Double.parseDouble(token.nextToken()));
                taxe.put(tara, categorieProcent);
            }

        }
    }

    void afisareInformatiiMagazine(String nume_fisier) throws IOException{
        File f = new File(nume_fisier);
        PrintWriter writer = new PrintWriter(f);

        Set<String> tari= taxe.keySet();
        Vector<String> tipuriMagazine = new Vector<>();
        tipuriMagazine.add("MiniMarket");
        tipuriMagazine.add("MediumMarket");
        tipuriMagazine.add("HyperMarket");

        DecimalFormat numberFormat = new DecimalFormat("#.####");
        DecimalFormatSymbols dfs = numberFormat.getDecimalFormatSymbols();

        dfs.setDecimalSeparator('.');
        numberFormat.setDecimalFormatSymbols(dfs);
        //Sortare magazine
        Collections.sort(magazine,new Comparator<Magazin>(){
            public int compare(Magazin m1, Magazin m2){
                if(m1.tip.compareTo(m2.tip) == 0) {
                    Double TotalFaraTaxe1 = m1.getTotalFaraTaxe();
                    Double TotalFaraTaxe2 = m2.getTotalFaraTaxe();
                    return TotalFaraTaxe1.compareTo(TotalFaraTaxe2);
                }
                else
                    return m2.tip.compareTo(m1.tip);
            }
        });


        for (String tipMagazin : tipuriMagazine){
            writer.println(tipMagazin);
            int nr1 = 0, nr2 = 0 , nr3 = 0; //variabile pentru afisare in formatul out.txt
            for (Magazin magazin : magazine){
                nr1++;
                int nrMagazine = magazine.size();
                if(tipMagazin.equals(magazin.tip)) {
                    writer.println(magazin.nume);
                    writer.println();
                    writer.println("Total " +
                            numberFormat.format(magazin.getTotalFaraTaxe()) +
                            " " + numberFormat.format(magazin.getTotalCuTaxe())
                            + " " + numberFormat.format(magazin.getTotalCuTaxeScutite()));
                    writer.println();
                    writer.println("Tara");
                    for (String tara : tari) {
                        if(magazin.getTotalTaraFaraTaxe(tara) == 0) {
                            writer.println(tara + " 0");
                        }
                        else{
                        writer.println(tara + " " +
                                numberFormat.format(magazin.getTotalTaraFaraTaxe(tara)) + " " +
                                numberFormat.format(magazin.getTotalTaraCuTaxe(tara)) + " " +
                                numberFormat.format(magazin.getTotalTaraCuTaxeScutite(tara)));
                        }
                    }
                    //Sortare facturi
                    Collections.sort(magazin.facturi,new Comparator<Factura>(){
                        public int compare(Factura f1, Factura f2){
                            Double TotalFaraTaxe1 = f1.getTotalCuTaxe();
                            Double TotalFaraTaxe2 = f2.getTotalCuTaxe();
                            return TotalFaraTaxe1.compareTo(TotalFaraTaxe2);
                            }
                    });

                    for (Factura factura : magazin.facturi){
                        nr2++;
                        writer.println();
                        int nrFacturi = magazin.facturi.size();
                        writer.println(factura.denumire);
                        writer.println();
                        writer.println("Total " +
                                numberFormat.format(factura.getTotalFaraTaxe()) + " "
                                + numberFormat.format(factura.getTotalCuTaxe()) );
                        writer.println();
                        writer.println("Tara");
                        int nrTari = tari.size();
                        for (String tara : tari) {
                            nr3++;
                            if (factura.getTotalTaraFaraTaxe(tara) == 0) {
                                writer.print(tara + " 0");
                                if(nr2 < nrFacturi || nr3 < nrTari)
                                    writer.println();
                            } else {
                                writer.println(tara + " " +
                                        numberFormat.format(factura.getTotalTaraFaraTaxe(tara))
                                        + " " +
                                        numberFormat.format(factura.getTotalTaraCuTaxe(tara)));

                            } }
                    }
                    if(nr1 < nrMagazine)
                        writer.println();
                }
            }
        }
        writer.close();

    }

}
