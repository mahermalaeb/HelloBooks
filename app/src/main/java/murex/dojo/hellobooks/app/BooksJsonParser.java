package murex.dojo.hellobooks.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 2/27/2016.
 */
public class BooksJsonParser {

    public static ArrayList<Book> parseJsonStringIntoBooksList(String jsonString) throws JSONException {
        ArrayList<Book> booksListFromJson = new ArrayList<Book>();
        JSONObject jsonStringAsObject = new JSONObject(jsonString);
        JSONArray itemsJsonArray = jsonStringAsObject.getJSONArray("items");
        for (int i = 0; i < itemsJsonArray.length(); i++) {
            JSONObject volumeInfoObject = itemsJsonArray.getJSONObject(i).getJSONObject("volumeInfo");
            String bookTitle = volumeInfoObject.getString("title");

            Book book = new Book(bookTitle);
            booksListFromJson.add(book);
        }
        return booksListFromJson;
    }
}
