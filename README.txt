Arhitectura:
Interfete:
IMagazin – interfata cu metodele de calcul a diverselor tipuri de sume pentru un magazin
Clase abstracte:
Magazin (implementeaza IMagazin) – clasa abstracta corespunzatoare interfetei IMagazin
Clase concrete(instantiabile):
1. Produs – defineste un produs ce poate fi regasit pe o factura
2. MiniMarket (extinde Magazin) – contine facturile asociate unui magazin de dimensiuni mici
3. MediumMarket (extinde Magazin) – contine facturile asociate unui magazin de dimensiuni medii
4. HyperMarket (extinde Magazin) – contine facturile asociate unui magazin de dimensiuni mari
5. Factura – contine produsele facturate de un magazin la un moment dat
6. ProdusComandat – defineste linia unei facturi
7. Gestiune – contine datele aplicatiei: produse, facturi, magazine, taxe etc. si metodele acesteia.
Descriere clase si interfete – schelet minimal
Produs
Date private:
● denumire
● categorie
● taraOrigine
● pret
Metode:
● setDenumire(String)
● getDenumire()
● setCategorie(String)
● getCategorie()
● setTaraOrigine(String)
● getTaraOrigine()
● setPret(double)
● getPret()
Obiectele de tipul Produs vor fi create in urma citirii si parsarii fisierului produse.txt.
Fisierul va contine pe prima linie denumirea tarilor separate prin spatiu (primele 2 cuvinte vor fi ignorate fiind
de ordin descriptiv), pe prima coloana denumirile produselor existente si pe coloana a doua categoria asociata
fiecarui produs. Restul informatiilor vor fi numere de tip double separate prin spatiu reprezentand pretul
neimpozitat al produselor caracterizate de denumirea liniei pe care se afla si tara coloanei asociate.
ProdusComandat
Date private:
● produs - obiect de tip Produs
● taxa
● cantitate

Metode:
● setProdus(Produs)
● getProdus()
● setTaxa(Double)
● getTaxa ()
● setCantitate(int)
● getCantitate()
Obiectele de tipul ProdusComandat vor fi create in urma citirii si parsarii unei linii din fisierul facturi.txt.
Fisierul va contine facturile emise de o serie de magazine.
Daca o linie incepe cu cuvantul cheie Magazin inseamna ca facturile ce urmeaza in continuare sunt asociate
unui nou magazin de tipul MiniMarket in cazul primul magazin din fisier avand numele MegaImage1. Antetul
recunoasterii unui magazin nou este: Magazin:TipMagazin:NumeMagazin, unde TipMagazin este MiniMarket,
MediumMarket sau HyperMarket iar nume magazin este denumirea magazinului. Se garanteaza ca nu vor
exista 2 magazine care sa aiba aceeasi denumire.
O factura noua va fi semnalizata cu cuvantul cheie Facturai(unde i este indexul facturii unui magazin). Facturile
vor fi despartite de un rand liber intre ele. Fiecare factura va avea pe prima linie antetul acesteia:
DenumireProdus Tara Cantitate. In continuare fiecare linie va contine denumirea produsului facturat, tara de
provenienta si cantitatea facturata, toate separate prin spatiu. Pentru fiecare astfel de linie se va crea un obiect
de tip ProdusComandat.
Factura
Date:
● denumire - obtinuta din fisier(prima linie a facturii). In cazul de mai sus: Factura1, Factura2 pentru
MegaImage1 si Factura1 pentru Cora5
● Vector<ProdusComandat>
Metode:
● getTotalFaraTaxe() – va calcula totalul facturii fara taxe
● getTotalCuTaxe() - va calcula totalul facturii cu taxe
● getTaxe() - va calcula totalul taxelor facturii
● getTotalTaraFaraTaxe(String) - va calcula totalul facturii fara taxe pentru o anumita tara
● getTotalTaraCuTaxe(String) - va calcula totalul facturii cu taxe pentru o anumita tara
● getTaxeTara(String) - va calcula totalul taxelor facturii pentru o anumita tara
Obiecte de tipul Factura vor fi create in urma citirii si parsarii unei facturi din fisierul facturi.txt. Pentru fiecare
factura din fisier se va crea un obiect de tip Factura.
IMagazin
Metodele interfetei:
● getTotalFaraTaxe() – calcul total facturi magazin fara taxe
● getTotalCuTaxe() - calcul total facturi magazin cu taxe
● getTotalCuTaxeScutite() - calcul total facturi magazin cu scutirea de taxe corespunzatoare
Obs. Daca nu exista scutiri de taxe atunci metoda intoarce aceeasi valoare cu getTotalCuTaxe()
● getTotalTaraFaraTaxe(String) - calcul total facturi magazin fara taxe, pentru o anumita tara
● getTotalTaraCuTaxe(String) - calcul total facturi magazin cu taxe, pentru o anumita tara
● getTotalTaraCuTaxeScutite(String) - calcul total facturi magazin cu scutire de taxe, pentru o anumita
tara(procentajul scutirii este cel calculat de metoda calculScutiriTaxe() si se aplica sumei returnate de
metoda getTotalTaraCuTaxe(String))
● calculScutiriTaxe() – calculeaza procentajului taxelor de care sunt scutite magazinele
Magazin – clasa abstracta, implementeaza IMagazin
Date:
● nume– obtinut din fisier(MegaImage1 si Cora5)
● Vector<Factura>
Metode: Implementeaza toate metodele din IMagazin, mai putin metoda calculScutiriTaxe() care ramane
abstracta!

MiniMarket, MediumMarket, HyperMarket – clase concrete, mostenesc Magazin
Date:
● Tip - MiniMarket, MediumMarket, HyperMarket
Metode:
● calculScutiriTaxe() - calculeaza procentajul reducerii aplicate sumei totale a magazinului (suma
returnata de metoda getTotalCuTaxe()) in functie de indeplinirea urmatoarelor criterii:
○ MiniMarket –10% daca exista o tara penru care suma produselor comandate (inclusiv taxe)
depaseste 50% din totalul vanzarilor realizate de catre magazin (inclusiv taxe)
○ MediumMarket – 5% daca exista o categorie de produse pentru care suma produselor
comandate(inclusiv taxe) depaseste 50% din totalul vanzarilor realizate de catre magazin
(inclusiv taxe)
○ HyperMarket –1% daca exista cel putin o factura a carei suma totala(inclusiv taxe) depaseste
10% din totalul vanzarilor realizate de catre magazin (inclusiv taxe)

Obiecte de tipul MiniMarket, MediumMarket sau HyperMarket vor fi create in urma citirii si parsarii fisierului
Facturi.txt prezentat mai jos.
Atentie! Pentru fiecare magazin se va crea un obiect corespunzator de tip MiniMarket, MediumMarket sau
HyperMarket, in functie de tipul magazinului, folosind Factory pattern . [1]
Gestiune
Date:
● produse - lista obiecte Produs
● magazine – lista de obiecte Magazin – modificare a enuntului!!
● taxe - dictionar de perechi (tara, dictionar de perechi (categorie, procent))
Legatura dintre o tara, o categorie de produs si taxa asociata combinatiei in cauza va fi retinuta sub forma
unuidictionar in urma procesarii fisierului taxe.txt exemplificat mai jos:
Fisierul contine pe prima linie denumirea tarilor separate prin spatiu(primul cuvant va fi ignorat fiind de ordin
descriptiv) si pe prima coloana categoriile produselor existente. Restul informatiilor vor fi numere intregi
separate prin spatiu reprezentand procentul cu care se vor impozita produsele caracterizate de categoria liniei
pe care se afla si tara coloanei asociate.
Fisierul in cauza specifica ca produsele ce fac parte din categoria Patiserie si sunt importate din Italia vor fi
impozitate cu 15% din pretul specificat in fisierul produse.txt. Astfel o factura ce va contine produsul Paine
importat din Italia va avea pretul final cu care urmeaza a fi achizitionat(dupa impozitare) de 2.415(2.1 * 115%).
Clasa Gestiune este implementata folosind Singleton pattern.

In fisierul out.txt:
1. Prima linie va contine tipul magazinului
2. In continuare va fi detaliat fiecare magazin avand tipul mentionat. Astfel linia a doua va contine numele
primului magazin considerat in ordinea sumelor totale fara taxe aplicate urmat de un rand liber.
3. Linia urmatoare va incepe cu cuvantul „Total” si va fi urmata de 3 valori numerice si de un rand liber:
● Total magazin fara taxe
● Total magazin cu taxe
● Total magazin cu taxe scutite

4. In continuare urmeaza o linie ce va contine cuvantul cheie „Tara” urmat de atatea randuri cate tari sunt
identificate in fisierul taxe.txt. Fiecare rand va incepe cu denumirea tarii si va fi urmata de 3 valori
numerice si de un rand liber:
● Total tara fara taxe
● Total tara cu taxe
● Total tara cu taxe scutite
In cazul in care nu exista produse comandate dintr-o anumita tara se va afisa valoarea 0.
5. In continuare va fi detaliata fiecare factura a magazinului in cauza. Astfel linia urmatoare va contine
numele primei facturi considerata in ordinea sumelor totale cu taxe urmata de un rand liber. Pentru
fiecare factura se vor afisa aceleasi informatii ca cele afisate pentru magazin (pasii 3, 4 si 5 de mai sus,
cu mentiunea ca nu vor fi specificate sumele totale cu taxe scutite, avand in vedere ca pentru clasa
Factura nu avem definite metodele asociate)
6. In urma afisarii tuturor informatiilor necesare legate de toate magazinele cu toate facturile din categoria
MiniMarket se va trece la afisarea acelorasi informatii pentru categoriile MediumMarket si HyperMarket.