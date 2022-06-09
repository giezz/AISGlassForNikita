package glass.aisglass.bin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("Glass");
        stage.setScene(scene);
        stage.show();
    }

    public static void openScene (Window owner, Modality modality, String fxmlFile, String title, boolean isResizable) {
        try {
            Stage stage = loadStage(fxmlFile);
            stage.setTitle(title);
            stage.initModality(modality);
            stage.initOwner(owner);
            stage.centerOnScreen();
            stage.setResizable(isResizable);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Stage loadStage(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}