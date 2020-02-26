import java.io.IOException;

public class test {
    public static void main (String args[]) throws IOException{
        Gestiune gestiune = Gestiune.getInstance();
        gestiune.citireProduse("produse.txt");
        gestiune.citireTaxe("taxe.txt");
        gestiune.citireFacturi("facturi.txt");
        gestiune.afisareInformatiiMagazine("out.txt");
    }
}
