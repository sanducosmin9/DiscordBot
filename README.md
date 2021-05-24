# DiscordBot
 
Am ales proiectul DiscordBot pentru ProgramareAvansata.
Bot-ul nostru are urmatoarele functionalitati, in momentul de fata:
![image](https://user-images.githubusercontent.com/56782613/119351991-cd9c4e80-bca9-11eb-8efc-924055d582f0.png) + comenzile !join, !play [link] si !skip.
O sa detaliez ce face fiecare comanda in parte:
!serverinfo - creeaza un mesaj cu informatii despre server(owner, membri, numarul de membri);
!stack [query] - introduci intrebarea pe care vrei s-o pui pe StackOverflow, iar el iti returneaza primele k(k>=0, k<=3) rezultate cu titlu + link;
!wiki [query] - introduci cautarea pe care vrei s-o faci pe wikipedia, iar el iti returneaza primele k(k>=0, k<=3) rezultate cu titlu + link;
              - Ambele folosesc Programmable Search de la google. ![image](https://user-images.githubusercontent.com/56782613/119352530-777bdb00-bcaa-11eb-9249-6bb5542659d9.png)
!hello - scrie in chat un mesaj de tipul "Hello". 
!purge [numberOfMessages] - sterge numarul introdus de intrebari, pana la un maxim de 20.(scrie un mesaj pentru numere mai mari).
!trivia - porneste un trivia cu intrebari luate de pe opentdb.api. 
!userDB - se conecteaza si adauga toti userii la baza de date.
--comenzi pentru muzica--
!join - identifica voice channel-ul in care se afla user-ul si intra pe el. (scrie un mesaj daca user-ul nu este in voice channel sau daca bot-ul este ocupat). 
!play [link] - porneste muzica in voice channel-ul in care se afla. (scrie un mesaj daca nu se afla intr-un voice channel). Adauga in queue link-ul respectiv.
!skip - sare peste piesa curenta.
--to be added-
!stop - opreste muzica si sterge queue-ul. 


--eventHandlers--
cand intra cineva pe server, afiseaza un mesaj in canalul welcome.
