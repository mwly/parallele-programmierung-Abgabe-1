## Programm Tankstelle

3 Klassen(App/Waschanlage/ZeitSimulator):

### App
* Einstiegspunkt
    * Erstellt Waschanlagen Objekt
    * Erstellt Zeitsimulator Objekt
    * Startet den Zeitsimulator

### Tankstelle
* 3 Nested Klassen (WaschStraße/SaugBoxen/Warteschlangen)
    * Initialisiert 6 Objekte( 3 Waschstraßen/ 2 Saugboxen / 1 Warteschlangen)
    * Waschstraße
        * Startet sich selbst als Thread im Konstruktor
        * Threadloop:
        1. Schaut ob Autos in der Warteschlange für das Waschen warten
            * Wenn ja: wasche Auto und loope danach zurück auf 1.
            * Wenn nein: next step
        2. Schaut ob Autos in der Warteschlange für Waschen und Saugen warten
            * wenn ja: wasche Auto, reihe Auto in Warteschlange saugen ein und loope danach zurück auf 1.
            * wenn nein: next step
        3. Thread.wait auf dem warteschlangen Objekt
            * on notify: loope zurück auf 1.
    * SaugBoxen
        * Startet sich selbst als Thread im Konstruktor
        * Threadloop:
        1. Schaut ob Autos in der Warteschlange für das Saugen und Waschen warten
            * wenn ja: sauge Auto, reihe Auto in Warteschlange Waschen ein und loope danach zurück auf 1.
            * Wenn nein: next step
        2. Schaut ob Autos in der Warteschlange für Saugen warten
            * Wenn ja: sauge Auto und loope danach zurück auf 1.
            * wenn nein: next step
        3. Thread.wait auf dem warteschlangen Objekt
            * on notify: loope zurück auf 1.
    * Warteschlangen
        * Repräsentiert 3 Threadsafe Counter
        1. WarteSchlangeWaschen
        2. WarteSchlangeBeides
        3. WarteSchlangeSaugen

### ZeitSimulator 
* Runnable
    * Führt nacheinander 4 Funktionen aus
    * nachmittag()
        * Fügt alle 5 minuten die Vorgegebne Menge Autos in die Warteschlangen
    * ruschhour()
        * Fügt alle 5 minuten die Vorgegebne Menge Autos in die Warteschlangen
    * abends()
        * Fügt alle 5 minuten die Vorgegebne Menge Autos in die Warteschlangen
    * feierabend()
        * Schließt die Tankstelle und schickt alle ungewaschenen Autos nach Hause





## Beispiel Ausgabe:


* Tolle Tankstelle!
* Preise:
* Diesel 2 €
* Benzin 2€
* Nachmittags-Min: 1
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 1
* Nachmittags-Min: 2
* Nachmittags-Min: 3
* Nachmittags-Min: 4
* Nachmittags-Min: 5
* Nachmittags-Min: 6
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Nachmittags-Min: 7
* Nachmittags-Min: 8
* Nachmittags-Min: 9
* Nachmittags-Min: 10
* Nachmittags-Min: 11
* 1 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Splish von Waschstraße 2
* Sauggeräusche beginnen in Box 1
* Nach 11 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 12
* Nachmittags-Min: 13
* Nachmittags-Min: 14
* Nachmittags-Min: 15
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 3
* Nachmittags-Min: 16
* 1 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Splish von Waschstraße 1
* Nachmittags-Min: 17
* Nach 7 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 18
* Nachmittags-Min: 19
* Nachmittags-Min: 20
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 2
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Nach 5 Minuten trocknet Waschstraße 1
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 1
* Nachmittags-Min: 21
* 1 neu in Warteschlange Waschen
* Nachmittags-Min: 22
* Nachmittags-Min: 23
* Nachmittags-Min: 24
* Nachmittags-Min: 25
* Nachmittags-Min: 26
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Nach 11 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Nachmittags-Min: 27
* Nachmittags-Min: 28
* Nachmittags-Min: 29
* Nach 9 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 30
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 2
* Nachmittags-Min: 31
* 1 neu in Warteschlange Waschen
* Nachmittags-Min: 32
* Nach 12 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Nachmittags-Min: 33
* Nachmittags-Min: 34
* Nach 8 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 35
* Nachmittags-Min: 36
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Nachmittags-Min: 37
* Nachmittags-Min: 38
* Nachmittags-Min: 39
* Nachmittags-Min: 40
* Nachmittags-Min: 41
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 3
* Nach 9 Minuten trocknet Waschstraße 1
* Nach 11 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 42
* Nachmittags-Min: 43
* Nachmittags-Min: 44
* Nachmittags-Min: 45
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 2
* Nachmittags-Min: 46
* 1 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Splish von Waschstraße 1
* Nachmittags-Min: 47
* Nachmittags-Min: 48
* Nachmittags-Min: 49
* Nachmittags-Min: 50
* Nachmittags-Min: 51
* 1 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 1
* Nach 6 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 52
* Nach 12 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 53
* Nachmittags-Min: 54
* Nachmittags-Min: 55
* Nachmittags-Min: 56
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 2
* Nachmittags-Min: 57
* Nach 12 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 58
* Nachmittags-Min: 59
* Nachmittags-Min: 60
* Nach 9 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Splish von Waschstraße 3
* RuschHour-Min: 1
* 3 neu in Warteschlange Waschen
* 2 neu in Warteschlange Beides
* Sauggeräusche beginnen in Box 2
* Splish von Waschstraße 1
* RuschHour-Min: 2
* RuschHour-Min: 3
* RuschHour-Min: 4
* RuschHour-Min: 5
* Nach 9 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 10 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* RuschHour-Min: 6
* 2 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Nach 6 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 7
* RuschHour-Min: 8
* RuschHour-Min: 9
* RuschHour-Min: 10
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 11
* 2 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Nach 11 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 12
* Nach 6 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 13
* Nach 8 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* RuschHour-Min: 14
* RuschHour-Min: 15
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 16
* 3 neu in Warteschlange Waschen
* 2 neu in Warteschlange Beides
* RuschHour-Min: 17
* Nach 6 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 18
* Nach 6 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 19
* RuschHour-Min: 20
* Nach 9 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 7 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 21
* 3 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* RuschHour-Min: 22
* RuschHour-Min: 23
* Nach 5 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 24
* Nach 7 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 25
* RuschHour-Min: 26
* 2 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* RuschHour-Min: 27
* RuschHour-Min: 28
* RuschHour-Min: 29
* Nach 5 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 30
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 31
* 3 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* Nach 11 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* RuschHour-Min: 32
* RuschHour-Min: 33
* RuschHour-Min: 34
* Nach 11 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 35
* RuschHour-Min: 36
* 3 neu in Warteschlange Waschen
* 2 neu in Warteschlange Beides
* RuschHour-Min: 37
* RuschHour-Min: 38
* RuschHour-Min: 39
* Nach 10 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Nach 5 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 40
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 9 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 41
* 2 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* RuschHour-Min: 42
* RuschHour-Min: 43
* RuschHour-Min: 44
* RuschHour-Min: 45
* Nach 6 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* RuschHour-Min: 46
* 3 neu in Warteschlange Waschen
* 2 neu in Warteschlange Beides
* RuschHour-Min: 47
* Nach 8 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 48
* Nach 8 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* RuschHour-Min: 49
* RuschHour-Min: 50
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* RuschHour-Min: 51
* 3 neu in Warteschlange Waschen
* 1 neu in Warteschlange Beides
* RuschHour-Min: 52
* RuschHour-Min: 53
* Nach 6 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 54
* RuschHour-Min: 55
* RuschHour-Min: 56
* 3 neu in Warteschlange Waschen
* 2 neu in Warteschlange Beides
* RuschHour-Min: 57
* Nach 12 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* RuschHour-Min: 58
* Nach 5 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* RuschHour-Min: 59
* RuschHour-Min: 60
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 12 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Nach 9 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 1
* 1 neu in Warteschlange Beides
* Abends-Min: 2
* Nach 5 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 3
* Abends-Min: 4
* Abends-Min: 5
* Nach 7 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Nach 3 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Abends-Min: 6
* 1 neu in Warteschlange Beides
* Abends-Min: 7
* Abends-Min: 8
* Nach 8 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Abends-Min: 9
* Abends-Min: 10
* Nach 5 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 11
* 1 neu in Warteschlange Beides
* Abends-Min: 12
* Abends-Min: 13
* Abends-Min: 14
* Nach 12 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 15
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 16
* 1 neu in Warteschlange Beides
* Abends-Min: 17
* Abends-Min: 18
* Nach 10 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Abends-Min: 19
* Nach 9 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Abends-Min: 20
* Abends-Min: 21
* 1 neu in Warteschlange Beides
* Abends-Min: 22
* Abends-Min: 23
* Abends-Min: 24
* Nach 5 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Abends-Min: 25
* Nach 11 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 26
* 1 neu in Warteschlange Beides
* Nach 8 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Abends-Min: 27
* Abends-Min: 28
* Abends-Min: 29
* Abends-Min: 30
* Nach 9 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 9 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 31
* 1 neu in Warteschlange Beides
* Abends-Min: 32
* Nach 6 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Nach 7 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 33
* Abends-Min: 34
* Nach 10 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Abends-Min: 35
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 36
* 1 neu in Warteschlange Beides
* Abends-Min: 37
* Abends-Min: 38
* Nach 6 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Abends-Min: 39
* Abends-Min: 40
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 41
* 1 neu in Warteschlange Beides
* Abends-Min: 42
* Abends-Min: 43
* Abends-Min: 44
* Nach 12 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 45
* Nach 9 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 46
* 1 neu in Warteschlange Beides
* Nach 12 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Abends-Min: 47
* Abends-Min: 48
* Abends-Min: 49
* Nach 11 Minuten trocknet Waschstraße 2
* Splish von Waschstraße 2
* Abends-Min: 50
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 51
* 1 neu in Warteschlange Beides
* Abends-Min: 52
* Abends-Min: 53
* Abends-Min: 54
* Abends-Min: 55
* Nach 6 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Abends-Min: 56
* 1 neu in Warteschlange Beides
* Nach 10 Minuten trocknet Waschstraße 3
* Splish von Waschstraße 3
* Nach 12 Minuten trocknet Waschstraße 1
* Splish von Waschstraße 1
* Abends-Min: 57
* Abends-Min: 58
* Abends-Min: 59
* Abends-Min: 60
* Nach 3 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 1
* Nach 3 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* 1 neu in Warteschlange Waschen
* Sauggeräusche beginnen in Box 2
* Schließe Tankstelle. Alle Autos die gerade noch geputzt werden, dürfen dies Beenden. Der Rest muss leider morgen wiederkommen.
* Autos die auf Wäsche und Saugen gewartet haben: 0
* Autos die auf Wäsche gewartet haben: 17
* Autos die auf Saugen gewartet haben: 0
* Nach 12 Minuten trocknet Waschstraße 2
* Nach 3 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.
* Nach 11 Minuten trocknet Waschstraße 1
* Nach 12 Minuten trocknet Waschstraße 3
* Nach 6 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.
* 
* 