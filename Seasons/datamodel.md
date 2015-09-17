# OpenTT-Statistics Data Model

## Deutsch

Die Daten werden aus einer csv-Datei eingelesen.
Ich erzeuge die Datei über LibreOffice, wo ich sie von Hand eingebe.

Wichtig ist, dass die Daten immer aus Perspektive des Spielers eingegeben werden.
Das erleichtert mir derzeit die Auswertung.

Die Punkte/Sätze des Spielers haben den Suffix "P" (player), die des Gegners "O" (opponent).

Beispiele

- Heimspiel 3:0 gewonnen: H/A = H, Sets P = 3, Sets O = 0
- Auswärtsspiel 3:1 gewonnen: H/A = A, Sets P = 3, Sets O = 1

## English

Data is read from a csv file.
I create the csv file from LibreOffice, I type them in by hand.

Important: data is given from the player's perspective.
This eases the computation at the moment.

Points/Sets of the player get the suffix "P", those of the opponent "O".

Examples:

- Won home match 3:0: H/A = H, Sets P = 3, Sets O = 0
- Won away match 3:1: H/A = A, Sets P = 3, Sets O = 1

## Spalten/Columns

Name  | Bedeutung  | Description
------|------------|------------
Date  | Datum  | date
Description  | Beschreibung  | description
H/A  | Heim/Auswärts  | home/away
S1P  | Punkte Satz 1 Spieler  | points set 1 player
S1O  | Punkte Satz 1 Gegner  | points set 1 opponent
Sets P  | Sätze Spieler  | sets player
Sets O  | Sätze Gegner  | sets opponent
LPZ-Diff  | LPZ Differenz  | lpz difference
Live-PZ  | Neue LPZ	 | new lpz

