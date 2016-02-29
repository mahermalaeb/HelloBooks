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
            showAllBirthdays(view);
         }
      });
   }


   @Override
   public void onClick(View view) {
      addBirthday(view);
      Intent intent = new Intent();
      intent.setAction("murex.dojo.hellobooks.app..LIKE_INTENT");
      sendBroadcast(intent);
   }

   public void addBirthday(View view) {
      // Add a new birthday record
      ContentValues values = new ContentValues();

      values.put(BirthProvider.NAME, "Maher");

      values.put(BirthProvider.BIRTHDAY, "January");

      Uri uri = getContentResolver().insert(
        BirthProvider.CONTENT_URI, values);

      Toast.makeText(getBaseContext(),
        "Javacodegeeks: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
   }


   public void showAllBirthdays(View view) {
      // Show all the birthdays sorted by friend's name
      String URL = "content://murex.dojo.hellobooks.app/friends";
      Uri friends = Uri.parse(URL);
      Cursor c = getContentResolver().query(friends, null, null, null, "name");
      String result = "Javacodegeeks Results:";

      if (!c.moveToFirst()) {
         Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
      } else {
         do {
            result = result + "\n" + c.getString(c.getColumnIndex(BirthProvider.NAME)) +
              " with id " + c.getString(c.getColumnIndex(BirthProvider.ID)) +
              " has birthday: " + c.getString(c.getColumnIndex(BirthProvider.BIRTHDAY));
         } while (c.moveToNext());
         Toast.makeText(this, result, Toast.LENGTH_LONG).show();
      }

   }


}
