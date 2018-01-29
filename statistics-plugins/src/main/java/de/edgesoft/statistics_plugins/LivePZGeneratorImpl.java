package de.edgesoft.statistics_plugins;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.internal.series.Series.DataType;
import org.knowm.xchart.style.Styler;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.xchart.XYSeriesUtils;
import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.ChartUtils;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Chart generation LivePZ.
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
public class LivePZGeneratorImpl implements IChartGenerator {

	public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
			final Season theSeason
			) throws StatisticsException {

		Map<String, Chart<? extends Styler, ? extends Series>> mapReturn = new HashMap<>();

		List<Match> lstMatches = theSeason.getMatch();

		boolean hasLPZ = !lstMatches.isEmpty() && (lstMatches.get(0).getLivePzDiff() != null);

		// lpz charts
		if (hasLPZ) {

		    List<Date> lstDates = new ArrayList<>();
		    List<Integer> lstLPZ = new ArrayList<>();
		    List<Integer> lstLPZ0 = new ArrayList<>();
		    Match lastMatch = null;

		    if (!lstMatches.isEmpty()) {

		    	lstDates.add(DateTimeUtils.toDate((LocalDate) lstMatches.get(0).getDate().getValue()));
	    		lstLPZ.add(lstMatches.get(0).getLivePzBefore().getValue());
		    	lstLPZ0.add(0);

		    	for (Match theMatch : lstMatches) {

		    		lstDates.add(DateTimeUtils.toDate((LocalDate) theMatch.getDate().getValue()));
		    		lstLPZ.add(theMatch.getLivePzBefore().getValue());
		    		lstLPZ0.add(lstLPZ0.get(lstLPZ0.size() - 1) + theMatch.getLivePzDiff().getValue());

		    		lastMatch = theMatch;

		    	}

		    	lstDates.add(DateTimeUtils.toDate((LocalDate) lastMatch.getDate().getValue()));
		    	lstLPZ.add(lastMatch.getLivePzAfter().getValue());
	    		lstLPZ0.add(lstLPZ0.get(lstLPZ0.size() - 1) + lastMatch.getLivePzDiff().getValue());

		    }


	    	List<XYSeries> lstSeries = new ArrayList<>();
		    lstSeries.add(XYSeriesUtils.getXYSeries("Live-PZ", lstDates, lstLPZ, null, DataType.Date));

		    mapReturn.put("lpz", ChartUtils.generateXYChart(
					"Live-PZ",
					Optional.empty(),
					lstSeries.toArray(new XYSeries[lstSeries.size()])
					));

	    	lstSeries = new ArrayList<>();
		    lstSeries.add(XYSeriesUtils.getXYSeries("Live-PZ-Änderung", lstDates, lstLPZ0, null, DataType.Date));

		    mapReturn.put("lpz-change", ChartUtils.generateXYChart(
					"Live-PZ-Änderung",
					Optional.empty(),
					lstSeries.toArray(new XYSeries[lstSeries.size()])
					));

			List<CategorySeries> lstSeries2 = new ArrayList<>();

		    lstSeries2.add(new CategorySeries("Live-PZ", lstDates, lstLPZ, null, DataType.Date));

		    mapReturn.put("lpz2", ChartUtils.generateCategoryChart(
					"Live-PZ 2",
					Optional.empty(),
					lstSeries2.toArray(new CategorySeries[lstSeries2.size()])
					));

		}

		return mapReturn;

	}

}

/* EOF */
