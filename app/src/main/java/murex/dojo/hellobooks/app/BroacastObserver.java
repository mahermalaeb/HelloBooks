package murex.dojo.hellobooks.app;

import static murex.dojo.hellobooks.app.Constants.NAME;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.content.Intent;

public class BroacastObserver implements Observer {
   private Context context;

   public BroacastObserver(Context context) {
      this.context = context;
   }

   @Override
   public void update(Observable observable, Object bookName) {
      Intent intent = new Intent();
      intent.setAction(Constants.LIKE_BROADCAST);
      intent.putExtra(NAME, (String) bookName);
      context.sendBroadcast(intent);
   }
}
