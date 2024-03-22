## Introducing Bibliohub: A Literary Haven for Readers and Companies

Welcome to Bibliohub, where the world of literature meets the innovation of collaborative reading. Bibliohub is a cutting-edge platform that seamlessly blends the joy of reading with the convenience of sharing, creating a vibrant literary community for both individuals and companies.

### Technologies

<img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Dark.svg" width="128px" height="128px" /> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Maven-Dark.svg"  width="128px" height="128px"/> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/PostgreSQL-Dark.svg"  width="128px" height="128px"/>

<details>
    <summary>1st Stage</summary>
    
- [x] sa se creeze o lista pe baza temei alese cu cel putin 10 actiuni/interogari care 
    se pot face in cadrul sistemului si o lista cu cel putin 8 tipuri de obiecte.
- [x] clase simple cu atribute private / protected si metode de acces
- [ ] cel putin 2 colectii diferite capabile sa gestioneze obiectele definite anterior 
    (eg: List, Set, Map etc.) dintre care cel putin una sa fie sortata; se vor folosi array-uri uni-/bidimensionale in cazul in care nu se parcurg colectiile 
    pana la data checkpoint-ului.
- [x] utilizare mostenire pentru crearea de clase aditionale si utilizarea lor 
    in cadrul colectiilor
- [ ] cel putin o clasa serviciu care sa expuna operatiile sistemului
- [ ] o clasa Main din care sunt facute apeluri catre servicii

</details>

<details>
    <summary>2nd Stage</summary>

- Extindeti proiectul din prima etapa prin realizarea persistentei utilizant o baza de date relationala si JDBC
  - [ ] sa se realizeze servicii care sa expuna operatii CRUD pentru cel putin 4 clase
  - [ ] se vor realiza servicii singleton generice pentru scrierea si citirea din baza de date
- Realizarea unui serviciu de audit
  - [ ] se va realiza un serviciu care scrie intr-un fisier de tip CSV de fiecare data cand este executata una dintre actiunile descrise in prima etapa. Structura fisierului: nume_actiune, timestamp
</details>

### Classes

1. Book (abstract)
2. User
3. Company
4. Shelf
5. Wishlist
6. Library
7. PhysicalBook (extends Books)
8. EBook (extends Books)


### Actions
#### User accessible
1. Search for books by title
2. Borrow a book for a period of time
3. Return a book early
4. View wishlisted books
5. Add books to my shelf
6. View books in my shelf
7. Search for available books
#### Librarian password only
8. Add, delete users
9. Add, delete books
10. Add, delete, edit companies


