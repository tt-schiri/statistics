# encoding: utf-8

# Reads seasondata data from csv file.
#
# @author ekleinod
# @version 0.3
# @since 0.1

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

source("Config.R")

# install and load RColorBrewer for nice colors
if (!require("RColorBrewer")) {
  install.packages("RColorBrewer")
  library(RColorBrewer)
  #display.brewer.all() # show all available color palettes
}

# load seasondata data
seasondata = read.table(sprintf("Season-%s_%s.csv", player, season), encoding="UTF-8", sep=",", quote="\"", fill=TRUE, header=TRUE)

# debug outputs

# output data
seasondata

# names
seasondata_names = names(seasondata)
seasondata_names

# values
seasondata$H.A
seasondata$LPZ.Diff
seasondata$Live.PZ

# home/away levels
ha_levels = setdiff(levels(seasondata$H.A), c(""))

# win/loss levels
wl_levels = c("wins", "losses")
