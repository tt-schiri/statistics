package de.edgesoft.statistics_plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.knowm.xchart.PieSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.jaxb.Set;
import de.edgesoft.statistics.model.SetModel;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.ChartUtils;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Chart generation extra time.
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
public class ExtraTimeGeneratorImpl implements IChartGenerator {

	public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
			final Season theSeason
			) throws StatisticsException {

		Map<String, Chart<? extends Styler, ? extends Series>> mapReturn = new HashMap<>();

		List<Match> lstMatches = theSeason.getMatch();

		// extra time
		List<Set> lstAllSets = new ArrayList<>();
		List<Set> lstLastSets = new ArrayList<>();
		for (int set_count = SET_COUNT_MIN; set_count <= SET_COUNT_MAX; set_count++) {

			List<Set> lstSingleSets = new ArrayList<>();
			for (Match theMatch : lstMatches) {

				if (theMatch.getSet().size() >= set_count) {
					Set theSet = theMatch.getSet().get(set_count - 1);
					if (theSet.getResult().getNumber().intValue() >= EXTRA_TIME_POINTS) {
						lstSingleSets.add(theSet);
						lstAllSets.add(theSet);
						if (theMatch.getSet().size() == set_count) {
							lstLastSets.add(theSet);
						}
					}

				}

			}

			mapReturn.put(String.format("extra-set-%d-win-loss", set_count), ChartUtils.generatePieChart(
					String.format("V-Satz %d: +/-", set_count),
					Optional.empty(),
					new PieSeries("+", lstSingleSets.stream().filter(SetModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstSingleSets.stream().filter(SetModel.LOST).collect(Collectors.toList()).size())
					));

		}
		mapReturn.put("extra-win-loss", ChartUtils.generatePieChart(
				"Verlängerung: +/-",
				Optional.empty(),
				new PieSeries("+", lstAllSets.stream().filter(SetModel.WON).collect(Collectors.toList()).size()),
				new PieSeries("-", lstAllSets.stream().filter(SetModel.LOST).collect(Collectors.toList()).size())
				));
		mapReturn.put("extra-last-set-win-loss", ChartUtils.generatePieChart(
				"V-Letzter-Satz: +/-",
				Optional.empty(),
				new PieSeries("+", lstLastSets.stream().filter(SetModel.WON).collect(Collectors.toList()).size()),
				new PieSeries("-", lstLastSets.stream().filter(SetModel.LOST).collect(Collectors.toList()).size())
				));

		return mapReturn;

	}

}

/* EOF */
