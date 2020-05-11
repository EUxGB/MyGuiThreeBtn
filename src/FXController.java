import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

import java.io.File;

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


    @FXML
       public void handleBtnSelectWorkCatalog(ActionEvent event) {
        dc.setTitle("Выбери каталог, в который будут сохранены файлы");
        dc.setInitialDirectory(new File("C:\\"));
        File file = dc.showDialog(null);
        if (file != null && file.isDirectory()) {
            ivWorkCatalog.clear();
            ivWorkCatalog.appendText(file.getAbsolutePath());
        } else ivWorkCatalog.appendText("Это не папка");
    }

    @FXML
    private void handleBtnOpenMultiFiles(ActionEvent event) {
        fc.setTitle("Выбери каталог, в который будут сохранены файлы");
        fc.setInitialDirectory(new File("C:\\"));
        File file = fc.showOpenDialog(null);
        if (file != null && file.isFile()) {
            ivWorkCatalog.clear();
            ivWorkCatalog.appendText(file.getAbsolutePath());
        } else ivWorkCatalog.appendText("Это не папка");
    }

    @FXML
    private void handleBtnOpenImgFile(ActionEvent event) {
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
    }
}

