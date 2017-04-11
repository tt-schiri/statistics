package de.edgesoft.statistics.model;

import java.util.function.Predicate;

import de.edgesoft.statistics.jaxb.Set;

/**
 * Set model, additional methods for jaxb model class.
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
public class SetModel extends Set {

	/**
	 * Filter predicate for won sets.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static Predicate<Set> WON = set -> set.getResult().getWon().getValue();

	/**
	 * Filter predicate for lost sets.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static Predicate<Set> LOST = WON.negate();

}

/* EOF */
