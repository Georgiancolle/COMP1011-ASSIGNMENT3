package ca.georgiancollege.comp1011m2022assignment3;

import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsViewController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label movieDetails;

    @FXML
    void backToSearchViewButtonClicked(ActionEvent event)throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Movie Search");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


