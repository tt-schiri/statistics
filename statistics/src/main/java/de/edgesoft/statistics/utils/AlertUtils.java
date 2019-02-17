package de.edgesoft.statistics.utils;

import de.edgesoft.statistics.controller.AppLayoutController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Utility methods for alerts.
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
public class AlertUtils {

	/**
	 * Creates and initializes an alert.
	 *
	 * This method just encapsulates the tiresome setting of
	 * the icon and resizing the alert to fit the text.
	 *
	 * @param theAlertType alert type
	 * @param theOwner owning stage
	 * @param theTitle title
	 * @param theHeader header
	 * @param theContent content
	 *
	 * @return created alert
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static Alert createAlert(final AlertType theAlertType, final Stage theOwner,
			final String theTitle, final String theHeader, final String theContent) {

		Alert alert = new Alert(theAlertType);

		// set owning stage
		alert.initOwner(theOwner);

		// display all text and resize to height
		alert.setResizable(true);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

		// set alert icon
		switch (theAlertType) {
			case CONFIRMATION:
				alert.setGraphic(new ImageView(Resources.loadImage("icons/48x48/status/dialog-question.png")));
				break;
			case ERROR:
				alert.setGraphic(new ImageView(Resources.loadImage("icons/48x48/status/dialog-error.png")));
				break;
			case INFORMATION:
				alert.setGraphic(new ImageView(Resources.loadImage("icons/48x48/status/dialog-information.png")));
				break;
			case NONE:
				alert.setGraphic(new ImageView(Resources.loadImage("icons/48x48/status/image-missing.png")));
				break;
			case WARNING:
				alert.setGraphic(new ImageView(Resources.loadImage("icons/48x48/status/dialog-warning.png")));
				break;
		}

		// set window icon
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(AppLayoutController.ICON);

		// set texts
		alert.setTitle(theTitle);
		alert.setHeaderText(theHeader);
		alert.setContentText(theContent);

		return alert;

	}

	/**
	 * Creates and initializes an alert.
	 *
	 * This method just encapsulates the tiresome setting of
	 * the icon and resizing the alert to fit the text.
	 *
	 * @param theAlertType alert type
	 * @param theOwner owning stage
	 * @param theTitle title
	 * @param theHeader header
	 * @param theContent content
	 * @param theDetails detail message
	 * @param theLongtext long text
	 *
	 * @return created alert
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static Alert createExpandableAlert(final AlertType theAlertType, final Stage theOwner,
			final String theTitle, final String theHeader, final String theContent, final String theDetails, final String theLongtext) {

		Alert alert = createAlert(theAlertType, theOwner, theTitle, theHeader, theContent);

		Label label = new Label(theDetails);

		TextArea textArea = new TextArea(theLongtext);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		return alert;

	}

}

/* EOF */
