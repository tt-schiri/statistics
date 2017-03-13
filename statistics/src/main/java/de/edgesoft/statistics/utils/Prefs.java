package de.edgesoft.statistics.utils;

import java.util.prefs.Preferences;

import de.edgesoft.statistics.Statistics;

/**
 * Preferences of the referee manager.
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
public class Prefs {

	/**
	 * Preferences object.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	private static Preferences preferences = null;

	/**
	 * Returns preferences.
	 *
	 * @return preferences
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	private static Preferences getPreferences() {
		if (preferences == null) {
			preferences = Preferences.userNodeForPackage(Statistics.class);
		}
		return preferences;
	}

	/**
	 * Get preference for key.
	 *
	 * @param theKey preference key
	 * @return preference value
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static String get(final PrefKey theKey) {

		switch (theKey) {
			default:
				return getPreferences().get(theKey.value(), "");
		}

	}

	/**
	 * Set preference value for key.
	 *
	 * @param theKey preference key
	 * @param theValue value
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void put(final PrefKey theKey, final String theValue) {
		put(theKey.value(), theValue);
	}

	/**
	 * Set preference value for text key.
	 *
	 * @param theKey text key
	 * @param theValue value
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void put(final String theKey, final String theValue) {
		getPreferences().put(theKey, theValue);
	}

}

/* EOF */
