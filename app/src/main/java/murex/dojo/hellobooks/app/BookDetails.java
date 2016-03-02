package murex.dojo.hellobooks.app;

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


public class BookDetails extends Activity implements View.OnClickListener {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_book_details);
      final String details = getIntent().getStringExtra("details");
      final TextView message = (TextView) findViewById(R.id.message);
      message.setText(details);

      final Button likeButton = (Button) findViewById(R.id.likeButton);
      likeButton.setOnClickListener(this);

      final Button showAll = (Button) findViewById(R.id.likesButton);
      showAll.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            showAllBooks(view);
         }
      });
   }


   @Override
   public void onClick(View view) {
      addBook(view);
      Intent intent = new Intent();
      intent.setAction("murex.dojo.hellobooks.app.LIKE_INTENT");
      sendBroadcast(intent);
   }

   public void addBook(View view) {
      ContentValues values = new ContentValues();

      values.put(BookProvider.NAME, "January");

      Uri uri = getContentResolver().insert(
        BookProvider.CONTENT_URI, values);

      Toast.makeText(getBaseContext(), uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
   }


   public void showAllBooks(View view) {
      Uri friends = Uri.parse(BookProvider.URL);
      Cursor c = getContentResolver().query(friends, null, null, null, null);
      String result = "";

      if (!c.moveToFirst()) {
         Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
      } else {
         do {
            result = result + "\n" +
              " with id " + c.getString(c.getColumnIndex(BookProvider.ID));
         } while (c.moveToNext());
         Toast.makeText(this, result, Toast.LENGTH_LONG).show();
      }

   }


}
