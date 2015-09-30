# encoding: utf-8

# Plots wins and losses as different charts.
#
# Not functional yet.
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

# load data
source("GetData.R")

col_palette = brewer.pal(8, "Dark2")

title = "Wins and Losses"

if (createfiles) {
  pdf(sprintf("%s-%s.pdf", title, player))
}

matches = is.element(season[,"H.A"], ha_levels)

# prepare data frame
counts = data.frame(0,0,0)
colnames(counts) = c("wins", "losses", "sum")
rownames(counts) = c("all")

for (i in 0:2) {
  for (ha in ha_levels) {
    counts = rbind(counts, c(0,0,0))
    rownames(counts)[nrow(counts)] = sprintf("3:%d%s", i, ha)
  }
  counts = rbind(counts, c(0,0,0))
  rownames(counts)[nrow(counts)] = sprintf("3:%d", i)
}

# count wins and losses
match_wl = data.frame(season[,"Sets.P"] == "3", season[,"Sets.P"] != "3")
colnames(match_wl) = wl_levels
!(match_wl[,"wins"])
for (i in 0:2) {
  for (ha in ha_levels) {
    for (wl in wl_levels) {
      counts[sprintf("3:%d%s", i, ha), wl] = counts[sprintf("3:%d%s", i, ha), wl] + nrow(season[matches & match_wl[,wl] & season[,"H.A"] == ha & season[,"Sets.O"] == i,])
    }
  }
}
counts
# some sums for convenience

#for (i in 0:2) {
#  sum(counts[paste(c("3:2"), ha_levels, sep=""), "wins"])
#}

#counts["all", "wins"] = sum(counts)

paste(c("3:","3:", "3:"), ha_levels, sep="")
paste(c("3:","3:", "3:"), 0:2, sep="")
paste(paste(c("3:","3:", "3:"), 0:2, sep=""), ha_levels, sep="")





wins = nrow(season[matches & match_wins,])
losses = nrow(season[matches & match_losses,])
sum = wins + losses

pie(
  # values
  c(wins, losses),
  # labels
  labels = "", # c("Wins", "Losses"),
  # title
  main = title,
  # colors
  col = col_palette,
  # no borders
  lty = 0
)
legend("topright", c(sprintf("Wins: %d (%1.0f %%)", wins, (wins/sum * 100)), sprintf("Losses: %d (%1.0f %%)", losses, (losses/sum * 100))), cex=0.8, fill=col_palette)

# flush output
if (createfiles) {
  dev.off()
}
