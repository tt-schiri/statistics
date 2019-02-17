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

import de.edgesoft.edgeutils.xchart.Colorschemes;
import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.model.MatchModel;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.ChartUtils;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Chart generation home-off.
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
public class HomeOffGeneratorImpl implements IChartGenerator {

	public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
			final Season theSeason
			) throws StatisticsException {

		Map<String, Chart<? extends Styler, ? extends Series>> mapReturn = new HashMap<>();

		List<Match> lstMatches = theSeason.getMatch();

		// home/off
		mapReturn.put("home-off", ChartUtils.generatePieChart(
				"Heim - Auswärts",
				Optional.of(Colorschemes.Paired_qualitative_2),
				new PieSeries("Heim", lstMatches.stream().filter(MatchModel.HOME).collect(Collectors.toList()).size()),
				new PieSeries("Auswärts", lstMatches.stream().filter(MatchModel.OFF).collect(Collectors.toList()).size())
				));

		return mapReturn;

	}

}

/* EOF */
