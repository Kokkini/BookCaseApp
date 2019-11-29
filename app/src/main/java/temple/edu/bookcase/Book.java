package temple.edu.bookcase;

import java.io.Serializable;

public class Book implements Serializable {
    public int id;
    public String title;
    public String author;
    public int published;
    public String coverURL;
    public int duration;

    public Book(int id, String title, String author, int published, String coverURL, int duration){
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
        this.coverURL = coverURL;
        this.duration = duration;
    }
}
