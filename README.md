# OpenTT-Statistics

- [Deutsch](#deutsch)
- [English](#english)
- [](#legal-stuff)

## Deutsch

Teil des Projekts "OpenTT", offene Dokumente und Programme rund um Tischtennis.

Dieser Teil enthält Statistik: Auswertung von Zahlen.

Lizenz der Dokumente: Creative-Commons-Lizenz Namensnennung - Nicht-kommerziell - Weitergabe unter gleichen Bedingungen 4.0 International
Siehe Datei LICENSE.

Lizenz der Programme: GNU General Public License.
Siehe Datei COPYING.

Bedeutet:

- die Dokumente sind frei, solange man
	- kein Geld damit macht
	- den Urheber nennt
	- abgeleitete Dokumente (Erweiterungen etc.) unter den gleichen Bedingungen weitergibt
- die Programme sind frei und Open Source
	- der Quellcode der Programme muss mitgeliefert werden
	- geänderter Quellcode muss unter der gleichen Lizenz veröffentlicht werden

### Was soll das? Kann ich mitmachen?

Ich will einige Daten statistisch auswerten, evtl. inspiriert das jemand anders, das auch zu tun.
Außerdem will ich so [R](https://www.r-project.org/) ausprobieren und üben.

Wer mitmachen will, ist gerne eingeladen.
Es gibt keine festen Aufgaben.

### Technische Details

#### Benötigte Programme

- R
- RStudio oder Editor Eurer Wahl, muss UTF-8 können
- LibreOffice, wenn Ihr die Daten nicht direkt eingeben wollt

#### Git-Repository

Kurze Details zum Aufbau des Git-Repositories:
Die Aufteilung in Zweige orientiert sich am Git-Branching-Modell, das in http://nvie.com/posts/a-successful-git-branching-model/ beschrieben wird.

Das heißt, es gibt immer mindestens drei Zweige:

1. `master` - enthält nur fertige Versionen
2. `develop` - Hauptzweig, gegen den entwickelt wird, dient der Synchronisation der feature-, release- und hotfix-Zweige
3. `feature-work` - Haupt-Arbeitszweig, in dem standardmäßig geschrieben wird

Zusätzlich werden bei Bedarf folgende Zweige genutzt:

- `feature-*` - für das Schreiben eines bestimmten Features/Textteils
- `release-*` - Synchronisation fertiger Versionen zwischen `develop` und `master`
- `hotfix-*` - schnelle Fehlerbehebung

## English

Part of the project "OpenTT" which provides open documents and applications for table tennis.

This part contains statistics: computing and using numbers.

License of the documents: Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
See file LICENSE.

License of the programs: GNU General Public License.
See file COPYING.

Which means:

- the documents are free, as long as you
	- don't make money with them
	- mention the creator
	- share derivates with the same license
- programs are free and open source
	- you have to provide the source code of the programs
	- if you change the source code, you have to distribute it under the same license

### What is this? Can I participate?

I want to do some statistical computing, maybe this inspires others to do the same.
Additionally, I want to try and learn [R](https://www.r-project.org/).

Everybody can participate, at the moment I don't have special tasks.

### Technical details

#### Needed programs

- R
- RStudio or editor of your choice, has to be able to handle UTF-8
- LibreOffice, if you do not want to input data in an editor

#### Git repository

Short details about the structure of the git repository:
The branches are constructed regarding the git branching model of http://nvie.com/posts/a-successful-git-branching-model/

This means, there are always at least three branches:

1. `master` - contains released versions
2. `develop` - main synchronisation branch for feature, release, and hotfix branches
3. `feature-work` - main wirking branch for development

Additionally, the following branches my occur:

- `feature-*` - writing a special feature
- `release-*` - synchronizing release versions between `develop` and `master`
- `hotfix-*` - fast bugfixes

## Legal stuff [legal]

Copyright 2015 Ekkart Kleinod <ekleinod@edgesoft.de>

The program is distributed under the terms of the GNU General Public License.

See COPYING for details.

This file is part of OpenTT-Statistics.

OpenTT-Statistics is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

OpenTT-Statistics is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with OpenTT-Statistics.  If not, see <http://www.gnu.org/licenses/>.

