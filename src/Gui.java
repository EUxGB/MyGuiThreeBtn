import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Gui implements Initializable {

    @FXML
    private Button btnSelectWorkCatalog;
    @FXML
    private Button btnOpenMultiFiles;
    @FXML
    private Button btnOpenImgFile;
    @FXML
    private Button btnConvertToPdf;
    @FXML
    private Button btnSignDoc;
    @FXML
    private Button btnOpenPDFDir;
    @FXML
    private Button btnOpenSignDir;
    @FXML
    private Button btnOpenOpisDir;
    @FXML
    private Button btnGenInventory;
    @FXML
    private Button btnClearFiles;

    @FXML
    private TextArea ivWorkCatalog;
    @FXML
    private ListView lvFiles;
    @FXML
    private ImageView ivFile;

    private FileChooser fc = new FileChooser();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleBtnSelectWorkCatalog(ActionEvent event) {
        fc.setTitle("Выбери каталог, в который будут сохранены файлы");
        fc.setInitialDirectory(new File("C:\\"));
        File file = fc.showOpenDialog(null);
        if (file != null && file.isDirectory()) {

            ivWorkCatalog.appendText(file.getAbsolutePath());
        } else ivWorkCatalog.appendText("Это не папка");
    }

    @FXML
    private void handleBtnOpenMultiFiles(ActionEvent event) {
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

