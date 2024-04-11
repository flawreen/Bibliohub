## Introducing Bibliohub: A Literary Haven for Readers and Companies

Welcome to Bibliohub, where the world of literature meets the innovation of collaborative reading. Bibliohub is a cutting-edge platform that seamlessly blends the joy of reading with the convenience of sharing, creating a vibrant literary community for both individuals and companies.

### Technologies
<img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Dark.svg" width="128px" height="128px" /> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Maven-Dark.svg"  width="128px" height="128px"/> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/PostgreSQL-Dark.svg"  width="128px" height="128px"/> 

### Design Patterns
<div style="display:flex;">

 <img src="https://refactoring.guru/images/patterns/cards/singleton-mini-2x.png">
 <label>Command</label>

 <img src="https://refactoring.guru/images/patterns/cards/command-mini-2x.png">
 <label>Factory</label>

 <img src="https://refactoring.guru/images/patterns/cards/factory-method-mini-2x.png">
 <label>Singleton</label>
</div>

### Preview:
<img src="https://github.com/flawreen/Bibliohub/assets/83332450/84ae0648-daed-4410-a90a-84c931abe788" width="400"/> <img src="https://github.com/flawreen/Bibliohub/assets/83332450/c4f3c6b5-4bdd-4fb4-a8f3-c3f593c4c788" width="400"/> 

<details>
    <summary>1st Stage</summary>
    
- [x] sa se creeze o lista pe baza temei alese cu cel putin 10 actiuni/interogari care 
    se pot face in cadrul sistemului si o lista cu cel putin 8 tipuri de obiecte.
- [ ] clase simple cu atribute private / protected si metode de acces
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


### See task management in the Issues tab


### Classes
1. Book (abstract)
2. User
3. Company
4. Shelf
5. Wishlist
6. Library
7. PhysicalBook (extends Book)
8. EBook (extends Book)


### Actions
#### User accessible
1. Borrow a book
2. Return a book
3. View wishlisted books  ->when user logs in display "x wishlist books available to borrow"
4. Add books to my wishlist
5. Remove books from wishlist
6. View books in my shelf
7. Search available books by title
8. View available books
#### Librarian password only
1. Add, delete users
2. Add, delete books
3. Add, delete companies
4. Add, delete books from library
5. Make books available in library

### Extra
1. SeedData file
2. Factory pattern for Book class
3. Command pattern for menu actions
4. Singleton pattern for services
