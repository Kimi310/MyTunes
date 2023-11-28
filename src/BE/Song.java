package BE;

public class Song {
    private String title;
    private String artist;
    private String category;
    private String time;

    public Song(String title, String artist, String category, String time) {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
