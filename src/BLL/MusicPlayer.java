package BLL;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class MusicPlayer {
    private boolean playing = false;
    Media sound;
    private MediaPlayer player;
    public void playNewSong(String file, Button playButton){
        playButton.setGraphic(new ImageView("Images/pause.png"));
        sound = new Media(new File(file).toURI().toString());
        player = new MediaPlayer(sound);
        player.play();
        playing=true;
    }
    public void playPause (Button playButton){
        if (sound==null){
            return;
        }else{
            if (playing){
                playButton.setGraphic(new ImageView("Images/play.png"));
                player.pause();
                playing=false;
            }else {
                playButton.setGraphic(new ImageView("Images/pause.png"));
                player.play();
                playing=true;
            }
        }
    }
}
