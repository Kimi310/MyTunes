package GUI.Controller;

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

    public void addSongToList(ActionEvent actionEvent) {
        if (checkNewSong(textFieldsToString())){
            Stage stage = (Stage) filetxt.getScene().getWindow();
            stage.close();
        }else{
            errorlbl.setVisible(true);
        }

    }

    private String[] textFieldsToString () {
        String[] s = new String[5];
        s[0] = titletxt.getText();
        s[1] = artisttxt.getText();
        s[2] = categorytxt.getText();
        s[3] = timetxt.getText();
        s[4] = filetxt.getText();
        return s;
    }

    static boolean checkNewSong (String[] s){
        if (s[0].isEmpty() || s[1].isEmpty() || s[2].isEmpty() || s[3].isEmpty() || s[4].isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
