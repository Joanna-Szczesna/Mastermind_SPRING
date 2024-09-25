# Mastermind in space


prototype view:
<img src="/images/MasterMindPrototype.png" width="720" height="512">



[English version](#table-of-content)

[Polska wersja](#spis-treści)


## Table of content

1. [History](#history)
2. [Rules](#rules)
3. [Console Version](#console-version)
    - [How to run?](#how-to-run)
    - [Example of game](#an-example-of-a-game-that-ended-in-victory)
    - [Handling invalid input](#handling-invalid-input)
4. [Web Version](#web-version)
4. [Used Technology](#used-technology-and-lib)
4. [Use Case Diagram](#use-case-diagram)
5. [Sequence Diagrams](#sequence-diagrams)
6. [Class Diagram](#class-diagram)
7. [Functional requirements](#functional-requirements)


## History

A powerful wizard wants to destroy the Earth.
You, as the last of the noble ones, received a chance to save the Earth.
The wizard has a challenge for you. You must crack his planetary code.
Planets arranged in a specific order will give you victory.
You have up to 10 tries and satellites with hints.
If you fail, for Earth there will be no rescue...


## Rules

The winning game ends when the secret code is guessed.

In the basic version, the code consists of 5 digits from the < 1, 8 > range.

Digits may repeat. The order of the digits is important. Hint is returned after each guess. The clue is worded in such a way that it cannot be assigned to a specific digit in the code.

**Hints:**

![rules](/images/screens/console_game/cg_hint.JPG)

**Black** means that the digit and its position are correct.
The number informs about the number of fields of the code provided by the user that meet this condition.

**White** means that the digit is correct but is in the wrong place.
The number informs about the number of fields of the code provided by the user that meet this condition.

In the basic version, there are a maximum of 10 attempts to guess the secret code.

Regardless of whether the game ends by guessing the code or by exhausting possible attempts, a secret code is displayed at the end of the game.

In the image below, the numbers that the clue applies to are color-coded.

![rules](/images/screens/console_game/Rules_White_black_hints_color.JPG)


## Console version

### How to run?

Unix:

`./gradlew clean build`

`java -jar build/libs/Mastermind-1.0-SNAPSHOT.jar`

### An example of a game that ended in victory

![sample of game](/images/screens/console_game/cg_part_1.JPG)
![sample of game](/images/screens/console_game/cg_part_2.JPG)
![sample of game](/images/screens/console_game/cg_part_3.JPG)


### Handling invalid input

**incorrect chars - letters against digits:**


![incorrect chars](/images/screens/console_game/cg_incorrect_chars.JPG)

**incorrect length of code:**


![incorrect length](/images/screens/console_game/cg_incorrect_length.JPG)


**numbers outside range:**


![outside range](/images/screens/console_game/cg_planet_numbers_outside_range.JPG)


## Web Version

<img src="/images/screens/angular/view.JPG" width="720">

Unix:

`java -jar build/libs/Mastermind-1.0-SNAPSHOT.jar --http`

[ tbc ]

## Used Technology and lib

- Java 17
- JUnit 5
- Pitest 1.9
- Google truth 1.1
- Gradle
- Gimp
- Figma
- ArchUnit
- Spring

## Use Case Diagram 

![Use Case Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/use_case.puml)

## Sequence Diagrams

[tbc]

PL version: [Diagram sekwencji](#diagram-sekwencji)

## Class Diagram

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/class_diagram.puml)


## Functional requirements

|   nb            |group name:                          |priority                         |
|----------------|-------------------------------|-----------------------------|
|**1**| **Reply after trying to guess the code** |**Essential**      |

REQ_FUNC_1.1. The return answer is based on the current guess attempt

REQ_FUNC_1.2. Feedback tells how many correct colors were placed in the correct position (expressed by black hint markers)

REQ_FUNC_1.3. The feedback tells how many correct colors were placed in the wrong position (expressed by white hint markers)

REQ_FUNC_1.4. Feedback provides information on how many incorrect colors were placed in the wrong position (expressed by the lack of a hint marker)

REQ_FUNC_1.5. The feedback cannot suggest which color (element), which position in the code it applies to (example: the player in two consecutive attempts places exactly the same combination of colors in the same positions, gets successively feedback expressed in hint markers of the same type, however, they are arranged in a different order)

REQ_FUNC_1.6. The return answer determines whether the game ends (possible states: win, lose or continuation of the game, i.e. making another attempt to guess)


|   nb             |group name:                         |priority                        |
|----------------|-------------------------------|-----------------------------|
|**2**| **Starting the game (initial conditions)** |**Essential**      |

REQ_FUNC_2.1. The game starts as a result of the user's action by selecting the "START" button

REQ_FUNC_2.2. The code consisting of colors in certain positions is randomized once at the start of the game and is constant throughout the game

REQ_FUNC_2.3. The drawn code is not visible to the player


|   nb             |group name:                         |priority                         |
|----------------|-------------------------------|-----------------------------|
|**3**| **Ending the game (end conditions)** |**Essential**      |

REQ_FUNC_3.1. The game ends when the current guessing attempt ends with deciphering the drawn code (win)

REQ_FUNC_3.2. The game ends when the player fails to guess the code in the last, maximum 14th attempt (lose)

REQ_FUNC_3.3. The game ends when the user selects the button "Blow up Earth!" (defeat)

REQ_FUNC_3.4. At the end of the game, the code drawn at the beginning of the game is presented (revealed).


|   nb             |group name:                         |priority                         |
|----------------|-------------------------------|-----------------------------|
|**4**| **Determining the code** |**Essential**      |

REQ_FUNC_4.1. The code consists of a combination of colors in certain positions

REQ_FUNC_4.2. The user selects the number of positions (number of fields) of the code to be guessed

REQ_FUNC_4.3. The code may consist of 5-7 items (fields)

REQ_FUNC_4.1. The number of code fields is known to the user

REQ_FUNC_4.2. The number of possible colors is fixed and amounts to 8 colors

REQ_FUNC_4.3. The colors in the code may be repeated

|   nb            |group name:                          |priority                         |
|----------------|-------------------------------|-----------------------------|
|**5**| **Possible modifications by the user** |**Desirable**      |

REQ_FUNC_5.1. Before starting the game, the application allows you to define the number of code fields (5-7 fields)

REQ_FUNC_5.2. Before starting the game, the application allows you to define the number of possible attempts (10-14)

REQ_FUNC_5.3. [extension] Before starting the game, the application allows you to load a saved game

REQ_FUNC_5.4. [extension] After starting the game, the application allows you to save the game

REQ_FUNC_5.5. [extension] The game save contains a name given by the user

|   nb            |group name:                          |priority                         |
|----------------|-------------------------------|-----------------------------|
|**6**| **Game scoring** |**Desirable** |

REQ_FUNC_6.1. Each game is scored

REQ_FUNC_6.2. Scoring takes place at the end of the game

REQ_FUNC_6.3. For each color placed in the right position, the user gets 2 points

REQ_FUNC_6.4. For a correct color placed in an incorrect position, the user gets 1 point

REQ_FUNC_6.5. For deciphering the entire code, the user receives an additional number of points equal to the difference between the maximum number of attempts (14) and the number of attempts in which the code was successfully deciphered

REQ_FUNC_6.6. The result of the game is recorded in the ranking of all games

|                                                                                                                                      |Scoring examples                          |
|--------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| **Victory**                                                                                                                          | |
|| for each planet in the right place, the number of planets arranged * 2 points (correct color and place)                              
|| maximum number of attempts (always 14) - how many attempts were made to guess                                                        |
|| `Example: successfully deciphered the code on the third try:`                                                                        |
|| `5 planets * 2 points = 10 points`                                                                                                   |
|| `14 - 3 attempts = 11 points`                                                                                                        |
|| `Sum 10 + 11 = 21 points for game`                                                                                                   |
| **Defeat**                                                                                                                           |  |
|| for each planet in the right place, the number of planets arranged * 2 points (correct color and place)                              |
|| for the correct color of the planet in the wrong position, the number of planets * 1 point (correct color, wrong position)           |
|| `Example: two planets in the correct color and position, one in the correct color and position, 2 in the correct color and position` |
|| `2 planets * points(correct color and place) = 4 points`                                                                             |
|| `1 planet * 1 point (correct color, wrong place) = 1 points`                                                                         |
|| `2 planets * 0 point (wrong color wrong place) = 0 points`                                                                           |
|| `Sum 4 + 1 + 0 = 5 points for game`                                                                                                  |


|   nb           |group name:                          |priority                         |
|----------------|-------------------------------|-----------------------------|
|**7**| **Registration** |**Desirable** |

REQ_FUNC_7.1. To register, it is required to provide an e-mail address, username and password

REQ_FUNC_7.2. The fields e-mail address, username and password are validated

REQ_FUNC_7.3. Password requirement: alphanumeric characters and special characters

REQ_FUNC_7.4. Minimum password length: 8 characters

REQ_FUNC_7.5. At least one lowercase and one uppercase letter in the password

REQ_FUNC_7.6. At least one digit in the password

REQ_FUNC_7.7. At least one special character in the password

REQ_FUNC_7.8. At the time of registration, the user receives feedback whether his password meets the requirements

REQ_FUNC_7.9. The password feedback contains information which of the requirements is not met yet

REQ_FUNC_7.10. The username can consist of alphanumeric characters. Special characters are not allowed

REQ_FUNC_7.11. The e-mail address must contain the "@" sign

REQ_FUNC_7.12. After verifying that the e-mail address, username and password fields are correct, an e-mail is sent to the user's e-mail address confirming the registration

REQ_FUNC_7.13. Confirmation of registration by the user is necessary to use the login option



|   nb            |group name:                          |priority                         |
|----------------|-------------------------------|-----------------------------|
|**8**| **Login** |**Desirable** |

REQ_FUNC_8.1. The application allows the user to log in

REQ_FUNC_8.2. Login using the username and the password set by him

REQ_FUNC_8.3. Correct login is a prerequisite to use the ability to load the game and save




## Spis treści

1. [Historia](#historia)
2. [Zasady](#zasady)
3. [Wersja konsolowa](#wersja-konsolowa)
   - [Jak uruchomić?](#jak-uruchomić)
   - [Przykład rozgrywki](#przykład-rozgrywki)
   - [Obsługa niepoprawnych wejść](#obsługa-niepoprawnych-wejść)
4. [Wersja web](#wersja-web)
4. [Użyte technologie](#użyte-technologie)
4. [Diagram przypadków użycia](#diagram-przypadków-użycia)
5. [Diagram sekwencji](#diagram-sekwencji)
6. [Diagram klas](#diagram-klas)
7. [Wymagania funkcjonalne](#wymagania-funkcjonalne)


## Historia

Potężny czarodziej chce zniszczyć Ziemię.
Tobie, jako ostatniemu ze szlachetnych daje szansę by Ziemia ocalała.
Proponuje byś złamał jego planetarny kod.
Planety ułożone w określonym porządku dadzą Ci zwycięstwo.
Masz maksymalnie 10 prób i satelity z podpowiedziami.
Gdy zawiedziesz dla Ziemi nie będzie już ratunku.

## Zasady

Gra zwycięska kończy się po odgadnięciu sekretnego kodu.

W wersji podstawowej kod składa się z 5-ciu cyfr z zakresu < 1 , 8 >

Cyfry mogą się powtarzać. Istotna jest kolejność cyfr. Po każdej próbie odgadnięcia zwracana jest wskazówka.
Wskazówka formułowana jest w taki sposób, by nie można było rozstrzygnąć, której konkretnie cyfry z kodu dotyczy.

**Wzkazówki:**

![rules](/images/screens/console_game/cg_hint.JPG)

**Black** oznacza że cyfra jak i jej położenie jest prawidłowe.
Cyfra informuje o ilości pól kodu podanego przez użytkownika które spełniają te warunek.

**White** oznacza że cyfra jest prawidłowa, natomiast jest w niewłaściwym miejscu.
Cyfra informuje o ilości pól kodu podanego przez użytkownika które spełniają te warunek.

W wersji bazowej jest maksymalnie 10 prób by odgadnąć sekretny kod.

Niezależnie od tego czy gra skończy się przez odgadniecie kodu, czy poprzez wyczerpanie możliwych prób na końcu rozgrywki wyświetlany jest sekretny kod.

Na poniższym obrazku oznaczono kolorami, których cyfr wskazówka dotyczy.

![rules](/images/screens/console_game/Rules_White_black_hints_color.JPG)


## Wersja konsolowa

### Jak uruchomić

Unix:

`./gradlew clean build`

`java -jar build/libs/Mastermind-1.0-SNAPSHOT.jar`

### Przykład rozgrywki

![sample of game](/images/screens/console_game/cg_part_1.JPG)
![sample of game](/images/screens/console_game/cg_part_2.JPG)
![sample of game](/images/screens/console_game/cg_part_3.JPG)


### Obsługa niepoprawnych wejść

**niepoprawne znaki - litery zamiast cyfr:**


![incorrect chars](/images/screens/console_game/cg_incorrect_chars.JPG)

**niepoprawna długość kodu:**


![incorrect length](/images/screens/console_game/cg_incorrect_length.JPG)


**cyfry z poza zakresu:**


![outside range](/images/screens/console_game/cg_planet_numbers_outside_range.JPG)


## Wersja web

Unix:

`java -jar build/libs/Mastermind-1.0-SNAPSHOT.jar --http`

[ cdn ]

## Użyte technologie

- Java 17
- JUnit 5
- Pitest 1.9
- Google truth 1.1
- Gradle
- Gimp
- Figma
- ArchUnit
- Spring

## Diagram przypadków użycia

![Use Case Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/use_case.puml)

## Diagram sekwencji

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/sequence_diagram.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/sequence_diagram_save_game.puml)

## Diagram klas

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Joanna-Szczesna/Mastermind_SPRING/main/diagrams/class_diagram.puml)

## Wymagania funkcjonalne

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**1**| **Odpowiedz zwrotna po próbie odgadnięcia kodu** |**Niezbędne**      |


REQ_FUNC_1.1. Odpowiedz zwrotna formułowana jest w oparciu o bieżącą próbę odgadnięcia

REQ_FUNC_1.2. Odpowiedz zwrotna przekazuje informację ile poprawnych kolorów było umieszczonych we właściwej pozycji (wyrażone przez czarne znaczniki podpowiedzi)

REQ_FUNC_1.3. Odpowiedz zwrotna przekazuje informację ile poprawnych kolorów było umieszczonych w nieprawidłowej pozycji (wyrażone przez białe znaczniki podpowiedzi)

REQ_FUNC_1.4. Odpowiedz zwrotna przekazuje Informację ile niepoprawnych kolorów było umieszczonych w nieprawidłowej pozycji (wyrażone przez brak znacznika podpowiedzi)

REQ_FUNC_1.5. Odpowiedz zwrotna nie może sugerować którego koloru (elementu), której pozycji w kodzie dotyczy (przykład: gracz w dwóch kolejnych próbach układa dokładnie tę samą kombinację kolorów w tych samych pozycjach, dostaje kolejno odpowiedzi zwrotne wyrażone w znacznikach podpowiedzi, których rodzaj jest ten sam, jednak są ułożone w różnej kolejności)

REQ_FUNC_1.6. Odpowiedz zwrotna określa czy gra się kończy (możliwe stany: wygrana, przegrana lub kontynuacja rozgrywki tj. podejmowanie kolejnej próby odgadnięcia)

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**2**| **Rozpoczęcie rozgrywki (warunki początkowe)** |**Niezbędne**      |

REQ_FUNC_2.1. Rozpoczęcie rozgrywki następuje w wyniku akcji ze strony użytkownika przez wybór przycisku "START"

REQ_FUNC_2.2. Kod składający się z kolorów w określonych pozycjach jest losowany jednorazowo przy rozpoczęciu rozgrywki i jest stały w trakcie całej gry

REQ_FUNC_2.3. Wylosowany kod nie jest widoczny dla gracza

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**3**| **Zakończenie rozgrywki (warunki końcowe)** |**Niezbędne**      |

REQ_FUNC_3.1. Gra kończy się w momencie gdy bieżąca próba odgadnięcia zakończy się rozszyfrowaniem wylosowanego kodu (wygrana)

REQ_FUNC_3.2. Gra kończy się gdy graczowy nie powiedzie się odgadniecie kodu w ostatniej, maksymalnie 14stej próbie (przegrana)

REQ_FUNC_3.3. Gra kończy się gdy użytkownik wybierze przycisk "Automatyczne wysadzenie Ziemii! Nie ruszaj!" (przegrana)

REQ_FUNC_3.4. W momencie zakończenia gry prezentowany(ujawniany) jest kod wylosowany na początku rozgrywki

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**4**| **Ustalanie kodu** |**Niezbędne**      |

REQ_FUNC_4.1. Na kod składa się kombinacja kolorów w określonych pozycjach

REQ_FUNC_4.2. Użytkownik wybiera ilość pozycji (ilość pól) kodu, którą będzie zgadywał

REQ_FUNC_4.3. Kod może składać się z 5-7 pozycji (pól)

REQ_FUNC_4.1. Ilosć pól kodu jest znana użytkownikowi

REQ_FUNC_4.2. Ilość możliwych kolorów jest stała i wynosi 8 kolorów

REQ_FUNC_4.3. Kolory w kodzie mogą się powtarzać

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**5**| **Możliwe modyfikacje ze strony użytkownika** |**Opcjonalne**      |

REQ_FUNC_5.1. Przed rozpoczęciem rozgrywki aplikacja umożliwia definiowanie ilości pól kodu (5-7pól)

REQ_FUNC_5.2. Przed rozpoczęciem rozgrywki aplikacja umożliwia definiowanie ilości możliwych prób (10-14)

REQ_FUNC_5.3. [rozszerzenie] Przed rozpoczęciem rozgrywki aplikacja umożliwia wczytanie zapisanego stanu gry

REQ_FUNC_5.4. [rozszerzenie] Po rozpoczęciu rozgrywki aplikacja umożliwia zapisanie stanu gry

REQ_FUNC_5.5. [rozszerzenie] Zapis gry zawiera nazwę nadaną przez użytkownika

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**6**| **Punktacja rozgrywki** |**Opcjonalne** |

REQ_FUNC_6.1. Każda z rozgrywek jest punktowana

REQ_FUNC_6.2. Podliczenie następuje w momencie zakończenia gry

REQ_FUNC_6.3. Za każdy kolor ułożony w dobrej pozycji użytkownik dostaje 2pkt

REQ_FUNC_6.4. Za poprawny kolor ułożony w niepoprawnej pozycji użytkownik dostaje 1pkt

REQ_FUNC_6.5. Za rozszyfrowanie całego kodu użytkownik dostaje dodatkowo ilość punktów równą różnicy pomiędzy maksymalną ilością prób (14) a ilością prób w której udało się kod rozszyfrować

REQ_FUNC_6.6. Wynik rozgrywki zapisywany jest w rankingu wszystkich rozgrywek

|                                                                                                                                       |Przykłady punktacja                          |
|---------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| **Wygrana**                                                                                                                           | |
|| za każdą planetę w dobrym miejscu ilość ułożonych planet planet*2pkt (dobry kolor i miejsce)                                          
|| ilość prób maksymalna(zawsze 14) - w ilu próbach udało się odgadnąć                                                                   |
|| `Przykład: udało się rozszyfrować kod za trzecim razem:`                                                                              |
|| `5planet * 2pkt = 10 pkt`                                                                                                             |
|| `14 - 3podejscia = 11pkt`                                                                                                             |
|| `Razem 10 + 11 = 21pkt za rozgrywkę`                                                                                                 |
| **Przegrana**                                                                                                                         |  |
|| za każdą planetę w dobrym miejscu ilość ułożonych planet planet * 2pkt (dobry kolor i miejsce)                                        |
|| za poprawny kolor planety w nieprawidłowej pozycji ilość planet *1pkt (dobry kolor, zła pozycja)                                      |
|| `Przykład: dwie planety w dobrym kolorze i pozycji, jedna w dobrym kolorze i nieprawidłowej pozycji, 2 nieprawidłowy kolor i pozycja` |
|| `2 planety * 2pkt(dobry kolor i pozycja) = 4pkt`                                                                                      |
|| `1 planeta * 1pkt (dobry kolor zła pozycja) = 1pkt`                                                                                   |
|| `2 planety * 0pkt (zły kolor zła pozycja) = 0pkt`                                                                                     |
|| `Razem 4 + 1 + 0 = 5pkt za rozgrywkę`                                                                                                 |



|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**7**| **Rejestracja** |**Opcjonalne** |

REQ_FUNC_7.1. Do rejestracji wymagane jest podanie adresu mailowego, nazwy użytkownika, hasła

REQ_FUNC_7.2. Pola adres mailowy, nazwa użytkownika jak i hasło są walidowane

REQ_FUNC_7.3. Hasło wymóg: znaki alfanumeryczne oraz znaki specjalne

REQ_FUNC_7.4. Minimalna długość hasła: 8 znaków

REQ_FUNC_7.5. Minimum jedna mała I jedna wielka litera w haśle

REQ_FUNC_7.6. Minimum jedna cyfra w haśle

REQ_FUNC_7.7. Minimum jeden znak specjalny w haśle

REQ_FUNC_7.8. W momencie rejestracji użytkownik dostaje informacje zwrotną czy jego hasło spełnia wymogi

REQ_FUNC_7.9. W informacji zwrotnej nt hasła jest informacja, który z wymogów nie jest jeszcze spełniony

REQ_FUNC_7.10. Nazwa użytkownika może składać się ze znaków alfanumerycznych. Znaki specjalne są zabronione

REQ_FUNC_7.11. Adres mailowy musi zawierać znak "@"

REQ_FUNC_7.12. Po weryfikacji jako poprawnych pól adresu mailowego, nazwy użytkownika, hasła na adres mailowy użytkownika zostaje wysłany mail z potwierdzeniem rejestracji

REQ_FUNC_7.13. Potwierdzenie rejestracji przez użytkownika jest niezbędne do skorzystania z opcji logowania

|   lp            |nazwa grupy:                          |priorytet                         |
|----------------|-------------------------------|-----------------------------|
|**8**| **Logowanie** |**Opcjonalne** |

REQ_FUNC_8.1. Aplikacja umożliwia logowanie się użytkownika

REQ_FUNC_8.2. Logowanie za pomocą nazwy użytkownika oraz ustalonego przez niego hasła

REQ_FUNC_8.3. Poprawne zalogowanie jest warunkiem skorzystania z możliwości wczytania gry i zapisu