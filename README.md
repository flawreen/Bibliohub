## Introducing Bibliohub: A Literary Haven for Readers and Companies

Welcome to Bibliohub, where the world of literature meets the innovation of collaborative reading. Bibliohub is a cutting-edge platform that seamlessly blends the joy of reading with the convenience of sharing, creating a vibrant literary community for both individuals and companies.

### Technologies

<img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Dark.svg" width="128px" height="128px" /> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Maven-Dark.svg"  width="128px" height="128px"/> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/PostgreSQL-Dark.svg"  width="128px" height="128px"/> 
![devicon--junit-wordmark](https://github.com/flawreen/Bibliohub/assets/83332450/b6e08bec-aebc-478a-b2fc-992cef30f740)<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
	<path fill="#dc514a" d="M108.79 44.601c-4.777-.092-9.332 1.169-13.23 4.77c-8.154 7.546-8.398 20.46-.522 28.283c1.42 1.41 3.107 2.71 4.959 3.502c.485-1.627.39-3.593.236-5.525c-.036-.039-.072-.075-.102-.118c-.516-.768-.134-2.785-.134-3.7c3.26.576 5.908 2.367 9.395 1.328c3.41-1.019 4.835-5.936 1.827-8.269c-1.815-1.405-4.513-1.213-6.654-.954c-1.284.154-2.894.628-3.78-.741c-.527-.804.015-2.781.103-3.693l.08-.792c.228-2.8.596-5.625.596-8.425h13.833V45.47c-2.221-.507-4.437-.827-6.608-.868z" />
	<path fill="#23a161" d="M115.192 45.535v8.438h-10.049l-.52 6.489c2.229-.175 4.294-.569 6.522-.043c1.528.357 2.96 1.078 4.024 2.249c2.96 3.263 2.374 9.386-1.024 12.14c-.963.781-2.065 1.292-3.23 1.619a10.789 10.789 0 0 1-3.177.485c-.388.02-.776.033-1.161.033a18.5 18.5 0 0 1-4.96-.668c-.405-.114-1.037-.275-1.436-.57l-.259-.059l-.131 5.449c10.354 5.49 23.55-.382 27.263-11.159c3.14-9.108-1.722-22.01-11.862-24.403" />
	<path fill="#737373" d="M64.902 49.977c-1.357.53-.998 4.087.776 3.438c1.556-.57 1.047-4.15-.776-3.438m-57.814.29v21.287c0 2.518.72 6.45-.968 8.564c-1.59 1.996-3.93 1.26-6.08 1.17c0 .45-.151 1.233.132 1.61c.525.706 2.244.466 3 .466c2.455-.004 4.752-1.14 5.46-3.633c.907-3.204.41-6.952.41-10.254v-19.21zm8.87 0v13.889c0 2.841-.242 5.817 1.095 8.436c3.175 6.225 14.266 6.12 17.614.128c1.451-2.604 1.257-5.687 1.257-8.564V50.267h-2.086v14.02c0 2.53.318 5.221-.955 7.526c-2.73 4.946-11.449 4.77-14.016-.259c-1.221-2.392-.95-5.193-.95-7.788V50.267Zm54.025 8.438h2.873v11.679c0 1.898-.055 4.143 1.442 5.567c1.774 1.691 4.615 1.238 6.776.792v-1.555c-1.507.064-3.319.693-4.696-.22c-1.564-1.038-1.568-3.047-1.568-4.71V58.704h5.743v-1.687H74.81v-4.672c-2.176.008-1.002 2.985-2.053 4.23c-1.007 1.198-2.77.087-2.774 2.13M44.54 59.744h-.132l-.262-2.726H42.45v19.726h1.958V67.53c0-2.157-.071-4.413 1.019-6.359c1.73-3.104 7.664-3.963 9.861-.907c1.086 1.516.994 3.8.994 5.579v10.9h1.827v-10.9c0-2.121.175-4.433-.9-6.359c-2.225-3.98-10.609-3.861-12.67.259m19.833-2.726v19.725h1.958V57.016Zm0 0" />
</svg>

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
1. Search for books by title
2. Borrow a book
3. Return a book
4. View wishlisted books
5. Add books to my shelf
6. View books in my shelf
7. Search for available books
#### Librarian password only
8. Add, delete users
9. Add, delete books
10. Add, delete, edit companies

### Extra
1. SeedData file
2. Unit tests for each action
