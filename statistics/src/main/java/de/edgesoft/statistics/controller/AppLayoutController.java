package de.edgesoft.statistics.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieSeries;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.xchart.ChartFactory;
import de.edgesoft.statistics.Statistics;
import de.edgesoft.statistics.jaxb.Content;
import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.ObjectFactory;
import de.edgesoft.statistics.jaxb.Result;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.jaxb.Set;
import de.edgesoft.statistics.utils.AlertUtils;
import de.edgesoft.statistics.utils.PrefKey;
import de.edgesoft.statistics.utils.Prefs;
import de.edgesoft.statistics.utils.Resources;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
 * @version 0.5.0
 * @since 0.5.0
 */
public class AppLayoutController {

	/**
	 * Application icon.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static final Image ICON = Resources.loadImage("images/icon-32.png");

	/**
	 * App border pane.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private BorderPane appPane;

	/**
	 * Menu item program -> quit.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private MenuItem mnuProgramQuit;

	/**
	 * Menu item help -> about.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private MenuItem mnuHelpAbout;

	/**
	 * Text field data file.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private TextField txtData;

	/**
	 * Text field output path.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private TextField txtOutpath;

	/**
	 * Text area for log.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private TextArea txtLog;

	/**
	 * Button create statistics.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private Button btnCreateStatistics;


	/**
	 * Primary stage.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private Stage primaryStage = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
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
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public void initController(final Stage thePrimaryStage) {

		primaryStage = thePrimaryStage;

		// set icon
		primaryStage.getIcons().add(ICON);

		// Show the scene containing the root layout.
		Scene scene = new Scene(appPane);
		primaryStage.setScene(scene);
		primaryStage.show();

		// load last values
		txtData.setText(Prefs.get(PrefKey.FILE));
		txtOutpath.setText(Prefs.get(PrefKey.OUTPATH));

		// set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			handleProgramExit();
		});


		// testing
		handleCreateStatistics();
		handleProgramExit();

	}

	/**
	 * Program menu exit.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
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
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private void handleHelpAbout() {

		Alert alert = AlertUtils.createAlert(AlertType.INFORMATION,
				primaryStage,
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
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@FXML
	private void handleCreateStatistics() {

		txtLog.clear();

		Path pathDataFile = Paths.get(txtData.getText());
		txtLog.setText(String.format("%s%n%s", txtLog.getText(), MessageFormat.format("Lade Daten aus ''{0}''.", pathDataFile.toAbsolutePath().normalize().toString())));

		Content theContent = null;

		if (pathDataFile.toString().endsWith(".csv")) {
			theContent = readFromCSV(pathDataFile);
		}

		if (pathDataFile.toString().endsWith(".ods")) {
			theContent = readFromODS(pathDataFile);
		}

		if ((theContent == null) || theContent.getSeason().isEmpty()) {
			txtLog.setText(String.format("%s%n%s", txtLog.getText(), "Keine Daten vorhanden."));
			return;
		}

		Path pathOut = Paths.get(txtOutpath.getText());
		txtLog.setText(String.format("%s%n%s", txtLog.getText(), MessageFormat.format("Erzeuge Grafiken in ''{0}''.", pathOut.toAbsolutePath().normalize().toString())));

		List<Match> lstMatches = theContent.getSeason().get(theContent.getSeason().size() - 1).getMatch();

		// wins - losses
		writePieChart(Paths.get(pathOut.toString(), String.format("%s.png", "win-loss")),
				"gewonnen - verloren",
				getPieSeries(
						lstMatches.stream().filter(match -> match.getSetResult().getWon().getValue()).collect(Collectors.toList()).size(),
						lstMatches.size()
						)
				);

		// home - off
		writePieChart(Paths.get(pathOut.toString(), String.format("%s.png", "home-off")),
				"Heim - Auswärts",
				getPieSeries(
						lstMatches.stream().filter(match -> match.getHome().getValue()).collect(Collectors.toList()).size(),
						lstMatches.size()
						)
				);

	}

	/**
	 * Returns primary stage.
	 *
	 * @return primary stage
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Read content from csv file.
	 *
	 * @param theInputPath input path
	 * @return content
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private Content readFromCSV(final Path theInputPath) {

		Content theContent = new ObjectFactory().createContent();

		Season theSeason = new ObjectFactory().createSeason();
		theSeason.setTitle(new SimpleStringProperty("temp"));
		theContent.getSeason().add(theSeason);

		try (Reader in = new InputStreamReader(new FileInputStream(theInputPath.toFile()), StandardCharsets.UTF_8)) {

			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

			// fill data model
			for (CSVRecord record : records) {

				if (record.get("H/A").isEmpty()) {
					continue;
				}

				Match theMatch = new ObjectFactory().createMatch();

				theMatch.setDate(new SimpleObjectProperty<LocalDate>(DateTimeUtils.parseDate(record.get("Date"))));
				theMatch.setTitle(new SimpleStringProperty(record.get("Description")));

				theMatch.setHome(new SimpleBooleanProperty(record.get("H/A").equals("H")));

				int iLPZDiff = Integer.parseInt(record.get("LPZ-Diff"));
				int iLPZ = Integer.parseInt(record.get("Live-PZ"));
				int iLPZOther = Integer.parseInt(record.get("Live-PZ-O"));

				theMatch.setLivePzBefore(new SimpleIntegerProperty(iLPZ - iLPZDiff));
				theMatch.setLivePzOther(new SimpleIntegerProperty(iLPZOther));
				theMatch.setLivePzDiff(new SimpleIntegerProperty(iLPZDiff));
				theMatch.setLivePzAfter(new SimpleIntegerProperty(iLPZ));

				for (int i = 1; i <= 5; i++) {
					if (!record.get(String.format("S%dP", i)).isEmpty()) {

						Set theSet = new ObjectFactory().createSet();

						Result theResult = new ObjectFactory().createResult();

						theResult.setWon(new SimpleBooleanProperty(record.get(String.format("S%d", i)).equals("+")));
						theResult.setNumber(new SimpleIntegerProperty(Integer.parseInt(record.get(String.format("S%dP", i)))));

						theSet.setResult(theResult);
						theMatch.getSet().add(theSet);
					}
				}

				Result theResult = new ObjectFactory().createResult();

				theResult.setWon(new SimpleBooleanProperty(record.get("Sets").equals("+")));
				theResult.setNumber(new SimpleIntegerProperty(Integer.parseInt(record.get("SetsP"))));

				theMatch.setSetResult(theResult);

				theSeason.getMatch().add(theMatch);

				txtLog.setText(String.format("%s%n  %03d - %s (%s)", txtLog.getText(),
						theSeason.getMatch().size(), theMatch.getTitle().getValue(),
						theMatch.getSetResult().getWon().getValue() ? "gewonnen" : "verloren"
						));

			}

		} catch (IOException | IllegalStateException e) {
			e.printStackTrace();
			txtLog.setText(String.format("%s%n  %s", txtLog.getText(), e.getMessage()));
		}

		return theContent;

	}

	/**
	 * Read content from ods file.
	 *
	 * @param theInputPath input path
	 * @return content
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private Content readFromODS(final Path theInputPath) {

		Content theContent = new ObjectFactory().createContent();

		try {

			SpreadsheetDocument theDoc = SpreadsheetDocument.loadDocument(theInputPath.toFile());

			for (int iSheet = 0; (iSheet < theDoc.getSheetCount()); iSheet++) {

				Table theSheet = theDoc.getSheetByIndex(iSheet);

				Season theSeason = new ObjectFactory().createSeason();
				theSeason.setTitle(new SimpleStringProperty(theSheet.getTableName()));
				theContent.getSeason().add(theSeason);

				Map<String, Integer> mapHeader = new HashMap<>();
				Row rowHeader = theSheet.getRowByIndex(0);
				for (int iCell = 0; (!rowHeader.getCellByIndex(iCell).getDisplayText().isEmpty()); iCell++) {
					mapHeader.put(rowHeader.getCellByIndex(iCell).getDisplayText(), iCell);
				}

				for (int iRow = 1; (!theSheet.getRowByIndex(iRow).getCellByIndex(0).getDisplayText().isEmpty()); iRow++) {

					Row theRow = theSheet.getRowByIndex(iRow);

					if (theRow.getCellByIndex(mapHeader.get("H/A")).getDisplayText().isEmpty()) {
						continue;
					}

					Match theMatch = new ObjectFactory().createMatch();

					theMatch.setDate(new SimpleObjectProperty<LocalDate>(DateTimeUtils.parseDate(theRow.getCellByIndex(mapHeader.get("Date")).getDisplayText())));
					theMatch.setTitle(new SimpleStringProperty(theRow.getCellByIndex(mapHeader.get("Description")).getDisplayText()));

					theMatch.setHome(new SimpleBooleanProperty(theRow.getCellByIndex(mapHeader.get("H/A")).getDisplayText().equals("H")));

					int iLPZDiff = Integer.parseInt(theRow.getCellByIndex(mapHeader.get("LPZ-Diff")).getDisplayText());
					int iLPZ = Integer.parseInt(theRow.getCellByIndex(mapHeader.get("Live-PZ")).getDisplayText());
					int iLPZOther = Integer.parseInt(theRow.getCellByIndex(mapHeader.get("Live-PZ-O")).getDisplayText());

					theMatch.setLivePzBefore(new SimpleIntegerProperty(iLPZ - iLPZDiff));
					theMatch.setLivePzOther(new SimpleIntegerProperty(iLPZOther));
					theMatch.setLivePzDiff(new SimpleIntegerProperty(iLPZDiff));
					theMatch.setLivePzAfter(new SimpleIntegerProperty(iLPZ));

					for (int i = 1; i <= 5; i++) {
						if (!theRow.getCellByIndex(mapHeader.get(String.format("S%dP", i))).getDisplayText().isEmpty()) {

							Set theSet = new ObjectFactory().createSet();

							Result theResult = new ObjectFactory().createResult();

							theResult.setWon(new SimpleBooleanProperty(theRow.getCellByIndex(mapHeader.get(String.format("S%d", i))).getDisplayText().equals("+")));
							theResult.setNumber(new SimpleIntegerProperty(Integer.parseInt(theRow.getCellByIndex(mapHeader.get(String.format("S%dP", i))).getDisplayText())));

							theSet.setResult(theResult);
							theMatch.getSet().add(theSet);
						}
					}

					Result theResult = new ObjectFactory().createResult();

					theResult.setWon(new SimpleBooleanProperty(theRow.getCellByIndex(mapHeader.get("Sets")).getDisplayText().equals("+")));
					theResult.setNumber(new SimpleIntegerProperty(Integer.parseInt(theRow.getCellByIndex(mapHeader.get("SetsP")).getDisplayText())));

					theMatch.setSetResult(theResult);

					theSeason.getMatch().add(theMatch);

					txtLog.setText(String.format("%s%n  %03d - %s (%s)", txtLog.getText(),
							theSeason.getMatch().size(), theMatch.getTitle().getValue(),
							theMatch.getSetResult().getWon().getValue() ? "gewonnen" : "verloren"
							));

				}

			}

			theDoc.close();

		} catch (Exception e) {
			e.printStackTrace();
			txtLog.setText(String.format("%s%n  %s", txtLog.getText(), e.getMessage()));
		}

		return theContent;

	}

	/**
	 * Write pie chart.
	 *
	 * @param theOutputPath output path
	 * @param theTitle chart title
	 * @param theSeries chart data
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private void writePieChart(final Path theOutputPath, final String theTitle, final PieSeries... theSeries) {

		try {

		    PieChart chart = ChartFactory.createPieChart(theTitle, OptionalInt.of(300), OptionalInt.of(300), Optional.empty());

		    for (PieSeries pieSeries : theSeries) {
		    	chart.getSeriesMap().put(pieSeries.getName(), pieSeries);
			}

		    BitmapEncoder.saveBitmap(chart, theOutputPath.toString(), BitmapFormat.PNG);
			txtLog.setText(String.format("%s%n  %s", txtLog.getText(), theOutputPath.toString()));

		} catch (IOException e) {
			e.printStackTrace();
			txtLog.setText(String.format("%s%n  %s", txtLog.getText(), e.getMessage()));
		}

	}

	/**
	 * Returns pie series.
	 *
	 * @param theOutputPath output path
	 * @param theTitle chart title
	 * @param theSeries chart data
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private PieSeries[] getPieSeries(final int theValue, final int theMaxValue) {

		List<PieSeries> lstReturn = new ArrayList<>();

		lstReturn.add(new PieSeries(String.format("%d", theValue), theValue));
		lstReturn.add(new PieSeries(String.format("%d", theMaxValue - theValue), theMaxValue - theValue));

		return lstReturn.toArray(new PieSeries[lstReturn.size()]);

	}

}

/* EOF */
