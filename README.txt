Ialama Nicoleta
323CC
Grad de dificultate: mediu spre greu
Timpul alocat rezolvarii temei: 4 zile (5-6 ore/zi)

Modul de implemtare:

Proiectul este realizat in IntelliJ IDEA 2017.2.5.
Am folosit acest IDE in primul rand pentru ca il cunosc mai bine, din obisnuinta.
In al doilea rand:
https://wheredatapp.com/blog/2015/11/19/why-intellij-idea-is-better-than-eclipse

Am realizat Task1.
Toate clasele mentionatate in cerinta sunt implementate.
In plus am creat clasa MagazinFactory pentru a utiliza Factory Pattern.
Clasa Gestiune este implementa folosind Singleton pattern.
In clasa Gestiune am implemtat 3 metode pentru citirea fiecarui fisier si o
metoda pentru afisarea in fisierul "out.txt."
Metoda citireFacturi asociaza toate informatiile obtinute din citireTaxe
si citireProduse.
Metoda afisareInformatiiMagazine are 3 variabile nr pentru ca
afisarea sa fie informatul cerut.
In aceasta metoda sunt realizate sortarile corespunzatoare pentru
magazine respectiv facturi.
Am folosit DecimalFormat pentru afisarea cu cel mult 4 zecimale si
DecimalFormatSymbols pentru a avea ca separator caracterul '.'(implicit ',').
Data "tip" este mostenita din clasa abstracta Magazin deoarece
este un atribut comun al celor 3 subclase.
Clasa test apeleaza cele 4 metode din Gestiune.
