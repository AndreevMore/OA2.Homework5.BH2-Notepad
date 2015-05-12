package ua.org.oa.kostitcina;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;

public class Notepad extends Application {

	// Save file
	public void SaveFile(String content, File file) {
		try {
			FileWriter fileWriter = null;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
		}

	}

	// Open file
	public StringBuilder readFile(File selectedFile) {
		StringBuilder sb = new StringBuilder(1024);
		String curLine = "";
		try {
			FileReader fr = new FileReader(selectedFile);
			BufferedReader br = new BufferedReader(fr);

			while (curLine != null) {
				curLine = br.readLine();
				sb.append(curLine).append("\n");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return sb;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane borderPane = new BorderPane();
		// Menu Bar
		MenuBar menuBar = new MenuBar();

		// Menu 1
		Menu file = new Menu("File");
		// Menu 1 items
		MenuItem itmOpen = new MenuItem("Open");
		MenuItem itmSave = new MenuItem("Save");
		MenuItem itmClose = new MenuItem("Close");

		// Menu 2
		Menu edit = new Menu("Edit");
		// Menu 2 items
		MenuItem itmFind = new MenuItem("X Find");

		// Menu 3
		Menu help = new Menu("Help");
		// Menu 2 items
		MenuItem itmAboutUs = new MenuItem("Х About Us");

		// Add MenuuItems to each menu
		file.getItems().addAll(itmOpen, itmSave, itmClose);
		edit.getItems().addAll(itmFind);
		help.getItems().addAll(itmAboutUs);

		// Add Menus to MenuBar
		menuBar.getMenus().addAll(file, edit, help);

		TextArea textArea = new TextArea();
		borderPane.setCenter(textArea);
		textArea.getScrollLeft();

		TextField textField = new TextField();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(3, 0, 3, 0));
		grid.add(new Label("Search in text: "), 0, 0);
		grid.add(textField, 1, 0);

		borderPane.setBottom(grid);

		// add MenuBar at the root

		borderPane.setTop(menuBar);

		// action when press FIND itm menu

		textField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// реализовать поиск
			}
		});

		// action when press SAVE itm menu
		itmSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						"TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File file = fileChooser.showSaveDialog(primaryStage);

				if (file != null) {
					SaveFile(textArea.getText(), file);
				}
			}
		});

		// action when press OPENFILE itm menu
		itmOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent Event) {
				String currentDir = System.getProperty("user.dir")
						+ File.separator;
				StringBuilder sb = null;

				FileChooserBuilder fcb = FileChooserBuilder.create();
				FileChooser fc = fcb.title("Open Dialog")
						.initialDirectory(new File(currentDir)).build();
				File selectedFile = fc.showOpenDialog(primaryStage);
				sb = readFile(selectedFile);
				textArea.setText(sb.toString());
			}
		});

		// action when press CLOSE itm menu
		itmClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent Event) {
				System.exit(0);
			}
		});

		// set scene start app. end of file
		Scene scene = new Scene(borderPane, 600, 400);

		primaryStage.setScene(scene);
		primaryStage.setTitle("My Notepad");
		primaryStage.show();

	}
}