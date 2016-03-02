package murex.dojo.hellobooks.app;

import static android.widget.Toast.LENGTH_LONG;
import static murex.dojo.hellobooks.app.BookProvider.CONTENT_URI;
import static murex.dojo.hellobooks.app.BookProvider.NAME;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BookDetails extends Activity {

   public static final String LIKE_BROADCAST = "murex.dojo.hellobooks.app.LIKE_INTENT";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_book_details);

      final String details = getIntent().getStringExtra(NAME);
      final TextView message = (TextView) findViewById(R.id.message);
      message.setText(details);

      final Button likeButton = (Button) findViewById(R.id.likeButton);
      likeButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            insertBook();
            broadcastLike();
         }
      });
   }

   private void broadcastLike() {
      Intent intent = new Intent();
      intent.setAction(LIKE_BROADCAST);
      sendBroadcast(intent);
   }


   public void insertBook() {
      ContentValues values = new ContentValues();
      final String bookName = getIntent().getStringExtra(NAME);
      values.put(NAME, bookName);

      Uri uri = getContentResolver().insert(CONTENT_URI, values);
      Toast.makeText(getBaseContext(), bookName + " " + uri.toString() + " inserted!", LENGTH_LONG).show();
   }


   public void showAllBooks(View view) {
      Uri friends = Uri.parse(BookProvider.URL);
      Cursor c = getContentResolver().query(friends, null, null, null, null);
      String result = "";

      if (!c.moveToFirst()) {
         Toast.makeText(this, result + " no content yet!", LENGTH_LONG).show();
      } else {
         do {
            result = result + "\n" + " id " + c.getString(c.getColumnIndex(BookProvider.ID)) + ", name " + c.getString(c.getColumnIndex(BookProvider.NAME));
         } while (c.moveToNext());
         Toast.makeText(this, result, LENGTH_LONG).show();
      }

   }

   public void close(View view) {
      this.finish();
   }
}
