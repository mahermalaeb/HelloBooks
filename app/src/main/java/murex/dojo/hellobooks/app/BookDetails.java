package murex.dojo.hellobooks.app;

import static android.widget.Toast.LENGTH_LONG;
import static murex.dojo.hellobooks.app.Constants.*;
import static murex.dojo.hellobooks.app.Constants.NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BookDetails extends Activity {

   private List<Observer> observers = new ArrayList<Observer>();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_book_details);

      observers.add(new BroacastObserver(this));
      observers.add(new LikeObserver(this));

      final String details = getIntent().getStringExtra(NAME);
      final TextView message = (TextView) findViewById(R.id.message);
      message.setText(details);

      final Button likeButton = (Button) findViewById(R.id.likeButton);
      likeButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            update();
         }
      });
   }

   private void update() {
      final String bookName = getIntent().getStringExtra(NAME);
      for (Observer observer : observers) {
         observer.update(null, bookName);
      }
   }

   public void showAllBooks(View view) {
      Uri friends = Uri.parse(URL);
      Cursor c = getContentResolver().query(friends, null, null, null, null);
      String result = "";

      if (!c.moveToFirst()) {
         Toast.makeText(this, result + " no content yet!", LENGTH_LONG).show();
      } else {
         do {
            result = result + "\n" + " id " + c.getString(c.getColumnIndex(ID)) + ", name " + c.getString(c.getColumnIndex(NAME));
         } while (c.moveToNext());
         Toast.makeText(this, result, LENGTH_LONG).show();
      }

   }

   public void close(View view) {
      this.finish();
   }
}
