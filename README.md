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
* Füge NachmittagAuto#1 in Warteschlange Waschen
* Füge NachmittagAuto#2 in Warteschlange Beides
* Splish! Wasche NachmittagAuto#1 in Waschstraße 1
* Splish! Wasche NachmittagAuto#2 in Waschstraße 2
* Nachmittags-Min: 2
* Nachmittags-Min: 3
* Nachmittags-Min: 4
* Nachmittags-Min: 5
* Nachmittags-Min: 6
* Füge NachmittagAuto#3 in Warteschlange Waschen
* Füge NachmittagAuto#4 in Warteschlange Beides
* Splish! Wasche NachmittagAuto#3 in Waschstraße 3
* CHooow! Sauge NachmittagAuto#4 in Box 1
* Nachmittags-Min: 7
* Nach 6 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 8
* Nachmittags-Min: 9
* Nachmittags-Min: 10
* Nachmittags-Min: 11
* Füge NachmittagAuto#5 in Warteschlange Waschen
* Nachmittags-Min: 12
* Nach 7 Minuten trocknet Waschstraße 3
* Splish! Wasche NachmittagAuto#5 in Waschstraße 3
* Nach 12 Minuten trocknet Waschstraße 2
* Jedoch möchte das Auto noch gesaugt werden.
* Füge NachmittagAuto#2 in Warteschlange Saugen
* Nachmittags-Min: 13
* Nachmittags-Min: 14
* Nachmittags-Min: 15
* Nachmittags-Min: 16
* Füge NachmittagAuto#6 in Warteschlange Waschen
* Füge NachmittagAuto#7 in Warteschlange Beides
* CHooow! Sauge NachmittagAuto#7 in Box 2
* Splish! Wasche NachmittagAuto#6 in Waschstraße 2
* Nachmittags-Min: 17
* Nachmittags-Min: 18
* Nachmittags-Min: 19
* Nachmittags-Min: 20
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#4 in Warteschlange Waschen
* CHooow! Sauge NachmittagAuto#2 in Box 1
* Splish! Wasche NachmittagAuto#4 in Waschstraße 1
* Nachmittags-Min: 21
* Füge NachmittagAuto#8 in Warteschlange Waschen
* Füge NachmittagAuto#9 in Warteschlange Beides
* Nachmittags-Min: 22
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche NachmittagAuto#8 in Waschstraße 3
* Nach 7 Minuten trocknet Waschstraße 2
* Splish! Wasche NachmittagAuto#9 in Waschstraße 2
* Nachmittags-Min: 23
* Nachmittags-Min: 24
* Nachmittags-Min: 25
* Nach 5 Minuten trocknet Waschstraße 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#7 in Warteschlange Waschen
* Splish! Wasche NachmittagAuto#7 in Waschstraße 1
* Nachmittags-Min: 26
* Füge NachmittagAuto#10 in Warteschlange Waschen
* Füge NachmittagAuto#11 in Warteschlange Beides
* CHooow! Sauge NachmittagAuto#11 in Box 2
* Nachmittags-Min: 27
* Nachmittags-Min: 28
* Nach 6 Minuten trocknet Waschstraße 2
* Jedoch möchte das Auto noch gesaugt werden.
* Füge NachmittagAuto#9 in Warteschlange Saugen
* Splish! Wasche NachmittagAuto#10 in Waschstraße 2
* Nachmittags-Min: 29
* Nachmittags-Min: 30
* Nachmittags-Min: 31
* Füge NachmittagAuto#12 in Warteschlange Waschen
* Füge NachmittagAuto#13 in Warteschlange Beides
* Nachmittags-Min: 32
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche NachmittagAuto#12 in Waschstraße 3
* Nachmittags-Min: 33
* Nachmittags-Min: 34
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche NachmittagAuto#13 in Waschstraße 2
* Nachmittags-Min: 35
* Nach 15 Minuten herrscht Stille in Box 1
* CHooow! Sauge NachmittagAuto#9 in Box 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#11 in Warteschlange Waschen
* Nachmittags-Min: 36
* Füge NachmittagAuto#14 in Warteschlange Beides
* CHooow! Sauge NachmittagAuto#14 in Box 2
* Nach 11 Minuten trocknet Waschstraße 1
* Splish! Wasche NachmittagAuto#11 in Waschstraße 1
* Nachmittags-Min: 37
* Nachmittags-Min: 38
* Nachmittags-Min: 39
* Nach 7 Minuten trocknet Waschstraße 3
* Nach 5 Minuten trocknet Waschstraße 2
* Jedoch möchte das Auto noch gesaugt werden.
* Füge NachmittagAuto#13 in Warteschlange Saugen
* Nachmittags-Min: 40
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#14 in Warteschlange Waschen
* CHooow! Sauge NachmittagAuto#13 in Box 2
* Splish! Wasche NachmittagAuto#14 in Waschstraße 2
* Nachmittags-Min: 41
* Füge NachmittagAuto#15 in Warteschlange Waschen
* Füge NachmittagAuto#16 in Warteschlange Beides
* Splish! Wasche NachmittagAuto#15 in Waschstraße 3
* Nachmittags-Min: 42
* Nachmittags-Min: 43
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche NachmittagAuto#16 in Waschstraße 1
* Nachmittags-Min: 44
* Nachmittags-Min: 45
* Nach 10 Minuten herrscht Stille in Box 1
* Nachmittags-Min: 46
* Füge NachmittagAuto#17 in Warteschlange Waschen
* Füge NachmittagAuto#18 in Warteschlange Beides
* CHooow! Sauge NachmittagAuto#18 in Box 1
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche NachmittagAuto#17 in Waschstraße 2
* Nachmittags-Min: 47
* Nachmittags-Min: 48
* Nach 8 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 49
* Nachmittags-Min: 50
* Nachmittags-Min: 51
* Füge NachmittagAuto#19 in Warteschlange Waschen
* Splish! Wasche NachmittagAuto#19 in Waschstraße 3
* Nachmittags-Min: 52
* Nach 9 Minuten trocknet Waschstraße 1
* Jedoch möchte das Auto noch gesaugt werden.
* Füge NachmittagAuto#16 in Warteschlange Saugen
* Nachmittags-Min: 53
* Nachmittags-Min: 54
* Nach 8 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 55
* Nach 15 Minuten herrscht Stille in Box 2
* CHooow! Sauge NachmittagAuto#16 in Box 2
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#18 in Warteschlange Waschen
* Splish! Wasche NachmittagAuto#18 in Waschstraße 1
* Nachmittags-Min: 56
* Füge NachmittagAuto#20 in Warteschlange Waschen
* Füge NachmittagAuto#21 in Warteschlange Beides
* CHooow! Sauge NachmittagAuto#21 in Box 1
* Splish! Wasche NachmittagAuto#20 in Waschstraße 2
* Nachmittags-Min: 57
* Nachmittags-Min: 58
* Nachmittags-Min: 59
* Nach 9 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 60
* Nach 5 Minuten herrscht Stille in Box 2
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge NachmittagAuto#21 in Warteschlange Waschen
* Splish! Wasche NachmittagAuto#21 in Waschstraße 3
* RuschHour-Min: 1
* Füge RushHourAuto#1 in Warteschlange Waschen
* Füge RushHourAuto#2 in Warteschlange Waschen
* Füge RushHourAuto#3 in Warteschlange Waschen
* Füge RushHourAuto#4 in Warteschlange Beides
* Füge RushHourAuto#5 in Warteschlange Beides
* CHooow! Sauge RushHourAuto#4 in Box 2
* CHooow! Sauge RushHourAuto#5 in Box 1
* RuschHour-Min: 2
* RuschHour-Min: 3
* RuschHour-Min: 4
* Nach 9 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#1 in Waschstraße 1
* RuschHour-Min: 5
* Nach 10 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#2 in Waschstraße 2
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#4 in Warteschlange Waschen
* RuschHour-Min: 6
* Füge RushHourAuto#6 in Warteschlange Waschen
* Füge RushHourAuto#7 in Warteschlange Waschen
* Füge RushHourAuto#8 in Warteschlange Waschen
* Füge RushHourAuto#9 in Warteschlange Beides
* CHooow! Sauge RushHourAuto#9 in Box 2
* RuschHour-Min: 7
* RuschHour-Min: 8
* RuschHour-Min: 9
* Nach 9 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#3 in Waschstraße 3
* RuschHour-Min: 10
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#9 in Warteschlange Waschen
* RuschHour-Min: 11
* Füge RushHourAuto#10 in Warteschlange Waschen
* Füge RushHourAuto#11 in Warteschlange Waschen
* Füge RushHourAuto#12 in Warteschlange Waschen
* Füge RushHourAuto#13 in Warteschlange Beides
* Füge RushHourAuto#14 in Warteschlange Beides
* CHooow! Sauge RushHourAuto#13 in Box 2
* RuschHour-Min: 12
* Nach 8 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#4 in Waschstraße 1
* RuschHour-Min: 13
* RuschHour-Min: 14
* Nach 9 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#6 in Waschstraße 2
* RuschHour-Min: 15
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#5 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#14 in Box 1
* RuschHour-Min: 16
* Füge RushHourAuto#15 in Warteschlange Waschen
* Füge RushHourAuto#16 in Warteschlange Waschen
* Füge RushHourAuto#17 in Warteschlange Waschen
* Füge RushHourAuto#18 in Warteschlange Beides
* RuschHour-Min: 17
* RuschHour-Min: 18
* RuschHour-Min: 19
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#7 in Waschstraße 3
* RuschHour-Min: 20
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#14 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#18 in Box 1
* RuschHour-Min: 21
* Füge RushHourAuto#19 in Warteschlange Waschen
* Füge RushHourAuto#20 in Warteschlange Waschen
* Füge RushHourAuto#21 in Warteschlange Beides
* RuschHour-Min: 22
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#8 in Waschstraße 2
* RuschHour-Min: 23
* RuschHour-Min: 24
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#9 in Waschstraße 1
* RuschHour-Min: 25
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#13 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#21 in Box 2
* RuschHour-Min: 26
* Füge RushHourAuto#22 in Warteschlange Waschen
* Füge RushHourAuto#23 in Warteschlange Waschen
* Füge RushHourAuto#24 in Warteschlange Waschen
* Füge RushHourAuto#25 in Warteschlange Beides
* Füge RushHourAuto#26 in Warteschlange Beides
* RuschHour-Min: 27
* RuschHour-Min: 28
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#10 in Waschstraße 2
* RuschHour-Min: 29
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#11 in Waschstraße 3
* RuschHour-Min: 30
* Nach 6 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#12 in Waschstraße 1
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#18 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#25 in Box 1
* RuschHour-Min: 31
* Füge RushHourAuto#27 in Warteschlange Waschen
* Füge RushHourAuto#28 in Warteschlange Waschen
* Füge RushHourAuto#29 in Warteschlange Beides
* RuschHour-Min: 32
* RuschHour-Min: 33
* RuschHour-Min: 34
* RuschHour-Min: 35
* Nach 6 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#5 in Waschstraße 3
* RuschHour-Min: 36
* Füge RushHourAuto#30 in Warteschlange Waschen
* Füge RushHourAuto#31 in Warteschlange Waschen
* Füge RushHourAuto#32 in Warteschlange Waschen
* Füge RushHourAuto#33 in Warteschlange Beides
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#15 in Waschstraße 2
* RuschHour-Min: 37
* RuschHour-Min: 38
* RuschHour-Min: 39
* RuschHour-Min: 40
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#21 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#26 in Box 2
* RuschHour-Min: 41
* Füge RushHourAuto#34 in Warteschlange Waschen
* Füge RushHourAuto#35 in Warteschlange Waschen
* Füge RushHourAuto#36 in Warteschlange Waschen
* Füge RushHourAuto#37 in Warteschlange Beides
* Füge RushHourAuto#38 in Warteschlange Beides
* RuschHour-Min: 42
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#16 in Waschstraße 1
* RuschHour-Min: 43
* RuschHour-Min: 44
* Nach 9 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#17 in Waschstraße 3
* RuschHour-Min: 45
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#25 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#29 in Box 1
* RuschHour-Min: 46
* Füge RushHourAuto#39 in Warteschlange Waschen
* Füge RushHourAuto#40 in Warteschlange Waschen
* Füge RushHourAuto#41 in Warteschlange Waschen
* Füge RushHourAuto#42 in Warteschlange Beides
* Füge RushHourAuto#43 in Warteschlange Beides
* Nach 10 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#14 in Waschstraße 2
* RuschHour-Min: 47
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#19 in Waschstraße 1
* RuschHour-Min: 48
* RuschHour-Min: 49
* Nach 5 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#20 in Waschstraße 3
* RuschHour-Min: 50
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#26 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#33 in Box 2
* RuschHour-Min: 51
* Füge RushHourAuto#44 in Warteschlange Waschen
* Füge RushHourAuto#45 in Warteschlange Waschen
* Füge RushHourAuto#46 in Warteschlange Waschen
* Füge RushHourAuto#47 in Warteschlange Waschen
* Füge RushHourAuto#44 in Warteschlange Beides
* RuschHour-Min: 52
* RuschHour-Min: 53
* RuschHour-Min: 54
* RuschHour-Min: 55
* Nach 8 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#13 in Waschstraße 1
* Nach 6 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#22 in Waschstraße 3
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#29 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#37 in Box 1
* RuschHour-Min: 56
* Füge RushHourAuto#49 in Warteschlange Waschen
* Füge RushHourAuto#50 in Warteschlange Waschen
* Füge RushHourAuto#51 in Warteschlange Beides
* RuschHour-Min: 57
* RuschHour-Min: 58
* Nach 12 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#23 in Waschstraße 2
* RuschHour-Min: 59
* RuschHour-Min: 60
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#37 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#38 in Box 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#33 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#42 in Box 2
* Abends-Min: 1
* Füge AbendsAuto#1 in Warteschlange Beides
* Abends-Min: 2
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#24 in Waschstraße 1
* Abends-Min: 3
* Abends-Min: 4
* Abends-Min: 5
* Nach 7 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#18 in Waschstraße 2
* Abends-Min: 6
* Füge AbendsAuto#2 in Warteschlange Beides
* Abends-Min: 7
* Nach 12 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#27 in Waschstraße 3
* Abends-Min: 8
* Nach 6 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#28 in Waschstraße 1
* Abends-Min: 9
* Abends-Min: 10
* Abends-Min: 11
* Füge AbendsAuto#3 in Warteschlange Beides
* Abends-Min: 12
* Abends-Min: 13
* Abends-Min: 14
* Nach 6 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#30 in Waschstraße 1
* Abends-Min: 15
* Nach 10 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#31 in Waschstraße 2
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#38 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#43 in Box 1
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#42 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#44 in Box 2
* Abends-Min: 16
* Füge AbendsAuto#4 in Warteschlange Beides
* Abends-Min: 17
* Abends-Min: 18
* Nach 11 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#32 in Waschstraße 3
* Abends-Min: 19
* Abends-Min: 20
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#44 in Warteschlange Waschen
* CHooow! Sauge RushHourAuto#51 in Box 2
* Abends-Min: 21
* Füge AbendsAuto#5 in Warteschlange Beides
* Abends-Min: 22
* Nach 8 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#21 in Waschstraße 1
* Abends-Min: 23
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#34 in Waschstraße 2
* Abends-Min: 24
* Abends-Min: 25
* Nach 7 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#35 in Waschstraße 3
* Abends-Min: 26
* Füge AbendsAuto#6 in Warteschlange Beides
* Abends-Min: 27
* Abends-Min: 28
* Abends-Min: 29
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#36 in Waschstraße 1
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#25 in Waschstraße 2
* Abends-Min: 30
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#43 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#1 in Box 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge RushHourAuto#51 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#2 in Box 2
* Abends-Min: 31
* Füge AbendsAuto#7 in Warteschlange Beides
* Abends-Min: 32
* Abends-Min: 33
* Abends-Min: 34
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#39 in Waschstraße 1
* Nach 9 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#40 in Waschstraße 3
* Abends-Min: 35
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#1 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#3 in Box 1
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#2 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#4 in Box 2
* Abends-Min: 36
* Füge AbendsAuto#8 in Warteschlange Beides
* Abends-Min: 37
* Abends-Min: 38
* Abends-Min: 39
* Nach 5 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#41 in Waschstraße 3
* Abends-Min: 40
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#3 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#5 in Box 1
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#4 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#6 in Box 2
* Abends-Min: 41
* Füge AbendsAuto#9 in Warteschlange Beides
* Nach 12 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#26 in Waschstraße 2
* Abends-Min: 42
* Abends-Min: 43
* Abends-Min: 44
* Abends-Min: 45
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#6 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#7 in Box 2
* Abends-Min: 46
* Füge AbendsAuto#10 in Warteschlange Beides
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#44 in Waschstraße 1
* Abends-Min: 47
* Abends-Min: 48
* Abends-Min: 49
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#45 in Waschstraße 2
* Abends-Min: 50
* Nach 11 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#46 in Waschstraße 3
* Abends-Min: 51
* Füge AbendsAuto#11 in Warteschlange Beides
* Abends-Min: 52
* Abends-Min: 53
* Abends-Min: 54
* Nach 5 Minuten trocknet Waschstraße 2
* Splish! Wasche RushHourAuto#47 in Waschstraße 2
* Abends-Min: 55
* Nach 9 Minuten trocknet Waschstraße 1
* Splish! Wasche RushHourAuto#29 in Waschstraße 1
* Nach 5 Minuten trocknet Waschstraße 3
* Splish! Wasche RushHourAuto#49 in Waschstraße 3
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#5 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#8 in Box 1
* Abends-Min: 56
* Füge AbendsAuto#12 in Warteschlange Beides
* Abends-Min: 57
* Abends-Min: 58
* Abends-Min: 59
* Abends-Min: 60
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Füge AbendsAuto#7 in Warteschlange Waschen
* CHooow! Sauge AbendsAuto#9 in Box 2
* Schließe Tankstelle. Alle Autos die gerade noch geputzt werden, dürfen dies Beenden. Der Rest muss leider morgen wiederkommen.
* Autos die auf Wäsche und Saugen gewartet haben: 3
* Autos die auf Wäsche gewartet haben: 15
* Autos die auf Saugen gewartet haben: 0
* Nach 7 Minuten trocknet Waschstraße 2
* Nach 8 Minuten trocknet Waschstraße 1
* Nach 10 Minuten trocknet Waschstraße 3
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte das Auto noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte das Auto noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.

