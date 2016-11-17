# encoding: utf-8

# Plots LivePZ development as line chart.
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

title = "LivePZ Development"

if (createfiles) {
  pdf(sprintf("%s-%s_%s.pdf", title, player, season))
}

lpz_range = range(seasondata$Live.PZ)

plot(seasondata$Live.PZ, type="n", main=title, xlab="", ylab="LivePZ", ylim=lpz_range, xaxt="n")
axis(1, at=which(seasondata$Date != ""), lab=seasondata[seasondata$Date != "", "Date"])
lines(seasondata$Live.PZ, type="s", lty=1, lwd=2, col=col_palette)
legend("bottomleft", c(player), cex=0.8, lty=1, lwd=2, col=col_palette)

# flush output
if (createfiles) {
  dev.off()
}
