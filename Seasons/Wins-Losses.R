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
  counts = rbind(counts, c(0,0,0))
  rownames(counts)[nrow(counts)] = sprintf("3:%d", i)
}
for (ha in ha_levels) {
  counts = rbind(counts, c(0,0,0))
  rownames(counts)[nrow(counts)] = sprintf("all%s", ha)
  for (i in 0:2) {
    counts = rbind(counts, c(0,0,0))
    rownames(counts)[nrow(counts)] = sprintf("3:%d%s", i, ha)
  }
}

# count wins and losses
match_wl = data.frame(season[,"Sets"] == "+", season[,"Sets"] == "-")
colnames(match_wl) = wl_levels
!(match_wl[,"wins"])
for (i in 0:2) {
  for (ha in ha_levels) {
    for (wl in wl_levels) {
      counts[sprintf("3:%d%s", i, ha), wl] = counts[sprintf("3:%d%s", i, ha), wl] + nrow(season[matches & match_wl[,wl] & season[,"H.A"] == ha & season[,"SetsP"] == i,])
    }
  }
}
counts

# some sums for convenience
# sets = A+H
for (i in 0:2) {
  for (wl in wl_levels) {
    counts[sprintf("3:%d", i), wl] = sum(counts[paste(sprintf("3:%d", i), ha_levels, sep = ""), wl])
  }
}
# all = sets
counts[paste("3:0", ha_levels, sep = ""), ]
for (wl in wl_levels) {
  counts["all", wl] = sum(counts[paste("3:", 0:2, sep = ""), wl])
}
# sums of rows
# tbd
counts

# overall wins and losses
wl_counts = counts["all", wl_levels]
sum = sum(wl_counts)
wl_percs = wl_counts/sum*100

pie(
  # values
  c(wl_counts["all","wins"], wl_counts["all","losses"]),
  # labels
  labels = "",
  # title
  main = title,
  # colors
  col = col_palette,
  # no borders
  lty = 0
)
legend("topright", 
       c(
         sprintf("Wins: %d (%1.0f %%)", wl_counts["all","wins"], wl_percs["all","wins"]), 
         sprintf("Losses: %d (%1.0f %%)", wl_counts["all","losses"], wl_percs["all","losses"])
         ), 
       cex=0.8, fill=col_palette)

# flush output
if (createfiles) {
  dev.off()
}
