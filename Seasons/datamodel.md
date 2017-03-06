# OpenTT-Statistics Datenmodell/Data Model

## Deutsch

Die Daten werden aus einer csv-Datei eingelesen.
Ich erzeuge die Datei über LibreOffice, wo ich sie von Hand eingebe.

Wichtig ist, dass die Daten immer aus Perspektive des Spielers eingegeben werden.
Das erleichtert mir derzeit die Auswertung.

Die erste Angabe ist, ob der Satz gewonnen ("+") oder verloren ("-") wurde, kein Suffix.
Die zweite Angabe ist die Punktzahl (Suffix "P"), mit der gewonnen bzw. verloren wurde.

Beispiele

- Heimspiel 3:0 gewonnen: H/A = H, Sets = +, SetsP = 0
- Auswärtsspiel 3:1 gewonnen: H/A = A, Sets = +, SetsP = 1

## English

Data is read from a csv file.
I create the csv file from LibreOffice, I type them in by hand.

Important: data is given from the player's perspective.
This eases the computation at the moment.

The first column states if the game/set was won ("+") or lost ("-"), no suffix.
The second column contains the points (suffix "P") of the win resp. loss.

Examples:

- Won home match 3:0: H/A = H, Sets = +, SetsP = 0
- Won away match 3:1: H/A = A, Sets = +, SetsP = 1

## Spalten/Columns

Name  | Bedeutung  | Description
------|------------|------------
Date  | Datum  | date
Description  | Beschreibung  | description
H/A  | Heim/Auswärts  | home/away
S1  | gewonnen/verloren  | won/lost
S1P  | Punkte Satz 1  | points set 1
...  |  |
Sets  | gewonnen/verloren  | won/lost
SetsP  | Sätze  | sets
LPZ-Diff  | LPZ Differenz  | lpz difference
Live-PZ  | Neue LPZ	 | new lpz

