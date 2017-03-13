package de.edgesoft.statistics.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;

import de.edgesoft.statistics.Statistics;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Resources helper.
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
 * @version 0.1.0
 * @since 0.1.0
 */
public class Resources {

	/**
	 * Loads image from resources.
	 *
	 * @param theImagePath image path
	 * @return loaded image
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static Image loadImage(final String theImagePath) {
		return new Image(Statistics.class.getClassLoader().getResourceAsStream(theImagePath));
	}

	/**
	 * Loads fxml pane from resources.
	 *
	 * @param thePaneName pane name
	 * @return loaded pane
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static Map.Entry<Pane, FXMLLoader> loadPane(final String thePaneName) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Statistics.class.getResource(String.format("view/%s.fxml", thePaneName)));
			return new AbstractMap.SimpleImmutableEntry<>((Pane) loader.load(), loader);

		} catch (IOException e) {
			Statistics.logger.catching(e);
			return null;
		}

	}

	/**
	 * Loads file from resources.
	 *
	 * @param theFileName pane name
	 * @return loaded file as string
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static String loadFile(final String theFileName) {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Statistics.class.getClassLoader().getResourceAsStream(theFileName)))) {

			StringBuilder sbReturn = new StringBuilder();
			String sLine = null;

			while ((sLine = reader.readLine()) != null) {
				sbReturn.append(sLine);
			}

			return sbReturn.toString();

		} catch (Exception e) {
			Statistics.logger.catching(e);
			return "";
		}

	}

}

/* EOF */
