import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FXController {

    @FXML
    public Button btnSelectWorkCatalog;
    @FXML
    public Button btnOpenMultiFiles;
    @FXML
    public Button btnOpenImgFile;
    @FXML
    public Button btnConvertToPdf;
    @FXML
    public Button btnSignDoc;
    @FXML
    public Button btnOpenPDFDir;
    @FXML
    public Button btnOpenSignDir;
    @FXML
    public Button btnOpenOpisDir;
    @FXML
    public Button btnGenInventory;
    @FXML
    public Button btnClearFiles;

    @FXML
    public TextArea ivWorkCatalog;
    @FXML
    public ListView lvFiles;
    @FXML
    public ImageView imgFile;

    public FileChooser fc = new FileChooser();
    public DirectoryChooser dc = new DirectoryChooser();
    public String initialDirectory = "C:\\";


    @FXML
       public void handleBtnSelectWorkCatalog(ActionEvent event) {
        dc.setTitle("Выбери каталог, в который будут сохранены файлы");
        dc.setInitialDirectory(new File(initialDirectory));
        File file = dc.showDialog(null);
        if (file != null && file.isDirectory()) {
            ivWorkCatalog.clear();
            ivWorkCatalog.appendText(file.getAbsolutePath());
        } else ivWorkCatalog.appendText("Это не папка");
        initialDirectory = file.getAbsolutePath();
    }

    @FXML
    private void handleBtnOpenMultiFiles(ActionEvent event) {

        fc.setTitle("Выбери несколько файлов для работы");
        fc.setInitialDirectory(new File(initialDirectory));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS DOC PDF", "*.pdf ; *.xl*; *.doc*"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*.*")

                );

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        lvFiles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (File selectedFile : selectedFiles) {


            if (selectedFiles != null) {
                lvFiles.getItems().add(selectedFile.getAbsoluteFile());


            } else {
                lvFiles.getItems().add("Ошибка ввода, повторить выбор");

            }
        }
        if (selectedFiles != null) {
            initialDirectory = selectedFiles.get(0).getParent();
        }

    }

    @FXML
    private void handleBtnOpenImgFile(ActionEvent event) {
        dc.setTitle("Выбери каталог, в который будут сохранены файлы");
        dc.setInitialDirectory(new File(initialDirectory));
        File file = dc.showDialog(null);
        if (file != null && file.isDirectory()) {
            imgFile.cl;
            imgFile.setImage();
        } else ivWorkCatalog.appendText("Это не папка");
        initialDirectory = file.getAbsolutePath();
    }

    @FXML
    private void handleBtnConvertToPdf(ActionEvent event) {
    }

    @FXML
    private void handleBtnSignDoc(ActionEvent event) {
    }

    @FXML
    private void handleBtnOpenDir(ActionEvent event) {
    }

    @FXML
    private void handleBtnGenInventory(ActionEvent event) {
    }

    @FXML
    private void handleBtnClearFiles(ActionEvent event) {
        List<Integer> selectedItemsCopy = new ArrayList<>(lvFiles.getSelectionModel().getSelectedItems());
        lvFiles.getItems().removeAll(selectedItemsCopy);



    }
}

