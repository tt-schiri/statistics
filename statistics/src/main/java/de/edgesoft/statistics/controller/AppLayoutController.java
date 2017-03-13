package de.edgesoft.statistics.controller;

import java.text.MessageFormat;
import java.util.Map;

import de.edgesoft.statistics.Statistics;
import de.edgesoft.statistics.utils.AlertUtils;
import de.edgesoft.statistics.utils.PrefKey;
import de.edgesoft.statistics.utils.Prefs;
import de.edgesoft.statistics.utils.Resources;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for application layout.
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
public class AppLayoutController {

	/**
	 * Application icon.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static final Image ICON = Resources.loadImage("images/icon-32.png");

	/**
	 * App border pane.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private BorderPane appPane;

	/**
	 * Menu item program -> quit.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private MenuItem mnuProgramQuit;

	/**
	 * Menu item help -> about.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private MenuItem mnuHelpAbout;

	/**
	 * Text field data file.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private TextField txtData;

	/**
	 * Text field output path.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private TextField txtOutpath;

	/**
	 * Button create statistics.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private Button btnCreateStatistics;


	/**
	 * Primary stage.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	private Stage primaryStage = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private void initialize() {

		// icons
		mnuProgramQuit.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/application-exit.png")));
		mnuHelpAbout.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/help-about.png")));

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param thePrimaryStage primary stage
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public void initController(final Stage thePrimaryStage) {

		primaryStage = thePrimaryStage;

				// set icon
		primaryStage.getIcons().add(ICON);

		// Show the scene containing the root layout.
		Scene scene = new Scene(appPane);
		primaryStage.setScene(scene);
		primaryStage.show();

		// load last paths
		txtData.setText(Prefs.get(PrefKey.FILE));
		txtOutpath.setText(Prefs.get(PrefKey.OUTPATH));

		// set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
					event.consume();
					handleProgramExit();
				});

	}

	/**
	 * Program menu exit.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	public void handleProgramExit() {

		Prefs.put(PrefKey.FILE, txtData.getText());
		Prefs.put(PrefKey.OUTPATH, txtOutpath.getText());

		Platform.exit();
	}

	/**
	 * Help menu about.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private void handleHelpAbout() {

		Alert alert = AlertUtils.createAlert(AlertType.INFORMATION, primaryStage,
				"Über \"Statistics\"",
				MessageFormat.format("Statistics Version {0}", Statistics.VERSION),
				null
				);

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("AboutText");
		VBox aboutText = (VBox) pneLoad.getKey();
		alert.getDialogPane().contentProperty().set(aboutText);

		alert.setGraphic(new ImageView(Resources.loadImage("images/icon-64.png")));
		alert.showAndWait();

	}

	/**
	 * Create statistics.
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@FXML
	private void handleCreateStatistics() {

		Statistics.logger.error("handleCreateStatistics not implemented yet");

	}

	/**
	 * Returns primary stage.
	 *
	 * @return primary stage
	 *
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

/* EOF */
