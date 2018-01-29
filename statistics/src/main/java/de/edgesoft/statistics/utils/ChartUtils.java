package de.edgesoft.statistics.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;

import de.edgesoft.edgeutils.xchart.ChartFactory;
import de.edgesoft.edgeutils.xchart.Colorschemes;

/**
 * Chart utilities for plugins.
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
public class ChartUtils {

	/**
	 * Chart size.
	 */
	public static final int CHARTSIZE = 150;

	/**
	 * Generates pie chart.
	 *
	 * @param theTitle chart title
	 * @param theColorscheme color scheme (optional)
	 * @param theSeries chart data
	 *
	 * @return generated chart
	 */
	public static PieChart generatePieChart(
			final String theTitle,
			final Optional<Colorschemes> theColorscheme,
			final PieSeries... theSeries
			) {

	    PieChart chart = ChartFactory.createPieChart(theTitle, CHARTSIZE, CHARTSIZE, Optional.empty(), Optional.of(theColorscheme.orElse(Colorschemes.PiYG_diverging_2)));

	    for (PieSeries series : theSeries) {
	    	chart.getSeriesMap().put(series.getName(), series);
		}

	    chart.getStyler().setDecimalPattern("#");
	    chart.getStyler().setSumVisible(true);
	    chart.getStyler().setSumFontSize(16f);

	    return chart;

	}

	/**
	 * Generates xy chart.
	 *
	 * @param theTitle chart title
	 * @param theColorscheme color scheme (optional)
	 * @param theSeries chart data
	 *
	 * @return generated chart
	 */
	public static XYChart generateXYChart(
			final String theTitle,
			final Optional<Colorschemes> theColorscheme,
			final XYSeries... theSeries) {

	    XYChart chart = ChartFactory.createXYChart(theTitle, CHARTSIZE, CHARTSIZE*4, theColorscheme);

	    for (XYSeries series : theSeries) {
	    	chart.getSeriesMap().put(series.getName(), series);
		}

	    chart.getStyler().setMarkerSize(4);

	    return chart;

	}

	/**
	 * Generates category chart.
	 *
	 * @param theTitle chart title
	 * @param theColorscheme color scheme (optional)
	 * @param theSeries chart data
	 *
	 * @return generated chart
	 */
	public static CategoryChart generateCategoryChart(
			final String theTitle,
			final Optional<Colorschemes> theColorscheme,
			final CategorySeries... theSeries) {

	    CategoryChart chart = ChartFactory.createCategoryChart(theTitle, CHARTSIZE, CHARTSIZE*4, Optional.empty(), theColorscheme);

	    List<Integer> lstMin = new ArrayList<>();

	    for (CategorySeries series : theSeries) {
	    	series.setFillColor(new Color(0, 0, 0, 0));
	    	series.setChartCategorySeriesRenderStyle(CategorySeriesRenderStyle.SteppedBar);

	    	lstMin.add(((Collection<Integer>) series.getYData()).stream().min(Integer::compare).get());

	    	chart.getSeriesMap().put(series.getName(), series);
		}

	    chart.getStyler().setYAxisMin(lstMin.stream().min(Integer::compare).get().doubleValue());

	    return chart;

	}

}

/* EOF */
