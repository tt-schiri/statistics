package de.edgesoft.statistics_plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.knowm.xchart.PieSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.jaxb.Set;
import de.edgesoft.statistics.model.MatchModel;
import de.edgesoft.statistics.model.SetModel;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.ChartUtils;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Chart generation sets.
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
public class SetsGeneratorImpl implements IChartGenerator {

	public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
			final Season theSeason
			) throws StatisticsException {

		Map<String, Chart<? extends Styler, ? extends Series>> mapReturn = new HashMap<>();

		List<Match> lstMatches = theSeason.getMatch();

		// number of sets
		for (int i = SET_COUNT_MIN + 2; i <= SET_COUNT_MAX; i++) {

			// variable is needed, because the filter cannot use the loop variable directly
			int set_count = i;

			mapReturn.put(String.format("%d-sets-win-loss", set_count), ChartUtils.generatePieChart(
					String.format("%d Sätze: +/-", set_count),
					Optional.empty(),
					new PieSeries("+", lstMatches.stream().filter(match -> match.getResult().getNumber().getValue() == Integer.valueOf(set_count - 3)).filter(MatchModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstMatches.stream().filter(match -> match.getResult().getNumber().getValue() == Integer.valueOf(set_count - 3)).filter(MatchModel.LOST).collect(Collectors.toList()).size())
					));

		}

		// set results
		for (int set_count = SET_COUNT_MIN; set_count <= SET_COUNT_MAX; set_count++) {

			List<Set> lstSets = new ArrayList<>();
			for (Match theMatch : lstMatches) {
				if (theMatch.getSet().size() >= set_count) {
					lstSets.add(theMatch.getSet().get(set_count - 1));
				}
			}

			mapReturn.put(String.format("set-%d-win-loss", set_count), ChartUtils.generatePieChart(
					String.format("Satz %d: +/-", set_count),
					Optional.empty(),
					new PieSeries("+", lstSets.stream().filter(SetModel.WON).collect(Collectors.toList()).size()),
					new PieSeries("-", lstSets.stream().filter(SetModel.LOST).collect(Collectors.toList()).size())
					));

		}

		// after certain set result
		Map<String, List<Match>> mapResultMatches = new HashMap<>();
		for (Match theMatch : lstMatches) {
			int iWon = 0;
			int iLost = 0;

			for (Set theSet : theMatch.getSet()) {
				if (SetModel.WON.test(theSet)) {
					iWon++;
				} else {
					iLost++;
				}

				if ((iWon < 3) && (iLost < 3)) {
					String sKey = String.format(SET_RESULT_FORMAT, iWon, iLost);
					mapResultMatches.computeIfAbsent(sKey, list -> new ArrayList<>());
					mapResultMatches.get(sKey).add(theMatch);
				}

			}

		}

    	for (Entry<String, List<Match>> theResultMatch : mapResultMatches.entrySet()) {
    		mapReturn.put(String.format("%s-win-loss", theResultMatch.getKey().replace(':', '-')), ChartUtils.generatePieChart(
    				String.format("%s: +/-", theResultMatch.getKey()),
    				Optional.empty(),
    				new PieSeries("+", theResultMatch.getValue().stream().filter(MatchModel.WON).collect(Collectors.toList()).size()),
    				new PieSeries("-", theResultMatch.getValue().stream().filter(MatchModel.LOST).collect(Collectors.toList()).size())
    				));
    	}

		for (int set_count = SET_COUNT_ZERO; set_count <= SET_COUNT_MAX1; set_count++) {
			System.out.println(set_count);
		}

		return mapReturn;

	}

}

/* EOF */
