package murex.dojo.hellobooks.app;

import android.net.Uri;

public class Constants {


   public static final String ID = "id";
   public static final String NAME = "name";
   public static final String DATABASE_NAME = "app";
   public static final String TABLE_NAME = "books";
   public static final String CREATE_TABLE =
     " CREATE TABLE " + TABLE_NAME +
       " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
       " name TEXT NOT NULL);";
   public static final int DATABASE_VERSION = 1;

   public static final String LIKE_BROADCAST = "murex.dojo.hellobooks.app.LIKE_INTENT";
   public static final String PROVIDER_NAME = "murex.dojo.hellobooks.app";
   public static final String URL = "content://" + PROVIDER_NAME + "/books";
   public static final Uri CONTENT_URI = Uri.parse(URL);

   public static final String BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyBN8xJKNTqENR17M7uyAgBocqYHXY1eYi8";
}
