package ca.georgiancollege.comp1011m2022assignment3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable
{

    @FXML
    private BorderPane boderPane;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Movie> resultsListView;

    @FXML
    private TextField searchTextField;

    @FXML
    void searchButtonClicked(ActionEvent event)
    {
        var searchResults=APIManager.Instance().getMovieFromOMDBBySearchTerm(searchTextField.getText());
        resultsListView.getItems().clear();
        if(searchResults.getMovies()!=null){
            resultsListView.getItems().addAll(searchResults.getMovies());

            resultsListView.getSelectionModel().select(0);
        }
        else {
            //movie not found maybe output to a message label of some kind
        }

    }

    @FXML
    void viewDetailsButtonClicked(ActionEvent event) {
        String selectedMovie = String.valueOf(resultsListView.getSelectionModel().getSelectedItem());
        String[] details = selectedMovie.split(",");
        var movieDetails = APIManager.Instance().getMovieFromOMDBByTitleAndYear(details[0].substring(7), details[1].substring(7));

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("details-view.fxml"));
            Parent root = fxmlLoader.load();
            DetailsViewController detailsViewController = fxmlLoader.getController();
            detailsViewController.transferDetails(movieDetails.movieDetails(),movieDetails.getPoster());
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Movie Details");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void submitSearchedText(ActionEvent event) {
        searchButtonClicked(event);
        resultsListView.requestFocus();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var posterNotFoundImage=new Image("https://trailerfailure.com/img/images/missingConverphoto.jpg");

        resultsListView.getSelectionModel().selectedItemProperty().addListener((obs , oldMovieSelected,newMovieSelected )->{
            System.out.println(newMovieSelected);
            try {
             {
                    imageView.setImage(new Image(newMovieSelected.getPoster()));
                }

            }catch (IllegalArgumentException e){

                    imageView.setImage(new Image("posterNotFoundImage"));

            }
        });
    }
}
