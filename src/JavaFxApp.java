// --module-path "D:\NokolaysDocs\LibraryJava\JavaFX14.0.1\lib" --add-modules javafx.controls,javafx.fxml


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("myFXML.fxml"));
        stage.setTitle("USNG");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}

