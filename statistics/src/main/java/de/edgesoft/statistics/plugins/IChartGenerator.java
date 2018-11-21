package de.edgesoft.statistics.plugins;

import java.util.Map;

import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;
import org.pf4j.ExtensionPoint;

import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.utils.StatisticsException;

/**
 * Interface for chart generation plugins.
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
public interface IChartGenerator extends ExtensionPoint {

	/**
	 * Set result format.
	 */
	public static final String SET_RESULT_FORMAT = "%d:%d";

	/**
	 * Set count zero.
	 */
	public static final int SET_COUNT_ZERO = 0;

	/**
	 * Set count best-of.
	 */
	public static final int SET_COUNT_BEST_OF = 5;

	/**
	 * Set count max number of set per opponent.
	 */
	public static final int SET_COUNT_MAX1 = ((int) (SET_COUNT_BEST_OF / 2)) + 1;

	/**
	 * Minimum set count.
	 */
	public static final int SET_COUNT_MIN = 1;

	/**
	 * Maximum set count.
	 */
	public static final int SET_COUNT_MAX = 5;

	/**
	 * Extra time points.
	 */
	public static final int EXTRA_TIME_POINTS = 10;

	/**
	 * Generates chart(s) and according filenames from season data.
	 *
	 * @param theSeason season data
	 * @return map of charts with their unique filenames
	 */
	public Map<String, Chart<? extends Styler, ? extends Series>> generateChart(
			final Season theSeason
			) throws StatisticsException;

}

/* EOF */
