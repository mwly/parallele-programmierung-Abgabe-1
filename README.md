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
* Starte Zeitsimulator
* Autos, die Nachmittags hinzugefügt werden, bekommen eine ID zwischen 000 und 100 zugeordnet
* Autos, die während der RuschHour hinzugefügt werden, bekommen eine ID zwischen 100 und 200 zugeordnet
* Autos, die Abends hinzugefügt werden, bekommen eine ID zwischen 200 und 300 zugeordnet
* Autos, die eine Premiummitgliedschaft abgeschlossen haben, führen eine 1, Autos ohne Mitgliedschaft eine 2.
* Nachmittags-Min: 1
* Füge Auto#2001 in Warteschlange Waschen
* Splish! Wasche Auto#2001 in Waschstraße 1
* Nachmittags-Min: 2
* Nachmittags-Min: 3
* Nachmittags-Min: 4
* Nachmittags-Min: 5
* Nachmittags-Min: 6
* Füge Auto#2002 in Warteschlange Beides
* Splish! Wasche Auto#2002 in Waschstraße 2
* Nachmittags-Min: 7
* Nachmittags-Min: 8
* Nachmittags-Min: 9
* Nachmittags-Min: 10
* Nachmittags-Min: 11
* Füge Auto#2003 in Warteschlange Waschen
* Füge Auto#2004 in Warteschlange Beides
* Splish! Wasche Auto#2003 in Waschstraße 3
* CHooow! Sauge Auto#2004 in Box 1
* Nach 11 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 12
* Nachmittags-Min: 13
* Nachmittags-Min: 14
* Nachmittags-Min: 15
* Nachmittags-Min: 16
* Füge Auto#2005 in Warteschlange Waschen
* Nach 11 Minuten trocknet Waschstraße 2
* Jedoch möchte Auto#2002noch gesaugt werden.
* Füge Auto#2002 in Warteschlange Saugen
* Splish! Wasche Auto#2005 in Waschstraße 2
* Nach 6 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 17
* Nachmittags-Min: 18
* Nachmittags-Min: 19
* Nachmittags-Min: 20
* Nachmittags-Min: 21
* Füge Auto#2006 in Warteschlange Waschen
* Füge Auto#2007 in Warteschlange Beides
* CHooow! Sauge Auto#2007 in Box 2
* Splish! Wasche Auto#2006 in Waschstraße 1
* Nach 5 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 22
* Nachmittags-Min: 23
* Nachmittags-Min: 24
* Nachmittags-Min: 25
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2004noch gewaschen werden.
* Füge Auto#2004 in Warteschlange Waschen
* CHooow! Sauge Auto#2002 in Box 1
* Splish! Wasche Auto#2004 in Waschstraße 3
* Nachmittags-Min: 26
* Füge Auto#1008 in Warteschlange Beides
* Splish! Wasche Auto#1008 in Waschstraße 2
* Nachmittags-Min: 27
* Nach 7 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 28
* Nachmittags-Min: 29
* Nachmittags-Min: 30
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2007noch gewaschen werden.
* Füge Auto#2007 in Warteschlange Waschen
* Splish! Wasche Auto#2007 in Waschstraße 1
* Nachmittags-Min: 31
* Füge Auto#2009 in Warteschlange Waschen
* Nachmittags-Min: 32
* Nach 7 Minuten trocknet Waschstraße 2
* Jedoch möchte Auto#1008noch gesaugt werden.
* Füge Auto#1008 in Warteschlange Saugen
* Splish! Wasche Auto#2009 in Waschstraße 2
* CHooow! Sauge Auto#1008 in Box 2
* Nachmittags-Min: 33
* Nach 8 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 34
* Nachmittags-Min: 35
* Nach 10 Minuten herrscht Stille in Box 1
* Nachmittags-Min: 36
* Füge Auto#2010 in Warteschlange Waschen
* Füge Auto#2011 in Warteschlange Beides
* Splish! Wasche Auto#2010 in Waschstraße 3
* CHooow! Sauge Auto#2011 in Box 1
* Nachmittags-Min: 37
* Nach 5 Minuten herrscht Stille in Box 2
* Nachmittags-Min: 38
* Nachmittags-Min: 39
* Nachmittags-Min: 40
* Nachmittags-Min: 41
* Füge Auto#1012 in Warteschlange Beides
* CHooow! Sauge Auto#1012 in Box 2
* Nach 11 Minuten trocknet Waschstraße 1
* Nachmittags-Min: 42
* Nach 10 Minuten trocknet Waschstraße 2
* Nachmittags-Min: 43
* Nachmittags-Min: 44
* Nachmittags-Min: 45
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1012noch gewaschen werden.
* Füge Auto#1012 in Warteschlange Waschen
* Splish! Wasche Auto#1012 in Waschstraße 1
* Nachmittags-Min: 46
* Füge Auto#2013 in Warteschlange Waschen
* Splish! Wasche Auto#2013 in Waschstraße 2
* Nach 11 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 47
* Nachmittags-Min: 48
* Nachmittags-Min: 49
* Nachmittags-Min: 50
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2011noch gewaschen werden.
* Füge Auto#2011 in Warteschlange Waschen
* Nachmittags-Min: 51
* Füge Auto#1014 in Warteschlange Beides
* Splish! Wasche Auto#2011 in Waschstraße 3
* Nachmittags-Min: 52
* Nachmittags-Min: 53
* Nachmittags-Min: 54
* Nach 9 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1014 in Waschstraße 2
* Nachmittags-Min: 55
* Nachmittags-Min: 56
* Füge Auto#1015 in Warteschlange Waschen
* Füge Auto#2016 in Warteschlange Beides
* CHooow! Sauge Auto#2016 in Box 1
* Nachmittags-Min: 57
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1015 in Waschstraße 1
* Nachmittags-Min: 58
* Nachmittags-Min: 59
* Nach 9 Minuten trocknet Waschstraße 3
* Nachmittags-Min: 60
* RuschHour-Min: 1
* Füge Auto#2101 in Warteschlange Waschen
* Füge Auto#2102 in Warteschlange Waschen
* Füge Auto#1003 in Warteschlange Beides
* CHooow! Sauge Auto#1003 in Box 2
* Splish! Wasche Auto#2101 in Waschstraße 3
* RuschHour-Min: 2
* RuschHour-Min: 3
* Nach 9 Minuten trocknet Waschstraße 2
* Jedoch möchte Auto#1014noch gesaugt werden.
* Füge Auto#1014 in Warteschlange Saugen
* Splish! Wasche Auto#2102 in Waschstraße 2
* RuschHour-Min: 4
* RuschHour-Min: 5
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1003noch gewaschen werden.
* Füge Auto#1003 in Warteschlange Waschen
* CHooow! Sauge Auto#1014 in Box 2
* RuschHour-Min: 6
* Füge Auto#2104 in Warteschlange Waschen
* Füge Auto#1005 in Warteschlange Waschen
* Füge Auto#1006 in Warteschlange Waschen
* Füge Auto#2107 in Warteschlange Waschen
* Füge Auto#2104 in Warteschlange Beides
* RuschHour-Min: 7
* RuschHour-Min: 8
* RuschHour-Min: 9
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1003 in Waschstraße 1
* RuschHour-Min: 10
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2016noch gewaschen werden.
* Füge Auto#2016 in Warteschlange Waschen
* CHooow! Sauge Auto#2104 in Box 1
* RuschHour-Min: 11
* Füge Auto#2109 in Warteschlange Waschen
* Füge Auto#2110 in Warteschlange Waschen
* Füge Auto#2111 in Warteschlange Beides
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1005 in Waschstraße 2
* RuschHour-Min: 12
* Nach 12 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1006 in Waschstraße 3
* RuschHour-Min: 13
* RuschHour-Min: 14
* RuschHour-Min: 15
* RuschHour-Min: 16
* Füge Auto#2112 in Warteschlange Waschen
* Füge Auto#1013 in Warteschlange Waschen
* Füge Auto#1014 in Warteschlange Beides
* RuschHour-Min: 17
* Nach 5 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1013 in Waschstraße 3
* RuschHour-Min: 18
* Nach 9 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2016 in Waschstraße 1
* Nach 7 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2104 in Waschstraße 2
* RuschHour-Min: 19
* RuschHour-Min: 20
* Nach 15 Minuten herrscht Stille in Box 2
* CHooow! Sauge Auto#1014 in Box 2
* RuschHour-Min: 21
* Füge Auto#2115 in Warteschlange Waschen
* Füge Auto#1016 in Warteschlange Waschen
* Füge Auto#1017 in Warteschlange Beides
* Füge Auto#1018 in Warteschlange Beides
* RuschHour-Min: 22
* RuschHour-Min: 23
* RuschHour-Min: 24
* RuschHour-Min: 25
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1016 in Waschstraße 1
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2104noch gewaschen werden.
* Füge Auto#2104 in Warteschlange Waschen
* CHooow! Sauge Auto#1017 in Box 1
* Nach 8 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2104 in Waschstraße 3
* RuschHour-Min: 26
* Füge Auto#2119 in Warteschlange Waschen
* Füge Auto#1020 in Warteschlange Waschen
* Füge Auto#2121 in Warteschlange Waschen
* Füge Auto#2122 in Warteschlange Waschen
* Füge Auto#1019 in Warteschlange Beides
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1020 in Waschstraße 2
* RuschHour-Min: 27
* RuschHour-Min: 28
* RuschHour-Min: 29
* RuschHour-Min: 30
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2107 in Waschstraße 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1014noch gewaschen werden.
* Füge Auto#1014 in Warteschlange Waschen
* CHooow! Sauge Auto#1018 in Box 2
* RuschHour-Min: 31
* Füge Auto#2124 in Warteschlange Waschen
* Füge Auto#1025 in Warteschlange Waschen
* Füge Auto#1026 in Warteschlange Beides
* Füge Auto#2127 in Warteschlange Beides
* RuschHour-Min: 32
* Nach 7 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1014 in Waschstraße 3
* RuschHour-Min: 33
* RuschHour-Min: 34
* RuschHour-Min: 35
* RuschHour-Min: 36
* Füge Auto#1028 in Warteschlange Waschen
* Füge Auto#1029 in Warteschlange Waschen
* Füge Auto#2130 in Warteschlange Beides
* RuschHour-Min: 37
* Nach 11 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1025 in Waschstraße 2
* RuschHour-Min: 38
* RuschHour-Min: 39
* RuschHour-Min: 40
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1017noch gewaschen werden.
* Füge Auto#1017 in Warteschlange Waschen
* CHooow! Sauge Auto#1019 in Box 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1018noch gewaschen werden.
* Füge Auto#1018 in Warteschlange Waschen
* CHooow! Sauge Auto#1026 in Box 2
* RuschHour-Min: 41
* Füge Auto#1031 in Warteschlange Waschen
* Füge Auto#1032 in Warteschlange Waschen
* Füge Auto#2133 in Warteschlange Waschen
* Füge Auto#2134 in Warteschlange Beides
* RuschHour-Min: 42
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1017 in Waschstraße 1
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1018 in Waschstraße 3
* RuschHour-Min: 43
* RuschHour-Min: 44
* RuschHour-Min: 45
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1026noch gewaschen werden.
* Füge Auto#1026 in Warteschlange Waschen
* CHooow! Sauge Auto#2111 in Box 2
* RuschHour-Min: 46
* Füge Auto#2135 in Warteschlange Waschen
* Füge Auto#1036 in Warteschlange Waschen
* Füge Auto#2137 in Warteschlange Waschen
* Füge Auto#1038 in Warteschlange Beides
* RuschHour-Min: 47
* Nach 10 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1026 in Waschstraße 2
* RuschHour-Min: 48
* Nach 6 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1028 in Waschstraße 1
* RuschHour-Min: 49
* Nach 7 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1029 in Waschstraße 3
* RuschHour-Min: 50
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1019noch gewaschen werden.
* Füge Auto#1019 in Warteschlange Waschen
* CHooow! Sauge Auto#1038 in Box 1
* RuschHour-Min: 51
* Füge Auto#2139 in Warteschlange Waschen
* Füge Auto#2140 in Warteschlange Waschen
* Füge Auto#1041 in Warteschlange Beides
* Füge Auto#1042 in Warteschlange Beides
* RuschHour-Min: 52
* RuschHour-Min: 53
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1019 in Waschstraße 1
* RuschHour-Min: 54
* RuschHour-Min: 55
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1031 in Waschstraße 2
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1038noch gewaschen werden.
* Füge Auto#1038 in Warteschlange Waschen
* CHooow! Sauge Auto#1041 in Box 1
* RuschHour-Min: 56
* Füge Auto#1043 in Warteschlange Waschen
* Füge Auto#2144 in Warteschlange Waschen
* Füge Auto#1045 in Warteschlange Waschen
* Füge Auto#2146 in Warteschlange Beides
* RuschHour-Min: 57
* RuschHour-Min: 58
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1032 in Waschstraße 1
* RuschHour-Min: 59
* RuschHour-Min: 60
* Nach 11 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1036 in Waschstraße 3
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2111noch gewaschen werden.
* Füge Auto#2111 in Warteschlange Waschen
* CHooow! Sauge Auto#1042 in Box 2
* Abends-Min: 1
* Füge Auto#1001 in Warteschlange Beides
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1038 in Waschstraße 2
* Abends-Min: 2
* Abends-Min: 3
* Nach 5 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1043 in Waschstraße 1
* Abends-Min: 4
* Abends-Min: 5
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1041noch gewaschen werden.
* Füge Auto#1041 in Warteschlange Waschen
* CHooow! Sauge Auto#1001 in Box 1
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#1042noch gewaschen werden.
* Füge Auto#1042 in Warteschlange Waschen
* CHooow! Sauge Auto#2127 in Box 2
* Abends-Min: 6
* Füge Auto#2202 in Warteschlange Beides
* Nach 5 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1041 in Waschstraße 2
* Abends-Min: 7
* Abends-Min: 8
* Abends-Min: 9
* Nach 9 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#1042 in Waschstraße 3
* Abends-Min: 10
* Abends-Min: 11
* Füge Auto#1003 in Warteschlange Beides
* Abends-Min: 12
* Abends-Min: 13
* Nach 7 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1045 in Waschstraße 2
* Abends-Min: 14
* Abends-Min: 15
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2109 in Waschstraße 1
* Nach 10 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1001noch gewaschen werden.
* Füge Auto#1001 in Warteschlange Waschen
* CHooow! Sauge Auto#1003 in Box 1
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2127noch gewaschen werden.
* Füge Auto#2127 in Warteschlange Waschen
* CHooow! Sauge Auto#2130 in Box 2
* Abends-Min: 16
* Füge Auto#2204 in Warteschlange Beides
* Abends-Min: 17
* Abends-Min: 18
* Abends-Min: 19
* Abends-Min: 20
* Abends-Min: 21
* Füge Auto#2205 in Warteschlange Beides
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#1001 in Waschstraße 2
* Nach 12 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2110 in Waschstraße 3
* Abends-Min: 22
* Abends-Min: 23
* Abends-Min: 24
* Abends-Min: 25
* Nach 10 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2111 in Waschstraße 1
* Abends-Min: 26
* Füge Auto#2206 in Warteschlange Beides
* Abends-Min: 27
* Abends-Min: 28
* Nach 7 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2112 in Waschstraße 2
* Abends-Min: 29
* Abends-Min: 30
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1003noch gewaschen werden.
* Füge Auto#1003 in Warteschlange Waschen
* CHooow! Sauge Auto#2134 in Box 1
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2130noch gewaschen werden.
* Füge Auto#2130 in Warteschlange Waschen
* CHooow! Sauge Auto#2146 in Box 2
* Abends-Min: 31
* Füge Auto#2207 in Warteschlange Beides
* Nach 6 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#1003 in Waschstraße 1
* Nach 10 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2115 in Waschstraße 3
* Abends-Min: 32
* Abends-Min: 33
* Nach 5 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2119 in Waschstraße 2
* Abends-Min: 34
* Abends-Min: 35
* Abends-Min: 36
* Füge Auto#2208 in Warteschlange Beides
* Abends-Min: 37
* Abends-Min: 38
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2121 in Waschstraße 1
* Abends-Min: 39
* Nach 6 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2122 in Waschstraße 2
* Nach 8 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2124 in Waschstraße 3
* Abends-Min: 40
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2146noch gewaschen werden.
* Füge Auto#2146 in Warteschlange Waschen
* CHooow! Sauge Auto#2202 in Box 2
* Abends-Min: 41
* Füge Auto#1009 in Warteschlange Beides
* Abends-Min: 42
* Abends-Min: 43
* Abends-Min: 44
* Abends-Min: 45
* Nach 7 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2127 in Waschstraße 1
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2134noch gewaschen werden.
* Füge Auto#2134 in Warteschlange Waschen
* CHooow! Sauge Auto#1009 in Box 1
* Nach 5 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2202noch gewaschen werden.
* Füge Auto#2202 in Warteschlange Waschen
* CHooow! Sauge Auto#2204 in Box 2
* Abends-Min: 46
* Füge Auto#2210 in Warteschlange Beides
* Nach 7 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2130 in Waschstraße 3
* Abends-Min: 47
* Nach 8 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2133 in Waschstraße 2
* Abends-Min: 48
* Abends-Min: 49
* Abends-Min: 50
* Abends-Min: 51
* Füge Auto#2211 in Warteschlange Beides
* Abends-Min: 52
* Abends-Min: 53
* Abends-Min: 54
* Abends-Min: 55
* Nach 10 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2204noch gewaschen werden.
* Füge Auto#2204 in Warteschlange Waschen
* CHooow! Sauge Auto#2205 in Box 2
* Abends-Min: 56
* Füge Auto#2212 in Warteschlange Beides
* Abends-Min: 57
* Nach 12 Minuten trocknet Waschstraße 1
* Splish! Wasche Auto#2134 in Waschstraße 1
* Nach 10 Minuten trocknet Waschstraße 2
* Splish! Wasche Auto#2135 in Waschstraße 2
* Nach 11 Minuten trocknet Waschstraße 3
* Splish! Wasche Auto#2137 in Waschstraße 3
* Abends-Min: 58
* Abends-Min: 59
* Abends-Min: 60
* Nach 15 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#1009noch gewaschen werden.
* Füge Auto#1009 in Warteschlange Waschen
* CHooow! Sauge Auto#2206 in Box 1
* Schließe Tankstelle. Alle Autos die gerade noch geputzt werden, dürfen dies Beenden. Der Rest muss leider morgen wiederkommen.
* Autos die auf Wäsche und Saugen gewartet haben: [Auto#2207, Auto#2208, Auto#2211, Auto#2212, Auto#2210]
* Autos die auf Wäsche gewartet haben: [Auto#1009, Auto#2140, Auto#2139, Auto#2146, Auto#2204, Auto#2202, Auto#2144]
* Autos die auf Saugen gewartet haben: []
* Nach 7 Minuten trocknet Waschstraße 2
* Nach 7 Minuten trocknet Waschstraße 3
* Nach 5 Minuten herrscht Stille in Box 1
* Jedoch möchte Auto#2206noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.
* Nach 9 Minuten trocknet Waschstraße 1
* Nach 15 Minuten herrscht Stille in Box 2
* Jedoch möchte Auto#2205noch gewaschen werden.
* Aber, da die Tankstelle geschlossenhat fährt es traurig davon.
