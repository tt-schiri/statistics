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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.xchart.EncoderFormats;
import de.edgesoft.statistics.Statistics;
import de.edgesoft.statistics.jaxb.Content;
import de.edgesoft.statistics.jaxb.Match;
import de.edgesoft.statistics.jaxb.ObjectFactory;
import de.edgesoft.statistics.jaxb.Result;
import de.edgesoft.statistics.jaxb.Season;
import de.edgesoft.statistics.jaxb.Set;
import de.edgesoft.statistics.plugins.IChartGenerator;
import de.edgesoft.statistics.utils.AlertUtils;
import de.edgesoft.statistics.utils.PrefKey;
import de.edgesoft.statistics.utils.Prefs;
import de.edgesoft.statistics.utils.Resources;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	 */
	public static final Image ICON = Resources.loadImage("images/icon-32.png");

	/**
	 * App border pane.
	 */
	@FXML
	private BorderPane appPane;

	/**
	 * Menu item program -> quit.
	 */
	@FXML
	private MenuItem mnuProgramQuit;

	/**
	 * Menu item help -> about.
	 */
	@FXML
	private MenuItem mnuHelpAbout;

	/**
	 * Text field data file.
	 */
	@FXML
	private TextField txtData;

	/**
	 * Text field output path.
	 */
	@FXML
	private TextField txtOutpath;

	/**
	 * Radio button group file format.
	 */
	@FXML
	private ToggleGroup grpFileFormat;

	/**
	 * Radio button file format png.
	 */
	@FXML
	private RadioButton radPNG;

	/**
	 * Radio button file format jpg.
	 */
	@FXML
	private RadioButton radJPG;

	/**
	 * Radio button file format gif.
	 */
	@FXML
	private RadioButton radGIF;

	/**
	 * Radio button file format bmp.
	 */
	@FXML
	private RadioButton radBMP;

	/**
	 * Radio button file format svg.
	 */
	@FXML
	private RadioButton radSVG;

	/**
	 * Radio button file format pdf.
	 */
	@FXML
	private RadioButton radPDF;

	/**
	 * Radio button file format eps.
	 */
	@FXML
	private RadioButton radEPS;



	/**
	 * Text area for log.
	 */
	@FXML
	private TextArea txtLog;


	/**
	 * Button create statistics.
	 */
	@FXML
	private Button btnCreateStatistics;


	/**
	 * Primary stage.
	 */
	private Stage primaryStage = null;


	/**
	 * Busy property.
	 */
	private BooleanProperty propBusy = new SimpleBooleanProperty(false);


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
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
		grpFileFormat.selectToggle(grpFileFormat.getToggles().stream().filter(tog -> ((RadioButton) tog).getId().equals(Prefs.get(PrefKey.FILE_FORMAT))).findFirst().orElse(radPNG));

		// set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			handleProgramExit();
		});

		// button enabling
		btnCreateStatistics.disableProperty().bind(
				txtData.textProperty().isEmpty()
				.or(txtOutpath.textProperty().isEmpty())
				.or(grpFileFormat.selectedToggleProperty().isNull())
				.or(propBusy)
				);

		// testing
//		handleCreateStatistics();
//		handleProgramExit();

	}

	/**
	 * Program menu exit.
	 */
	@FXML
	public void handleProgramExit() {

		Prefs.put(PrefKey.FILE, txtData.getText());
		Prefs.put(PrefKey.OUTPATH, txtOutpath.getText());
		Prefs.put(PrefKey.FILE_FORMAT, ((RadioButton) grpFileFormat.getSelectedToggle()).getId());

		Platform.exit();
	}

	/**
	 * Help menu about.
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
	 */
	@FXML
	private void handleCreateStatistics() {

		propBusy.setValue(true);
		primaryStage.getScene().setCursor(Cursor.WAIT);
		txtLog.clear();

		Task<Void> taskStats = new Task<Void>() {
			@Override protected Void call() throws Exception {

				// read and parse data
				Path pathDataFile = Paths.get(txtData.getText());
				appendTextLogMessage(MessageFormat.format("Lade Daten aus ''{0}''.", pathDataFile.toAbsolutePath().normalize().toString()));

				Content theContent = null;

				if (pathDataFile.toString().endsWith(".csv")) {
					theContent = readFromCSV(pathDataFile);
				}

				if (pathDataFile.toString().endsWith(".ods")) {
					theContent = readFromODS(pathDataFile);
				}

				if ((theContent == null) || theContent.getSeason().isEmpty()) {
					appendTextLogMessage("Keine Daten vorhanden.");
					return null;
				}

				Path pathOut = Paths.get(txtOutpath.getText());
				appendTextLogMessage(MessageFormat.format("Erzeuge Grafiken in ''{0}''.", pathOut.toAbsolutePath().normalize().toString()));

				// select season to generate charts for
				Season theSeason = theContent.getSeason().get(theContent.getSeason().size() - 1);

				// generate charts
				PluginManager pluginManager = new DefaultPluginManager();
			    pluginManager.loadPlugins();
			    pluginManager.startPlugins();

			    List<PluginWrapper> lstPlugins = pluginManager.getPlugins();
			    for (PluginWrapper plugin : lstPlugins) {
			    	List<IChartGenerator> chartGenerators = pluginManager.getExtensions(IChartGenerator.class, plugin.getDescriptor().getPluginId());
			    	for (IChartGenerator chartGenerator : chartGenerators) {
			    		appendTextLogMessage(MessageFormat.format("  Nutze Generator ''{0}'' aus Plugin ''{1}''.", chartGenerator.getClass().getSimpleName(), plugin.getDescriptor().getPluginId()));
			    		chartGenerator.generateChart(theSeason).entrySet().stream()
			    		.forEach(entrySet -> {
			    			writeChart(pathOut, theSeason, entrySet.getKey(), entrySet.getValue());
			    		});
			    	}
				}

				pluginManager.stopPlugins();

				return null;
			}
		};

        // task succeeded - show results
        taskStats.setOnSucceeded(event -> {

        	appendTextLogMessage("Fertig.");
        	propBusy.setValue(false);
        	primaryStage.getScene().setCursor(Cursor.DEFAULT);

        });

        Thread thread = new Thread(taskStats);
        thread.start();

	}

	/**
	 * Returns primary stage.
	 *
	 * @return primary stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Read content from csv file.
	 *
	 * @param theInputPath input path
	 * @return content
	 */
	private Content readFromCSV(final Path theInputPath) {

		Content theContent = new ObjectFactory().createContent();

		Season theSeason = new ObjectFactory().createSeason();
		theSeason.setTitle(new SimpleStringProperty(theInputPath.getFileName().toString().substring(0, theInputPath.getFileName().toString().indexOf('.'))));
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

				String sValue = record.get("LPZ-Diff");
				int iLPZDiff = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);
				sValue = record.get("Live-PZ");
				int iLPZ = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);
				sValue = record.get("Live-PZ-O");
				int iLPZOther = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);

				theMatch.setLivePzBefore(new SimpleIntegerProperty(iLPZ));
				theMatch.setLivePzOther(new SimpleIntegerProperty(iLPZOther));
				theMatch.setLivePzDiff(new SimpleIntegerProperty(iLPZDiff));
				theMatch.setLivePzAfter(new SimpleIntegerProperty(iLPZ + iLPZDiff));

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

				theMatch.setResult(theResult);

				theSeason.getMatch().add(theMatch);

				appendTextLogMessage(String.format("  %03d - %s (%s)",
						theSeason.getMatch().size(), theMatch.getTitle().getValue(),
						theMatch.getResult().getWon().getValue() ? "gewonnen" : "verloren"
						));

			}

		} catch (IOException | IllegalStateException e) {
			e.printStackTrace();
			appendTextLogMessage(String.format("  %s", e.getMessage()));
			return null;
		}

		return theContent;

	}

	/**
	 * Read content from ods file.
	 *
	 * @param theInputPath input path
	 * @return content
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

					if (mapHeader.containsKey("LPZ-Diff")) {

						String sValue = theRow.getCellByIndex(mapHeader.get("LPZ-Diff")).getDisplayText();
						int iLPZDiff = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);
						sValue = theRow.getCellByIndex(mapHeader.get("Live-PZ")).getDisplayText();
						int iLPZ = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);
						sValue = theRow.getCellByIndex(mapHeader.get("Live-PZ-O")).getDisplayText();
						int iLPZOther = Integer.parseInt(sValue.isEmpty() ? "0" : sValue);

						theMatch.setLivePzBefore(new SimpleIntegerProperty(iLPZ));
						theMatch.setLivePzOther(new SimpleIntegerProperty(iLPZOther));
						theMatch.setLivePzDiff(new SimpleIntegerProperty(iLPZDiff));
						theMatch.setLivePzAfter(new SimpleIntegerProperty(iLPZ + iLPZDiff));

					}

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

					theMatch.setResult(theResult);

					theSeason.getMatch().add(theMatch);

					appendTextLogMessage(String.format("  %03d - %s (%s)",
							theSeason.getMatch().size(), theMatch.getTitle().getValue(),
							theMatch.getResult().getWon().getValue() ? "gewonnen" : "verloren"
							));

				}

			}

			theDoc.close();

		} catch (Exception e) {
			e.printStackTrace();
			appendTextLogMessage(String.format("  %s", e.getMessage()));
			return null;
		}

		return theContent;

	}

	/**
	 * Updates text log.
	 *
	 * @param message message to append
	 */
	private void appendTextLogMessage(String message) {
		if (Platform.isFxApplicationThread()) {
			txtLog.appendText(String.format("%s%n", message));
		} else {
			Platform.runLater(() -> txtLog.appendText(String.format("%s%n", message)));
		}
	}

	/**
	 * Write chart.
	 *
	 * @param theOutputPath output path
	 * @param theSeason season
	 * @param theFilename filename
	 * @param theChart chart
	 * @param theSeries chart data
	 */
	private void writeChart(final Path theOutputPath, final Season theSeason, final String theFilename, final Chart<?, ?> theChart) {

		try {

			String sFileFormat = ((RadioButton) grpFileFormat.getSelectedToggle()).getId().substring("rad".length()).toLowerCase();

			Paths.get(theOutputPath.toString(), theSeason.getTitle().getValue()).normalize().toFile().mkdirs();
			
			// adding the file extension is not needed, the save methods add them themselves
			Path pathOut = Paths.get(theOutputPath.toString(), theSeason.getTitle().getValue(), theFilename).normalize();
			
			if (EncoderFormats.BitmapFormats().containsKey(sFileFormat)) {
			    BitmapEncoder.saveBitmap(theChart, pathOut.toString(), EncoderFormats.BitmapFormats().get(sFileFormat));
			} else if (EncoderFormats.VectorFormats().containsKey(sFileFormat)) {
				VectorGraphicsEncoder.saveVectorGraphic(theChart, pathOut.toString(), EncoderFormats.VectorFormats().get(sFileFormat));
			} else {
				throw new IOException(String.format("Dateiformat '%s' unbekannt.", sFileFormat));
			}

			appendTextLogMessage(String.format("    %s", pathOut.toString()));

		} catch (IOException e) {
			e.printStackTrace();
			appendTextLogMessage(String.format("    %s", e.getMessage()));
		}

	}

}

/* EOF */
