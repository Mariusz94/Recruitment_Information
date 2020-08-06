package pl.lyszczarzmariusz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.lyszczarzmariusz.model.service.ListCompanyService;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ListCompanyService.existConfigurationFolder();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/pl/lyszczarzmariusz/view/HomeView.fxml"));
        Pane pane = loader.load();

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(s -> {
            try {
                ListCompanyService.writeListToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.exit();
        });

        primaryStage.setTitle("Recruitment Information");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
