# encoding: utf-8

# Reads season data from csv file.

## Legal stuff
#
# Copyright 2015 Ekkart Kleinod <ekleinod@edgesoft.de>
#   
# The program is distributed under the terms of the GNU General Public License.
# 
# See COPYING for details.
# 
# This file is part of OpenTT-Statistics.
# 
# OpenTT-Statistics is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# OpenTT-Statistics is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with OpenTT-Statistics.  If not, see <http://www.gnu.org/licenses/>.

# create output files (TRUE) or plot directly (FALSE)
createfiles = FALSE

# install and load RColorBrewer for nice colors
if (!require("RColorBrewer")) {
  install.packages("RColorBrewer")
  library(RColorBrewer)
  #display.brewer.all() # show all available color palettes
}

# player name (for file names, later maybe a function parameter)
player = "Ekkart"

# load season data
season = read.table(sprintf("Season-%s.csv", player), encoding="UTF-8", sep=",", quote="\"", fill=TRUE, header=TRUE)

# debug outputs

# output data
season

# names
season_names = names(season)
season_names

# values
season$H.A
season$LPZ.Diff
season$Live.PZ
