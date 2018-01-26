package de.edgesoft.statistics_plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.knowm.xchart.PieSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.model.MatchModel;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.ChartUtils;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Generation plugins.
 *
 * ## Legal stuff
 *
 * Copyright 2015-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * The program is distributed under the terms of the GNU General Public License.
 *
 * See COPYING for details.
 *
 * This file is part of TT-Schiri: Statistics.
 *
 * TT-Schiri: Statistics is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Statistics is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TT-Schiri: Statistics.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.5.0
 * @since 0.5.0
 */
public class GeneratorPlugins extends Plugin {

	public GeneratorPlugins(PluginWrapper wrapper) {
        super(wrapper);
    }

	/**
	 * Chart generation wins-losses.
	 */
	@Extension
	public static class WinLossGenerator implements IChartGenerator {

		public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
				final Season theSeason
				) throws StatisticsException {

			Map<String, Chart<? extends Styler, ? extends Series>> mapReturn = new HashMap<>();

			List<Match> lstMatches = theSeason.getMatch();

			// wins/losses
			mapReturn.put("win-loss", ChartUtils.generatePieChart(
					"+/-",
					Optional.empty(),
					new PieSeries("+", lstMatches.stream().filter(MatchModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstMatches.stream().filter(MatchModel.LOST).collect(Collectors.toList()).size())
					));

			// home - wins/losses
			mapReturn.put("home-win-loss", ChartUtils.generatePieChart(
					"Heim: +/-",
					Optional.empty(),
					new PieSeries("+", lstMatches.stream().filter(MatchModel.HOME).filter(MatchModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstMatches.stream().filter(MatchModel.HOME).filter(MatchModel.LOST).collect(Collectors.toList()).size())
					));

			// off - wins/losses
			mapReturn.put("off-win-loss", ChartUtils.generatePieChart(
					"Auswärts: +/-",
					Optional.empty(),
					new PieSeries("+", lstMatches.stream().filter(MatchModel.OFF).filter(MatchModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstMatches.stream().filter(MatchModel.OFF).filter(MatchModel.LOST).collect(Collectors.toList()).size())
					));

			return mapReturn;

		}

	}

}

/* EOF */
