package murex.dojo.hellobooks.app;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BooksJsonParser {

    public static ArrayList<Book> parseJsonStringIntoBooksList(String jsonString) throws JSONException {
        ArrayList<Book> booksListFromJson = new ArrayList<Book>();
        JSONObject jsonStringAsObject = new JSONObject(jsonString);
        JSONArray itemsJsonArray = jsonStringAsObject.getJSONArray("items");
        for (int i = 0; i < itemsJsonArray.length(); i++) {
            JSONObject volumeInfoObject = itemsJsonArray.getJSONObject(i).getJSONObject("volumeInfo");
            String bookTitle = volumeInfoObject.getString("title");

            JSONObject imageLinksObject =  volumeInfoObject.getJSONObject("imageLinks");
            String imageUrl = imageLinksObject.getString("thumbnail");

            Book book = new Book(bookTitle,imageUrl);
            booksListFromJson.add(book);
        }
        return booksListFromJson;
    }
}
