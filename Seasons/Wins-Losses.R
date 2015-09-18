# encoding: utf-8

# Plots wins and losses as different charts.
#
# Not functional yet.
#
# @author ekleinod
# @version 0.2
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
match_wins = season[,"Sets.P"] == "3"
match_losses = season[,"Sets.P"] != "3"

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
