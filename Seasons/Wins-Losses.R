# encoding: utf-8

# Plots wins and losses as different charts.

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

# load data
source("GetData.R")

col_palette = brewer.pal(8, "Dark2")

title = "Wins and Losses"

if (createfiles) {
  pdf(sprintf("%s-%s.pdf", title, player))
}

# counting wins and losses - I have a feeling, this can be improved a lot
wl_cats = c("won", "lost")
ha_cats = c("H", "A")
counts = data.frame(
  "won" = c("3:0" = 0, "3:1" = 0, "3:2" = 0),
  "lost" = 0
)

for (i in 1:2) {
  for (j in 0:2) {
    counts[sprintf("3:%d", j), wl_cats[i]] = counts[sprintf("3:%d", j), wl_cats[i]] +
      nrow(season[season$H.A == ha_cats[i] & season[,sprintf("Sets.%s", ha_cats[i])] == "3" & season[,sprintf("Sets.%s", ha_cats[i%%2+1])] == j,])
  }
}

counts
sum(counts[,"won"])

nrow(season[season$H.A == cat & season[,sprintf("Sets.%s", cat)] == "3" & season[,sprintf("Sätze.%s", cat)] == "0",])
lost = lost + nrow(season[season$H.A == cat & season[,sprintf("Punkte.%s", cat)] == "0",])
counts

won = 0
lost = 0
for (cat in c("H", "A")) {
  won = won + nrow(season[season$H.A == cat & season[,sprintf("Sets.%s", cat)] == "3",])
  lost = lost + nrow(season[season$H.A == cat & season[,sprintf("Sets.%s", cat)] != "3",])
}
sum = won + lost

pie(
  # values
  c(won, lost),
  # labels
  labels = "", # c("Wins", "Losses"),
  # title
  main = title,
  # colors
  col = col_palette,
  # no borders
  lty = 0
)
legend("topright", c(sprintf("Wins: %d (%1.0f %%)", won, (won/sum * 100)), sprintf("Losses: %d (%1.0f %%)", lost, (lost/sum * 100))), cex=0.8, fill=col_palette)

# flush output
if (createfiles) {
  dev.off()
}
