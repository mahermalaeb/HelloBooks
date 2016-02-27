package murex.dojo.hellobooks.app;


public class Book {
    private String title;
    private String imageURL;

    public Book(String imageURL, String title) {
        this.imageURL = imageURL;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
