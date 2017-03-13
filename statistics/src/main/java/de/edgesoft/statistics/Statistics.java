package de.edgesoft.statistics;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;

import de.edgesoft.edgeutils.commons.Version;
import de.edgesoft.edgeutils.commons.ext.VersionExt;
import de.edgesoft.statistics.controller.AppLayoutController;
import de.edgesoft.statistics.utils.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Statistics application.
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
public class Statistics extends Application {

	/**
	 * Central logger for all classes.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static final Logger logger = LogManager.getLogger(Statistics.class.getPackage().getName());

	/**
	 * Program and doc version.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static final Version VERSION = new VersionExt("0.1.0");

	/**
	 * Host services delegate.
	 *
	 * Needed for opening links in browser etc.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static HostServicesDelegate hostServices = null;

	/**
	 * Starts the application.
	 *
	 * @param args command line arguments
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * @param primaryStage primary stage
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	public void start(Stage primaryStage) {

		// load app layout and controller, then delegate control to controller
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("AppLayout");
		((AppLayoutController) pneLoad.getValue().getController()).initController(primaryStage);

		// host services
		hostServices = HostServicesFactory.getInstance(this);

	}

}

/* EOF */
