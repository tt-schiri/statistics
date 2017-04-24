Quotes Language:		german
Base Header Level:	3
latex input:				basis-mmd-scrartcl
MyOptions:
latex input:				basis-mmd-style
Title:							TT-Statistik
MySubTitle:					Handbuch
Author:							Ekkart Kleinod
MyEMail:						ekleinod@edgesoft.de
Date:								18. April 2017
latex input:				basis-mmd-begin-doc

<!-- \maketitle -->

<!-- \tableofcontents -->

<!-- \cleardoublepage -->
# Installation

"TT-Statistik" ist ein Java-Programm, das heißt, das Programm läuft auf allen Rechnern, auf denen Java installiert ist.

## Anforderungen

Für "TT-Statistik" muss Java, mindestens in Version 8 installiert sein.
Java wird *nicht* automatisch installiert.

Für die Dateneingabe bietet es sich an, LibreOffice zu installieren.
Man kann die Daten auch direkt in eine csv-Datei eingeben, dafür würde ein Texteditor nötig sein.

## Windows-Installer

"TT-Statistik" kann als Windows-Installer heruntergeladen werden.
Der Installer installiert die jar-Datei und legt auf Wunsch eine Verknüpfung im Startmenü an.

- https://github.com/tt-schiri/statistics/releases/latest aufrufen
- dort den Link auf "tt-statistics_install.exe" klicken
- den Installer herunterladen und ausführen

## Herunterladen der jar-Datei

"TT-Statistik" ist eine jar-Datei, die wie folgt heruntergeladen wird:

- https://github.com/tt-schiri/statistics/releases/latest aufrufen
- dort den Link auf "tt-statistics.jar" klicken
- die jar-Datei auf der Festplatte speichern
- die Datei per Doppelklick oder von der Kommandozeile aus ausführen

Kommandozeile:

	java -jar tt-statistics.jar

<!-- \cleardoublepage -->
# Dateneingabe

Die Daten für die Statistik müssen per LibreOffice-Calc-Datei (ods) oder als csv-Datei bereitgestellt werden.
Das Datenformat ist aus den Beispieldateien im Repository zu entnehmen:

- [Season-Ekkart.ods](https://github.com/tt-schiri/statistics/blob/master/data/Einzel.ods)
- [2016-2017.csv](https://github.com/tt-schiri/statistics/blob/master/data/Einzel_2016-2017.csv)

Am besten ist es, die Dateien zu kopieren und anzupassen.
Das Statistikprogramm wertet genau eine csv-Datei aus oder das am weitesten rechts erscheinende Tabellenblatt der ods-Datei.

Die csv-Datei muss UTF-8-kodiert sein, alle Texte sind mit Anführungszeichen einzuschließen.
Mit LibreOffice funktioniert das, mit Microsoft Excel wird das schwierig.

Die Dateneingabe muss von Hand erfolgen.
Dies hat zwei Gründe:

1. ich habe keinen Importer aus TT-Live geschrieben und
2. ich bin mir nicht sicher, ob ich das rechtlich dürfte.

Kurz zusammengefasst, wie ich es gelernt habe:
die Daten, die in TT-Live über Euch erhoben werden, gehören Euch.
Die Darstellung der Daten, also die Datenbank, die Webseite etc. gehören Euch nicht.
Daher ist es rechtlich problematisch, die Daten per Skript auszulesen, aber kein Problem, die Daten abzutippen.



<!-- \cleardoublepage -->
# Bedienung

Nach dem Start erscheint die Oberfläche des Programms (siehe [](#gui)).

![Bedienoberfläche.][gui]

[gui]: images/gui.png width=".6\textwidth"

Datendatei
: hier gebt Ihr die Datei ein, aus der die Daten gelesen werden sollen

Ausgabepfad
: das ist das Verzeichnis, in das die Statistikdateien generiert werden sollen

Dateiformat
: hier wählt Ihr das Format, in dem die Grafiken erzeugt werden sollen

Statistikdateien erzeugen
: startet das Erzeugen der Dateien, je nach Ein- und Ausgabeformat kann das etwas dauern

Ein kleiner Überblick über die geleistete Arbeit erscheint in der Mitte des Programms, falls Fehler auftreten, werden auch diese dort ausgegeben.

Beim Beenden des Programms merkt sich das Programm die eingegebenen Daten und lädt sie beim nächsten Start neu.
Diese Daten findet Ihr hier (falls Ihr das braucht):

Windows-Registry
:	`HKEY_CURRENT_USER\Software\JavaSoft\Prefs\de\edgesoft\statistics`

Linux
: `~/.java/.userPrefs/de/edgesoft/statistics/prefs.xml`

## Dateigenerierung

Das Einlesen von LibreOffice-Calc-Dateien dauert etwas länger - nicht die Geduld verlieren.

Die erzeugten Dateien folgen dem Namensschema:

	<Präfix>_<Statistik>.<Endung>

Präfix
: bei LibreOffice-Calc-Dateien der Name des verwendeten Tabellenblatts
: bei csv-Dateien der Dateiname ohne ".csv"

Statistik
: Kürzel der Statistik

Endung
: je nach gewähltem Dateiformat

## Verfügbare Statistiken

Falls Euch Statistiken fehlen: als [Issue](https://github.com/tt-schiri/statistics/issues) melden oder mir eine E-Mail schreiben oder selbst programmieren und als Pull-Request einreichen.

### win-loss

Gewonnene und verlorene Spiele Gesamt.

0-1-win-loss
0-2-win-loss
1-0-win-loss
1-1-win-loss
1-2-win-loss
2-0-win-loss
2-1-win-loss
2-2-win-loss
3-sets-win-loss
4-sets-win-loss
5-sets-win-loss
home-off
home-win-loss
lpz
lpz2
lpz-change
off-win-loss
opp-strong-win-loss
opp-weak-win-loss
set-1-win-loss
set-2-win-loss
set-3-win-loss
set-4-win-loss
set-5-win-loss

