package GUI.Controller;

import BE.Song;
import BLL.FilterHandler;
import BLL.MusicPlayer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class MainController implements Initializable {
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider progressslider;
    @FXML
    private TextField filtertxt;
    @FXML
    private TableColumn<Song,String> titleColumn;
    @FXML
    private TableColumn<Song,String> artistColumn;
    @FXML
    private TableColumn<Song,String> categoryColumn;
    @FXML
    private TableColumn<Song,String> timeColumn;

    @FXML
    private Label playinglbl;
    @FXML
    private TableView<Song> songtable = new TableView<Song>();
    @FXML
    private Button playbtn;
    @FXML
    private Button nextbtn;
    @FXML
    private Button prevbtn;
    private MusicPlayer player = new MusicPlayer();
    private final ObservableList<Song> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
        setTablePropertiesOnInit();
        setVolumeListener();
        setProgressOnMouse();
        setDataListener();
        progressslider.disableProperty().set(true);
    }

    private void setDataListener() {
        data.addListener(new ListChangeListener<Song>() {
            @Override
            public void onChanged(Change<? extends Song> c) {
                if (data.isEmpty()){
                    progressslider.disableProperty().set(true);
                }
            }
        });
    }

    @FXML
    private void addSong(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AddSongView.fxml"));
        Parent root = loader.load();
        AddSongViewController addSong = loader.getController();
        addSong.setParentController(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void addSongToTable(Song song){
        data.add(song);
        System.out.println(data.get(0).getArtist());
    }

    public void playPauseMusicHandler(ActionEvent actionEvent) {
        player.playPause(playbtn);
    }

    private void setTablePropertiesOnInit(){  // sets all the necessary properties to song table
        songtable.setEditable(true);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        songtable.setItems(data);
        songtable.setRowFactory(tv -> {
            TableRow<Song> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount()==2 && !row.isEmpty()){
                    Song rowSong = row.getItem();
                    player.playNewSong(rowSong.getFile(),playbtn);
                    player.beginTimer(progressslider);
                    progressslider.disableProperty().set(false);
                    progressslider.setMax(player.getSound().getDuration().toSeconds());
                }
            });
            return row;
        });
    }

    private void setImages(){ // sets images to button
        nextbtn.setGraphic(new ImageView("Images/next.png"));
        prevbtn.setGraphic(new ImageView("Images/prev.png"));
        playbtn.setGraphic(new ImageView("Images/play.png"));
    }

    public void filterTableHandler(ActionEvent actionEvent) {
        if (!filtertxt.getText().isEmpty()){
            songtable.setItems(new FilterHandler().filter(data,filtertxt));
        }else {
            songtable.setItems(data);
        }
    }

    public void resetFilter(ActionEvent actionEvent) {
        filtertxt.setText("");
        songtable.setItems(data);
    }

    public void setVolumeListener(){
         volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                 MediaPlayer mediaPlayer = player.getPlayer();
                 mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
             }
         });
    }
    public void setProgressOnMouse() {
        progressslider.setOnMouseClicked(event -> {
            MediaPlayer mediaPlayer = player.getPlayer();
            mediaPlayer.seek(Duration.seconds(progressslider.getValue()));
        });
    }
}
