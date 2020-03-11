package sample;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.lineSeparator;

public class NotePadViewModel {
    private Stage stage;
    private Path currentFile;
    private TextArea textArea;
    private String loggedText = "";

    public Stage getStage() {
        return stage;
    }

    public NotePadViewModel(Stage stage) {
        this.stage = stage;
    }

    void onSave(List<String> lines, String name) {
        FileUtils.writeAll(lines, name);
    }

    void open() {
        FileChooser fileChooser = new FileChooser();
        Path openedFile = fileChooser.showOpenDialog(stage).toPath();
        if (openedFile != null) {
            System.out.println("RRReaded");
            currentFile = openedFile;
            refill(FileManager.readPath(currentFile));
            updateCondition();
        }
    }

    private void refill(List<String> contents) {
        textArea.clear();
        for (String line : contents) {
            textArea.appendText(line + lineSeparator());
        }
    }

    private void logText() {
        loggedText = textArea.getText();
    }

    void updateTitle() {
        stage.setTitle(fileName() + " — Блокнот");
    }

    private String fileName() {
        if (currentFile == null) {
            return "Безымянный";
        }
        return currentFile.getFileName().toString();
    }

    private void updateCondition() {
        logText();
        updateTitle();
    }
}