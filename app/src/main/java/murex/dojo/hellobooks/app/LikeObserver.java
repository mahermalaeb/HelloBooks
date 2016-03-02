package murex.dojo.hellobooks.app;

import static android.widget.Toast.LENGTH_LONG;
import static murex.dojo.hellobooks.app.BookProvider.CONTENT_URI;
import static murex.dojo.hellobooks.app.BookProvider.NAME;

import java.util.Observable;
import java.util.Observer;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

public class LikeObserver implements Observer {
   private Context context;

   public LikeObserver(Context context) {
      this.context = context;
   }

   @Override
   public void update(Observable observable, Object bookName) {
      ContentValues values = new ContentValues();
      values.put(NAME, (String) bookName);

      Uri uri = context.getContentResolver().insert(CONTENT_URI, values);
      Toast.makeText(context, bookName + " " + uri.toString() + " inserted!", LENGTH_LONG).show();
   }
}
