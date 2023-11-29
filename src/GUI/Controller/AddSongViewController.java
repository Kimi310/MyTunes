package GUI.Controller;

import BE.Song;
import BLL.AddingSongHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSongViewController {
    @FXML
    private Label errorlbl;
    @FXML
    private TextField categorytxt;
    @FXML
    private TextField titletxt;
    @FXML
    private TextField artisttxt;
    @FXML
    private TextField timetxt;
    @FXML
    private TextField filetxt;
    private AddingSongHandler handler = new AddingSongHandler();

    private MainController controller = new MainController();

    public void addSongToList(ActionEvent actionEvent) {
        if (handler.checkNewSong(handler.textFieldsToString(titletxt,artisttxt,categorytxt,timetxt,filetxt))){
            Song song = new Song(titletxt.getText(),artisttxt.getText(),categorytxt.getText(),timetxt.getText(),filetxt.getText());
            controller.addSongToTable(song);
            Stage stage = (Stage) filetxt.getScene().getWindow();
            stage.close();
        }else{
            errorlbl.setVisible(true);
        }
    }

    public void setParentController (MainController controller){
        this.controller = controller;
    }
}
