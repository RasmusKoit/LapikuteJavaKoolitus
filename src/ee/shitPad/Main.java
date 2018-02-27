package ee.shitPad;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;

public class Main extends Application{

    private FileChooser fileChooser = new FileChooser();
    private TextArea textArea = new TextArea();

    public static void main(String[] args) {
	    System.out.println("Stuff");
	    launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        HBox menu = new HBox();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveFile(primaryStage));

        Button openButton = new Button("Open");
        openButton.setOnAction(event -> openFile(primaryStage));

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> quit(primaryStage));

        Button dateButton = new Button("Date");
        dateButton.setOnAction(event -> shitDate());



        menu.getChildren().addAll(openButton, saveButton, dateButton, quitButton);

        root.setTop(menu);


        root.setCenter(textArea);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);

        primaryStage.setTitle("ShitPad++");
        primaryStage.show();
    }


    private void openFile(Stage primaryStage) {
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file == null) {
            return;
        }

        try {
            byte[] contents = Files.readAllBytes(file.toPath());
            String stringContents = new String(contents, StandardCharsets.UTF_8);
            textArea.setText(stringContents);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(Stage primaryStage) {
        fileChooser.showSaveDialog(primaryStage);
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file == null) {
            return;
        }

        byte[] contents = textArea.getText().getBytes(StandardCharsets.UTF_8);
        try {
            Files.write(file.toPath(), contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void quit(Stage primaryStage) {
        primaryStage.hide();
    }

    private void shitDate() {
        if (!(textArea.getText().endsWith("\n")) && !(textArea.getText().isEmpty())) {
            textArea.appendText("\n");
        }
        textArea.appendText(String.valueOf(LocalDate.now()));

    }
}
